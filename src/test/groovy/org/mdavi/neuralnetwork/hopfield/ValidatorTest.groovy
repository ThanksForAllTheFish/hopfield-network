package org.mdavi.neuralnetwork.hopfield

import spock.lang.Specification;

class ValidatorTest extends Specification
{

  def "Validator description" () {
    when: "The validator takes a training pattern, and a Hopfield network"
      Validator validator = new Validator(training, new HopfieldNetwork())
    then: "it tells whether the network can recognize an input pattern"
      validator.computePattern(pattern) == expectedResult
    where:
      pattern << [[0, 1, 0, 1], [1, 0, 1, 0], [1, 1, 1, 1]]
      training << [[0, 1, 0, 1], [0, 1, 0, 1], [0, 1, 0, 1]]
      expectedResult << [true, true, false]
  }
}
