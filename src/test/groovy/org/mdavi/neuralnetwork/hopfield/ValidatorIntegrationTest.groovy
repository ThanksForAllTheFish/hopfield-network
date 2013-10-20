package org.mdavi.neuralnetwork.hopfield

import org.mdavi.neuralnetwork.hopfield.coach.HopfieldCoachCreator
import org.mdavi.neuralnetwork.hopfield.network.HopfieldNetwork;
import org.mdavi.neuralnetwork.hopfield.network.components.layer.HopfieldLayerCreator
import org.mdavi.neuralnetwork.hopfield.network.components.neuron.HopfieldNeuronCreator

import spock.lang.Specification;

class ValidatorIntegrationTest extends Specification
{
  
  def network
  
  def setup () {
    network = new HopfieldNetwork(new HopfieldCoachCreator(), new HopfieldLayerCreator(new HopfieldNeuronCreator()))
  }

  def "Validator description" () {
    when: "The validator takes a training pattern, and a Hopfield network"
      Validator validator = new Validator(network, training)
    then: "it tells whether the network can recognize an input pattern"
      validator.computePattern(pattern) == expectedResult
    where:
      pattern << [[0, 1, 0, 1], [1, 0, 1, 0], [1, 1, 1, 1]]
      training << [
        [[0, 1, 0, 1]], 
        [[0, 1, 0, 1]],
        [[0, 1, 0, 1]]]
      expectedResult << [true, true, false]
  }
  
  def "Validator description with multiple training sequence" () {
    when: "The validator takes more training patterns, and a Hopfield network"
      Validator validator = new Validator(network, training)
    then: "it recognizes more input patterns"
      validator.computePattern(pattern) == expectedResult
    where:
      pattern << [[1, 1, 1, 1], 
        [1, 0, 1, 0], 
        [0, 1, 1, 0]]
      training << [
        [[0,0,0,0]], 
        [[0, 1, 0, 1], [1,0,0,1]], 
        [[0, 1, 0, 1], [1,0,0,1]]]
      expectedResult << [true, 
        true, 
        true]
  }
  
  def "Validator shows network limitation" () {
    when: "There are some training sequences"
      Validator validator = new Validator(network, training)
    then: "which breaks the network"
      validator.computePattern(pattern) == expectedResult
    where:
      pattern << [[1, 1, 1, 1], 
        [0, 1, 0, 1], 
        [0, 1, 1, 0]]
      training << [
        [[0,0,0,0]],
        [[0, 1, 0, 1], [1,1,1,1]],
        [[0, 1, 0, 1], [1,1,1,1]]]
      expectedResult << [true, 
        false,
        false]
  }
}
