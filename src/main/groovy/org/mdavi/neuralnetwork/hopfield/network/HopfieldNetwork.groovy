package org.mdavi.neuralnetwork.hopfield.network

import org.mdavi.neuralnetwork.hopfield.coach.Coach
import org.mdavi.neuralnetwork.hopfield.coach.HopfieldCoach;
import org.mdavi.neuralnetwork.hopfield.network.components.layer.HopfieldLayer;
import org.mdavi.neuralnetwork.hopfield.network.components.layer.Layer

class HopfieldNetwork
{
  def weights
  def coachCreator 
  def layerCreator

  HopfieldNetwork(coachCreator, layerCreator) {
    this.coachCreator = coachCreator
    this.layerCreator = layerCreator
    weights = []
    for(row in 0..<NetworkSize.SIZE) {
      weights[row] = []
      for(column in 0..<NetworkSize.SIZE)
        weights[row][column] = 0
    }
  }
  
  def train = {
    pattern ->
    Coach coach = coachCreator.newCoach()
    coach.computeTrainingSequence(pattern)
    weights = coach.obtainWeights(weights)
  }
  
  def recognize = {
    pattern ->
    Layer layer = layerCreator.newLayer()
    layer.activateFor(pattern, weights)
  }
}
