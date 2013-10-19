package org.mdavi.neuralnetwork.hopfield

import spock.lang.Specification

class HopfieldNetworkTest extends Specification
{
  HopfieldNetwork network
  def setup () {
    network = new HopfieldNetwork()
  }
  
  def "Hopfield nework training" () {
    when: "The hopfield network is trained to recognize a pattern"
      network.train(pattern)
    then: "it changes the connection weights accordingly"
      network.weights == expectedWeights
    where:
      pattern << [[0,1,0,1]]
      expectedWeights << [
        [[0, -1, 1, -1], [-1, 0, -1, 1],[1, -1, 0, -1],[-1, 1, -1, 0]]
        ]
  }
  
  def "Hopfield nework recognition" () {
    when: "The hopfield network is trained to recognize a pattern"
      network.train(pattern)
    then: "can recognize that pattern and the inverse one"
      network.recognize(pattern) == pattern
    where:
      pattern << [[0,1,0,1], [1,0,1,0]]
  }
  
  def "Hopfield nework multipattern recognition" () {
    when: "The hopfield network is trained to recognize a pattern"
      network.train(pattern[0])
      network.train(pattern[1])
    then: "can recognize that pattern and the inverse one"
      def output = network.recognize(input)
      output == input
    where:
      pattern << [
        [[0,1,0,1], [1,0,0,1]],
        [[0,1,0,1], [1,0,0,1]]]
      input << [[0,1,1,0], [1,0,1,0]]
      
  }
}
