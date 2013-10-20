package org.mdavi.neuralnetwork.hopfield.network.components.neuron

class HopfieldNeuron implements Neuron
{
  @Override
  def activateFor (pattern, weights) {
    int activation = 0
    
    weights.eachWithIndex {
      connectionWeight, i ->
        activation += pattern[i]*connectionWeight
    }
    
    return activation
  }
}
