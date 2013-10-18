package org.mdavi.neuralnetwork.hopfield

import org.junit.Ignore;

import spock.lang.Specification;

class CoachTest extends Specification
{
  
  def coach
  
  def setup () {
    coach = new Coach()
  }
  
  def "Coach input" () {
    when: "The coach takes a ${NetworkSize.SIZE} input array as training sequence"
      coach.computeTrainingSequence( [0,1,0,1] )
    then: "it converts 0s to -1s"
      coach.trainingSequence == [-1,1,-1,1]
  }
  
  def "Coach output" () {
    when: "The coach takes a ${NetworkSize.SIZE} input array as training sequence and is asked to elaborate such sequence"
      coach.computeTrainingSequence( [0,1,0,1] )
    then: "it produces the weights of the connections"
      coach.obtainWeights() == [[0, -1, 1, -1], [-1, 0, -1, 1],[1, -1, 0, -1],[-1, 1, -1, 0]]
  }
  
}
