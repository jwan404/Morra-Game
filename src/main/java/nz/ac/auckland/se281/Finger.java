package nz.ac.auckland.se281;

public class Finger {
  private int finger;
  private int average;
  private int fingerSum;

  public int getFingerSum() {
    for (int i = 0; i < finger; i++) {
      fingerSum += finger;
    }
    return fingerSum;
  }

  public int getAverage() {
    average = fingerSum / finger;
    return average;
  }

  public int getFinger() {
    return finger;
  }

  public Finger(int finger) {
    this.finger = finger;
  }


}
