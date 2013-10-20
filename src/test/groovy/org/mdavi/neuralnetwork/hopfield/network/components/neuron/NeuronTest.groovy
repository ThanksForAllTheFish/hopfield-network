package org.mdavi.neuralnetwork.hopfield.network.components.neuron

import spock.lang.Specification

class NeuronTest extends Specification
{
  
  def "HopfieldNeuron output" () {
    when: "The neuron receive an input pattern"
      HopfieldNeuron neuron = new HopfieldNeuronCreator().newNeuron(weights)
      def activation = 0
      activation += neuron.activateFor(pattern)
    then: "computes the activation value, summing up the weights related to the 1s in pattern"
      activation == expectedValue
    where:
      weights << [[0,-1,1,-1], [0,-1,1,-1], [0,-1,1,-1],
        [0, -2, 0, 0], [-2, 0, 0, 0],[0, 0, 0, -2],[0, 0, -2, 0],
        [0, -2, 0, 0], [-2, 0, 0, 0],[0, 0, 0, -2],[0, 0, -2, 0]]
      pattern << [[0,1,0,1], [0,0,1,0], [0,0,0,0], 
        [0,1,0,1], [0,1,0,1], [0,1,0,1], [0,1,0,1],
        [1,0,0,1], [1,0,0,1], [1,0,0,1], [1,0,0,1]]
      expectedValue << [-2, 1, 0, 
        -2, 0, -2, 0,
        0, -2, -2, 0]
  }
}
