package nz.ac.auckland.se281;

public class Finger implements FingerInterface {
  private int finger;
  private int fingerSum;

  public Finger(int finger) {
    this.finger = finger;
  }

  @Override
  public int getFingerSum() {
    for (int i = 0; i < finger; i++) {
      fingerSum += finger;
    }
    return fingerSum;
  }

  public int getFinger() {
    return finger;
  }
}
