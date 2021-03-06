package org.mdavi.neuralnetwork.hopfield.coach

import org.junit.Ignore;
import org.mdavi.neuralnetwork.hopfield.network.NetworkSize;

import spock.lang.Specification;

class CoachTest extends Specification
{
  
  def coach
  
  def setup () {
    coach = new HopfieldCoachCreator().newCoach()
  }
  
  def "HopfieldCoach input" () {
    when: "The coach takes a ${NetworkSize.SIZE} input array as training sequence"
      coach.computeTrainingSequence( [0,1,0,1] )
    then: "it converts 0s to -1s"
      coach.trainingSequence == [-1,1,-1,1]
  }
  
  def "HopfieldCoach output" () {
    when: "The coach takes a ${NetworkSize.SIZE} input array as training sequence and is asked to elaborate such sequence"
      coach.computeTrainingSequence( training )
    then: "it produces the weights of the connections"
      coach.obtainWeights([[0, 0, 0, 0], [0, 0, 0, 0],[0, 0, 0, 0],[0, 0, 0, 0]]) == weights
    where:
      training << [[0,1,0,1], 
        [1,0,0,1]]
      weights << [[[0, -1, 1, -1], [-1, 0, -1, 1],[1, -1, 0, -1],[-1, 1, -1, 0]],
        [[0, -1, -1, 1], [-1, 0, 1, -1],[-1, 1, 0, -1],[1, -1, -1, 0]]]
  }
  
  def "HopfieldCoach output for multiple training" () {
    when: "The coach takes a ${NetworkSize.SIZE} input array as training sequence and is asked to elaborate such sequence"
      coach.computeTrainingSequence( [0,1,0,1] )
      def weights = coach.obtainWeights([[0, 0, 0, 0], [0, 0, 0, 0],[0, 0, 0, 0],[0, 0, 0, 0]])
      coach.computeTrainingSequence( [1,0,0,1] )
      weights = coach.obtainWeights(weights)
    then: "it produces the weights of the connections"
      weights == [[0, -2, 0, 0], [-2, 0, 0, 0],[0, 0, 0, -2],[0, 0, -2, 0]]
  }
  
}
