package org.mdavi.neuralnetwork.hopfield.network.components.neuron

class HopfieldNeuronCreator implements NeuronCreator
{

  @Override
  def newNeuron ()
  {
    new HopfieldNeuron()
  }

}
