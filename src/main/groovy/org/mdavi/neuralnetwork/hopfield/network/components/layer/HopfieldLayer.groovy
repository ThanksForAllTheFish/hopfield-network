package org.mdavi.neuralnetwork.hopfield.network.components.layer

import org.mdavi.neuralnetwork.hopfield.network.components.neuron.HopfieldNeuron;

class HopfieldLayer implements Layer
{

  def weights
  def neurons
  
  HopfieldLayer(neuronCreator, weights) {
    this.weights = weights
    neurons = []
    this.weights.eachWithIndex {
      weight, index ->
      neurons[index] = neuronCreator.newNeuron(weight);
    }
  }
  
  @Override
  def activateFor (pattern) {
    
    def output = []
    
    neurons.eachWithIndex {
      neuron, index ->
      def activation = neuron.activateFor(pattern)
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
