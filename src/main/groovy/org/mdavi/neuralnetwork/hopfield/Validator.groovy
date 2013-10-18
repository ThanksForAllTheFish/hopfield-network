package org.mdavi.neuralnetwork.hopfield

class Validator
{

  HopfieldNetwork network
  
  Validator(training, network) {
    this.network = network
    this.network.train(training)
  }
  
  def computePattern = {
    pattern ->
    network.recognize(pattern) == pattern
  }
}
