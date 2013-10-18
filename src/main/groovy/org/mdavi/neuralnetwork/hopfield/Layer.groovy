package org.mdavi.neuralnetwork.hopfield

class Layer
{

  def weights
  Neuron[] neurons
  
  Layer(weights) {
    this.weights = weights
    neurons = new Neuron[this.weights.size()]
    this.weights.eachWithIndex {
      weight, index ->
      neurons[index] = new Neuron(weight);
    }
  }
  
  def activateFor = {
    pattern ->
    
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
