package nz.ac.auckland.se281;

public class AverageStrategy implements Strategy {

  @Override
  public String getFingersStrat() {
    int fingerInt = Utils.getRandomNumber(1, 5);
    return Integer.toString(fingerInt);
  }

  @Override
  public String getSumStrat(
      int fingerInt, int average, int mostCommon) { // fingerInt is from getFingersStrat.
    int sumInt = fingerInt + average;
    return Integer.toString(sumInt);
  }
}
