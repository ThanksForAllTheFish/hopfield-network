package org.mdavi.neuralnetwork.hopfield.network

import org.mdavi.neuralnetwork.hopfield.coach.Coach;
import org.mdavi.neuralnetwork.hopfield.coach.CoachCreator;
import org.mdavi.neuralnetwork.hopfield.coach.HopfieldCoachCreator;
import org.mdavi.neuralnetwork.hopfield.network.components.layer.HopfieldLayerCreator
import org.mdavi.neuralnetwork.hopfield.network.components.layer.Layer;
import org.mdavi.neuralnetwork.hopfield.network.components.layer.LayerCreator;
import org.mdavi.neuralnetwork.hopfield.network.components.neuron.HopfieldNeuronCreator

import spock.lang.Specification

class HopfieldNetworkTest extends Specification
{
  HopfieldNetwork network
  def coachCreator = Mock(CoachCreator)
  def layerCreator = Mock(LayerCreator)
  def coach = Mock(Coach)
  def layer = Mock(Layer)
  
  def setup () {
    network = new HopfieldNetwork(coachCreator, layerCreator)
  }
  
  def "Hopfield nework training" () {
    when: "The hopfield network is trained to recognize a pattern"
      network.train(pattern)
    then: "it changes the connection weights accordingly"
      interaction {
        coachExpectations (pattern, initial, expectedWeights)
      }
      network.weights == expectedWeights
    where:
      pattern << [[0,1,0,1]]
      initial << [[[0, 0, 0, 0], [0, 0, 0, 0],[0, 0, 0, 0],[0, 0, 0, 0]]]
      expectedWeights << [
        [[0, -1, 1, -1], [-1, 0, -1, 1],[1, -1, 0, -1],[-1, 1, -1, 0]]
        ]
  }
  
  def "Hopfield nework recognition" () {
    when: "The hopfield network is trained to recognize a pattern"
      network.train(pattern)
      def output = network.recognize(pattern)
    then: "can recognize that pattern and the inverse one"
      interaction {
        coachExpectations (pattern, initial, expectedWeights)
        layerExpectations(pattern, expectedWeights)
      }
      output == pattern
    where:
      pattern << [[0,1,0,1]]
      initial << [
        [[0, 0, 0, 0], [0, 0, 0, 0],[0, 0, 0, 0],[0, 0, 0, 0]]
        ]
      expectedWeights << [
        [[0, -1, 1, -1], [-1, 0, -1, 1],[1, -1, 0, -1],[-1, 1, -1, 0]]
        ]
  }
  
  
  def "Hopfield nework multipattern recognition" () {
    when: "The hopfield network is trained to recognize more patterns"
      network.train(pattern[0])
      network.train(pattern[1])
      def output = network.recognize(input)
    then: "can recognize both trained patterns and the inverse ones"
      interaction {
        coachExpectations (pattern[0], initial, expectedWeights[0])
        coachExpectations (pattern[1], expectedWeights[0], expectedWeights[1])
        layerExpectations(input, expectedWeights[1])
      }
      output == input
    where:
      pattern << [
        [[0,1,0,1], [1,0,0,1]],
        [[0,1,0,1], [1,0,0,1]]]
      input << [
        [0,1,1,0],
        [1,0,1,0]]
      initial << [
        [[0, 0, 0, 0], [0, 0, 0, 0],[0, 0, 0, 0],[0, 0, 0, 0]],
        [[0, 0, 0, 0], [0, 0, 0, 0],[0, 0, 0, 0],[0, 0, 0, 0]]
        ]
      expectedWeights << [
        [[[0, -1, 1, -1], [-1, 0, -1, 1],[1, -1, 0, -1],[-1, 1, -1, 0]], [[0, -2, 0, 0], [-2, 0, 0, 0],[0, 0, 0, -2],[0, 0, -2, 0]]], 
        [[[0, -1, 1, -1], [-1, 0, -1, 1],[1, -1, 0, -1],[-1, 1, -1, 0]], [[0, -2, 0, 0], [-2, 0, 0, 0],[0, 0, 0, -2],[0, 0, -2, 0]]]
        ]
      
  }

  private def layerExpectations (pattern, expectedWeights) {
    1 * layerCreator.newLayer() >>> [layer]
    1 * layer.activateFor(pattern, expectedWeights) >>> [pattern]
  }
  
  private def coachExpectations (pattern, initial, expectedWeights) {
    1 * coachCreator.newCoach() >>> [coach]
    1 * coach.computeTrainingSequence(pattern) >>> [initial]
    1 * coach.obtainWeights(initial) >>> [expectedWeights]
  }
}
