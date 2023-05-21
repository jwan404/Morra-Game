package nz.ac.auckland.se281;

public class Finger {
  private int finger;
  private int fingerSum;

  public int getFingerSum() {
    fingerSum += finger;
    return fingerSum;
  }

  public int getFinger() {
    return finger;
  }

  public Finger(int finger) {
    this.finger = finger;
  }
}
