package org.mdavi.neuralnetwork.hopfield.network.components.layer

import org.mdavi.neuralnetwork.hopfield.network.NetworkSize;
import org.mdavi.neuralnetwork.hopfield.network.components.neuron.HopfieldNeuron;

class HopfieldLayer implements Layer
{

  def neurons
  
  HopfieldLayer(neuronCreator) {
    neurons = []
    for(index in 0..<NetworkSize.SIZE)
      neurons[index] = neuronCreator.newNeuron();
  }
  
  @Override
  def activateFor (pattern, weigths) {
    
    def output = []
    
    neurons.eachWithIndex {
      neuron, index ->
      def activation = neuron.activateFor(pattern, weigths[index])
      output[index] = threshold(activation)
    }
    
    return output
  }
  
  private def threshold = {
    activation ->
    def out = tanh(activation) >= 0
    out.compareTo(false)
  }
  
  private def tanh = {
    x ->
    def a = Math.exp(x)
    def b = Math.exp(-x)
    (a-b)/(a+b)
  }
  
}
