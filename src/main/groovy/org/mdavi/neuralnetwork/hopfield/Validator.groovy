package org.mdavi.neuralnetwork.hopfield

import java.awt.TexturePaintContext.Int;

import org.mdavi.neuralnetwork.hopfield.network.HopfieldNetwork;

class Validator
{

  HopfieldNetwork network
  
  Validator(network, trainings) {
    this.network = network
    trainings.each {
      training ->
      this.network.train(training)
    }
  }
  
  def computePattern = {
    pattern ->
    network.recognize(pattern) == pattern
  }
}
