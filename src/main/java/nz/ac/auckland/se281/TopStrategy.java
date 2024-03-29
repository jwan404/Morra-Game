package nz.ac.auckland.se281;

public class TopStrategy implements Strategy {

  @Override
  public String getFingersStrat() {
    int fingers = Utils.getRandomNumber(1, 5);
    String fingerString = Integer.toString(fingers);
    return fingerString;
  }

  @Override
  public String getSumStrat(int fingerInt, int average, int mostCommon) {
    int sumInt = fingerInt + mostCommon;
    return Integer.toString(sumInt);
  }
}
