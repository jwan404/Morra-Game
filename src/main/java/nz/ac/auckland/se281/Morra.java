package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Difficulty;

public class Morra {

  private float cumulativeHumanFingers = 0;
  private String playerName;
  private Difficulty difficulty;
  private int roundCount = 0;
  private Strategy strategy;

  public Morra() {}

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);
    playerName = options[0];
    this.difficulty = difficulty;
  }

  public void play() {
    MessageCli.START_ROUND.printMessage(Integer.toString(roundCount + 1));
    MessageCli.ASK_INPUT.printMessage();

    boolean inputValid = false;

    while (!inputValid) {
      String input = Utils.scanner.nextLine();
      String fingerString = input.split(" ")[0]; // finger
      int finger = Integer.parseInt(fingerString);
      String sum = input.split(" ")[1]; // sum
      int sumInt = Integer.parseInt(sum);
      if ((!Utils.isInteger(sum)) || (!Utils.isInteger(fingerString))) {
        MessageCli.INVALID_INPUT.printMessage();
        MessageCli.ASK_INPUT.printMessage();
        return;
      }

      if ((finger >= 1 && finger <= 5) && (sumInt >= 1 && sumInt <= 10)) {
        roundCount++;
        int currentFinger = finger;
        this.cumulativeHumanFingers += finger; // add finger cumulativeHumanFingers
        float average =
            (this.cumulativeHumanFingers - currentFinger)
                / (roundCount - 1); // gets average of human fingers
        int averageInt = Math.round(average); // rounds average to nearest integer\
        fingerString = Integer.toString(finger);
        MessageCli.PRINT_INFO_HAND.printMessage(playerName, fingerString, sum);
        strategy = new RandomStrategy();
        String aiFingers = strategy.getFingersStrat();
        int aiFingersInt = Integer.parseInt(aiFingers);
        if (roundCount > 3) {
          System.out.println(this.cumulativeHumanFingers);
          System.out.println(roundCount - 1);
          System.out.println("Average: " + averageInt);
          System.out.println(average);

          if (this.difficulty == Difficulty.MEDIUM) {
            AverageStrategy strategy = new AverageStrategy();
            String aiSum = strategy.getSumStrat(aiFingersInt, averageInt);
            int aiSumInt = Integer.parseInt(aiSum);
            MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", aiFingers, aiSum);
            if (finger + aiFingersInt == sumInt) {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
            } else if (finger + aiFingersInt == aiSumInt) {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
            } else {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
            }
          } /*  else if (this.difficulty == Difficulty.HARD) {
              TopStrategy strategy = new TopStrategy();
            } else if (this.difficulty == Difficulty.MASTER) {
              AverageStrategy strategy = new AverageStrategy();
              TopStrategy strategy2 = new TopStrategy();
            }*/
        } else {
          RandomStrategy strategy = new RandomStrategy();
          String aiSum = strategy.getSumStrat(Integer.parseInt(aiFingers), averageInt);
          int aiSumInt = Integer.parseInt(aiSum);
          MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", aiFingers, aiSum);
          if (finger + aiFingersInt == sumInt) {
            MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
          } else if (finger + aiFingersInt == aiSumInt) {
            MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
          } else {
            MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
          }
        }

        // Prints out the player's name, fingers and sum
        // Prints out the AI's fingers and sum
        // Prints results of the round

        inputValid = true;
      } else {
        MessageCli.INVALID_INPUT.printMessage();
        MessageCli.ASK_INPUT.printMessage();
      }
    }
  }

  public void showStats() {}
}
