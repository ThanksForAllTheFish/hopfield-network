package org.mdavi.neuralnetwork.hopfield.network.components.neuron

class HopfieldNeuronCreator implements NeuronCreator
{

  @Override
  def newNeuron (weights)
  {
    new HopfieldNeuron(weights)
  }

}
