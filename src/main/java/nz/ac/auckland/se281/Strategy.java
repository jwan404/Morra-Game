package nz.ac.auckland.se281;

public interface Strategy {

  public String getFingersStrat();

  public String getSumStrat(int fingerInt, int average, int mostCommon);
}
