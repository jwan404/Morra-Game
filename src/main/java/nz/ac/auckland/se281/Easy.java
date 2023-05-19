package nz.ac.auckland.se281;

public class Easy extends AI {

  public Easy(Strategy strategy) {
    super(strategy);
    // TODO Auto-generated constructor stub
  }

  @Override
  public String getFingers() {
    RandomStrategy random = new RandomStrategy();
    return random.getFingersStrat();
  }

  @Override
  public String getSum() {
    RandomStrategy random = new RandomStrategy();
    return random.getSumStrat(Integer.parseInt(random.getFingersStrat()));
  }
}
