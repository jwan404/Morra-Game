package nz.ac.auckland.se281;

public class Finger implements FingerInterface {
  private int finger;
  private int fingerSum;
  private int sumInt;

  public Finger(int sumInt) {
    this.sumInt = sumInt;
  }

  public int getSumInt() {
    return sumInt;
  }

  public Finger(int finger, int sumInt) {
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
