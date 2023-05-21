package nz.ac.auckland.se281;

import java.util.ArrayList;

public class AverageStrategy implements Strategy {

  @Override
  public String getFingersStrat() {
    int fingerInt = Utils.getRandomNumber(1, 5);
    return Integer.toString(fingerInt);
  }

  @Override
  public String getSumStrat(int fingerInt) { // fingerInt is from getFingersStrat.
    int sumInt = fingerInt /* + average of human fingers */;
    return Integer.toString(sumInt);
  }

  public int getAverage(ArrayList<Finger> humanFingers) {
    double sum = 0;
    for (Finger finger : humanFingers) {
      sum += finger.getFinger();
    }
    double average = sum / humanFingers.size();
    Math.round(average);
    return (int) average;
  }
}
