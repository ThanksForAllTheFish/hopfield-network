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
    int[][] weights = new int[NetworkSize.SIZE][NetworkSize.SIZE]
      
    for(row in 0..<NetworkSize.SIZE)
      for(col in 0..<NetworkSize.SIZE)
        weights[row][col] = row == col? 0 : trainingSequence[row] * trainingSequence[col]
    return weights    
  }
}
