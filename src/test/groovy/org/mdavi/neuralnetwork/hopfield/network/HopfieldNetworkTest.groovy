package org.mdavi.neuralnetwork.hopfield.network

import org.mdavi.neuralnetwork.hopfield.coach.HopfieldCoachCreator;
import org.mdavi.neuralnetwork.hopfield.network.components.layer.HopfieldLayerCreator
import org.mdavi.neuralnetwork.hopfield.network.components.neuron.HopfieldNeuronCreator

import spock.lang.Specification

class HopfieldNetworkTest extends Specification
{
  HopfieldNetwork network
  def setup () {
    network = new HopfieldNetwork(new HopfieldCoachCreator(), new HopfieldLayerCreator(new HopfieldNeuronCreator()))
  }
  
  def "Hopfield nework training" () {
    when: "The hopfield network is trained to recognize a pattern"
      network.train(pattern)
    then: "it changes the connection weights accordingly"
      network.weights == expectedWeights
    where:
      pattern << [[0,1,0,1]]
      expectedWeights << [
        [[0, -1, 1, -1], [-1, 0, -1, 1],[1, -1, 0, -1],[-1, 1, -1, 0]]
        ]
  }
  
  def "Hopfield nework recognition" () {
    when: "The hopfield network is trained to recognize a pattern"
      network.train(pattern)
    then: "can recognize that pattern and the inverse one"
      network.recognize(pattern) == pattern
    where:
      pattern << [[0,1,0,1], [1,0,1,0]]
  }
  
  def "Hopfield nework multipattern recognition" () {
    when: "The hopfield network is trained to recognize more patterns"
      network.train(pattern[0])
      network.train(pattern[1])
    then: "can recognize both trained patterns and the inverse ones"
      network.recognize(input) == input
    where:
      pattern << [
        [[0,1,0,1], [1,0,0,1]],
        [[0,1,0,1], [1,0,0,1]]]
      input << [
        [0,1,1,0],
        [1,0,1,0]]
      
  }
}
