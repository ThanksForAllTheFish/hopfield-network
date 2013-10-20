package org.mdavi.neuralnetwork.hopfield.network.components.layer

class HopfieldLayerCreator implements LayerCreator
{
  def neuronCreator
  
  HopfieldLayerCreator(neuronCreator) {
    this.neuronCreator = neuronCreator
  }

  @Override
  def newLayer ()
  {
    new HopfieldLayer(neuronCreator)
  }

}
