package org.mdavi.neuralnetwork.hopfield

import spock.lang.Specification;

class LayerTest extends Specification
{

  def "Layer description" () {
    when: "The layer is created with the weights of the connections between the neurons"
      Layer layer = new Layer(weights)
    then: "it is composed by ${NetworkSize.SIZE} neurons"
      layer.neurons?.size() == NetworkSize.SIZE
    where:
      weights << [
        [[0, -1, 1, -1], [-1, 0, -1, 1],[1, -1, 0, -1],[-1, 1, -1, 0]]
        ] 
  }
  
  def "Layer output" () {
    when: "The layer is activated with an input pattern"
      Layer layer = new Layer(weights)
      def output = layer.activateFor(pattern)
    then: "it computes an out pattern"
      output.size() == 4
      output == pattern
    where:
      weights << [
        [[0, -1, 1, -1], [-1, 0, -1, 1],[1, -1, 0, -1],[-1, 1, -1, 0]]
        ]
      pattern << [[0,1,0,1]]
  }
}
