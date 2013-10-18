package org.mdavi.neuralnetwork.hopfield

class HopfieldNetwork
{
  def weights = []

  HopfieldNetwork() {
  }
  
  def train = {
    pattern ->
    Coach coach = new Coach()
    coach.computeTrainingSequence(pattern)
    weights = coach.obtainWeights()
  }
  
  def recognize = {
    pattern ->
    Layer layer = new Layer(weights)
    layer.activateFor(pattern)
  }
}
