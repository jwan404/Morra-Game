package nz.ac.auckland.se281;

public class AverageStrategy implements Strategy {

  private int roundCount;

  public AverageStrategy() {
    roundCount = 0;
  }

  @Override
  public String getFingersStrat() {
    int fingerInt = Utils.getRandomNumber(1, 5);
    return Integer.toString(fingerInt);
  }

  @Override
  public String getSumStrat(int fingerInt) { // fingerInt is from getFingersStrat.
    int sumInt = fingerInt;
    return Integer.toString(sumInt);
  }
}
