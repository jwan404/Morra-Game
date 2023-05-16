package nz.ac.auckland.se281;

public class RandomStrategy implements Strategy {

  /*This strategy selects both the number of fingers and the sum randomly.
  The Random strategy chooses the number of fingers to play randomly between 1 and 5.
  Keep in mind that this strategy will never win if it selects a sum that is equal to or smaller than its fingers.
  For instance: fingers=4 sum=3 In this case, it is impossible for the AI to win because the sum of the AI and player hands cannot be smaller than the AI’s fingers. In other words, sum > fingers.
  Additionally, the AI cannot win if it selects a sum greater than fingers + 5, where 5 is the maximum number of fingers the human player can choose.
  As a result, the Random strategy will choose a sum randomly between fingers + 1 and fingers + 5 (inclusive). */

  @Override
  public int getFingers() {
    return Utils.getRandomNumber(1, 5);
  }

  @Override
  public int getSum() {
    return Utils.getRandomNumber(getFingers() + 1, getFingers() + 5);
  }
}
