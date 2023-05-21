package nz.ac.auckland.se281;

public class Medium extends AI {
  // first 3 rounds: random
  // after 3 rounds: average
  private int roundCount;

  public Medium() {
    roundCount = 0;
  }

  @Override
  public String getFingers() {
    AverageStrategy average = new AverageStrategy();
    return average.getFingersStrat();
  }

  @Override
  public String getSum() {
    roundCount++;
    if (roundCount > 3) {
      AverageStrategy average = new AverageStrategy();
      return average.getSumStrat(Integer.parseInt(average.getFingersStrat()));
    } else {
      RandomStrategy random = new RandomStrategy();
      return random.getSumStrat(Integer.parseInt(random.getFingersStrat()));
    }
  }
}
