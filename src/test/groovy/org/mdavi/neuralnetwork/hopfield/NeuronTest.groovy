package org.mdavi.neuralnetwork.hopfield

import spock.lang.Specification


class NeuronTest extends Specification
{
  
  def "Neuron output" () {
    when: "The neuron receive an input pattern"
      Neuron neuron = new Neuron(weights)
      def activation = 0
      activation += neuron.activateFor(pattern)
    then: "computes the activation value, summing up the weights related to the 1s in pattern"
      activation == expectedValue
    where:
      weights << [[0,-1,1,-1], [0,-1,1,-1], [0,-1,1,-1]]
      pattern << [[0,1,0,1], [0,0,1,0], [0,0,0,0]]
      expectedValue << [-2, 1, 0]
  }
}
