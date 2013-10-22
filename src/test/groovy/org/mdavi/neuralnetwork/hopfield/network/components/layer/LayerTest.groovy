package org.mdavi.neuralnetwork.hopfield.network.components.layer

import org.mdavi.neuralnetwork.hopfield.network.NetworkSize;
import org.mdavi.neuralnetwork.hopfield.network.components.neuron.HopfieldNeuron
import org.mdavi.neuralnetwork.hopfield.network.components.neuron.HopfieldNeuronCreator
import org.mdavi.neuralnetwork.hopfield.network.components.neuron.Neuron;
import org.mdavi.neuralnetwork.hopfield.network.components.neuron.NeuronCreator;

import spock.lang.Specification;

class LayerTest extends Specification
{
  def layerCreator
  def neuronCreator = Mock(NeuronCreator)
  def neuron1 = Mock(Neuron)
  def neuron2 = Mock(Neuron)
  def neuron3 = Mock(Neuron)
  def neuron4 = Mock(Neuron)
  
  def setup () {
    layerCreator = new HopfieldLayerCreator(neuronCreator)
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
      def output = layer.activateFor(pattern, weights)
    then: "it computes an out pattern"
      interaction {
        NetworkSize.SIZE * neuronCreator.newNeuron() >>> [neuron1, neuron2, neuron3, neuron4]
        1 * neuron1.activateFor(pattern, weights[0]) >>> [neuronOutput[0]]
        1 * neuron2.activateFor(pattern, weights[1]) >>> [neuronOutput[1]]
        1 * neuron3.activateFor(pattern, weights[2]) >>> [neuronOutput[2]]
        1 * neuron4.activateFor(pattern, weights[3]) >>> [neuronOutput[3]]
      }
      output == pattern
      
    where:
      neuronOutput << [
        [-2, 1, -2, 1],
        [-2, 0, -2, 0],
        [0, -2, -2, 0],
        ]
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
