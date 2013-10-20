package org.mdavi.neuralnetwork.hopfield.network.components.layer

import org.mdavi.neuralnetwork.hopfield.network.NetworkSize;
import org.mdavi.neuralnetwork.hopfield.network.components.neuron.HopfieldNeuronCreator

import spock.lang.Specification;

class LayerTest extends Specification
{
  def layerCreator
  
  def setup () {
    layerCreator = new HopfieldLayerCreator(new HopfieldNeuronCreator())
  }

  def "HopfieldLayer description" () {
    when: "The layer is created with the weights of the connections between the neurons"
      HopfieldLayer layer = layerCreator.newLayer()
    then: "it is composed by ${NetworkSize.SIZE} neurons"
      layer.neurons?.size() == NetworkSize.SIZE
  }
  
  def "HopfieldLayer output" () {
    when: "The layer is activated with an input pattern"
      HopfieldLayer layer = layerCreator.newLayer()
    then: "it computes an out pattern"
      layer.activateFor(pattern, weights) == pattern
    where:
      weights << [
        [[0, -1, 1, -1], [-1, 0, -1, 1],[1, -1, 0, -1],[-1, 1, -1, 0]],
        [[0, -2, 0, 0], [-2, 0, 0, 0],[0, 0, 0, -2],[0, 0, -2, 0]],
        [[0, -2, 0, 0], [-2, 0, 0, 0],[0, 0, 0, -2],[0, 0, -2, 0]]]
        //[[0, 0, 2, 0], [0, 0, 0, 2],[2, 0, 0, 0],[0, 2, 0, 0]]]
      pattern << [
        [0,1,0,1],
        [0,1,0,1],
        [1,0,0,1]]
        //[0,1,0,1]]
  }
}
