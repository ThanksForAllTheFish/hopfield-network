package org.mdavi.neuralnetwork.hopfield

class HopfieldNetwork
{
  def weights = new int[NetworkSize.SIZE][NetworkSize.SIZE]

  HopfieldNetwork() {
    for(row in 0..<NetworkSize.SIZE)
      for(column in 0..<NetworkSize.SIZE)
        weights[row][column] = 0
  }
  
  def train = {
    pattern ->
    Coach coach = new Coach()
    coach.computeTrainingSequence(pattern)
    weights = coach.obtainWeights(weights)
  }
  
  def recognize = {
    pattern ->
    Layer layer = new Layer(weights)
    layer.activateFor(pattern)
  }
}
