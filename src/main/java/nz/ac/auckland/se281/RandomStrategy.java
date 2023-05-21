package nz.ac.auckland.se281;

public class RandomStrategy implements Strategy {

  @Override
  public String getFingersStrat() {
    int fingers = Utils.getRandomNumber(1, 5);
    String fingerString = Integer.toString(fingers);
    return fingerString;
  }

  @Override
  public String getSumStrat(int fingerInt, int average, int mostCommon) {
    int sum = Utils.getRandomNumber(fingerInt + 1, fingerInt + 5);
    String sumString = Integer.toString(sum);
    return sumString;
  }
}
