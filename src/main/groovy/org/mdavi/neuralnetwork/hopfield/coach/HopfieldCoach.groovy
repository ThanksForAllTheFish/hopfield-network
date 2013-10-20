package org.mdavi.neuralnetwork.hopfield.coach

import org.mdavi.neuralnetwork.hopfield.network.NetworkSize;

class HopfieldCoach implements Coach
{
  def trainingSequence = []
  
  @Override
  def computeTrainingSequence (trainingSequence) {
    trainingSequence.eachWithIndex {
      value, index ->
      this.trainingSequence[index] = value?: -1
    }
  }
  
  @Override
  def obtainWeights (currentWeights) {
    int[][] weights = new int[NetworkSize.SIZE][NetworkSize.SIZE]
      
    for(row in 0..<NetworkSize.SIZE)
      for(col in 0..<NetworkSize.SIZE)
        weights[row][col] = (row != col).compareTo(false) *( trainingSequence[row] * trainingSequence[col] + currentWeights[row][col] )
    return weights    
  }
}
