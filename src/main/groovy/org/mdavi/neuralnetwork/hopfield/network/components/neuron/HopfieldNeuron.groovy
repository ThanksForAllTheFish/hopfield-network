package org.mdavi.neuralnetwork.hopfield.network.components.neuron

class HopfieldNeuron implements Neuron
{
  private def weights = []
  
  HopfieldNeuron(weights) {
    this.weights = weights
  }
  
  @Override
  def activateFor (pattern) {
    int activation = 0
    
    weights.eachWithIndex {
      connectionWeight, i ->
        activation += pattern[i]*connectionWeight
    }
    
    return activation
  }
}
