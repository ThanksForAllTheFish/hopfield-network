package org.mdavi.neuralnetwork.hopfield.coach

class HopfieldCoachCreator implements CoachCreator
{

  def newCoach () {
    new HopfieldCoach()
  }
}
