package org.mdavi.neuralnetwork.hopfield

class Coach
{
  def trainingSequence = []
  
  def computeTrainingSequence = {
    trainingSequence ->
    trainingSequence.eachWithIndex {
      value, index ->
      this.trainingSequence[index] = value?: -1
    }
  }
  
  def obtainWeights = {
    currentWeights ->
    int[][] weights = new int[NetworkSize.SIZE][NetworkSize.SIZE]
      
    for(row in 0..<NetworkSize.SIZE)
      for(col in 0..<NetworkSize.SIZE)
        weights[row][col] = (row != col).compareTo(false) *( trainingSequence[row] * trainingSequence[col] + currentWeights[row][col] )
    return weights    
  }
}
