package org.mdavi.neuralnetwork.hopfield

class Neuron
{
  private def weights = []
  
  Neuron (weights) {
    this.weights = weights
  }
  
  def activateFor = {
    pattern ->
    int activation = 0
    
    weights.eachWithIndex {
      connectionWeight, i ->
      if( pattern[i] )
        activation += connectionWeight
      
    }
    
    return activation
  }
}
