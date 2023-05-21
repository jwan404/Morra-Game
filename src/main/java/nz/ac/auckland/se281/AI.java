package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Difficulty;

public abstract class AI { // use factory

  private Strategy strategy;

  /* public AI(Strategy strategy) {
    this.strategy = strategy;
  }  */

  /*public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }  */

  public Difficulty getDifficulty() {
    return getDifficulty();
  }

  public String getFingers() {
    return strategy.getFingersStrat();
  }

  public String getSum() {
    return strategy.getSumStrat(Integer.parseInt(getFingers()));
  }
}
