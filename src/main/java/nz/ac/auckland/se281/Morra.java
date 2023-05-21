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
        this.cumulativeHumanFingers += finger; // add finger cumulativeHumanFingers
        float average = this.cumulativeHumanFingers / roundCount; // gets average of human fingers
        int averageInt = Math.round(average); // rounds average to nearest integer
        String aiFingers = strategy.getFingersStrat();
        int aiFingersInt = Integer.parseInt(aiFingers);
        if (roundCount > 3) {
          if (this.difficulty == Difficulty.MEDIUM) {
            AverageStrategy strategy = new AverageStrategy();
            sum = strategy.getSumStrat(aiFingersInt, averageInt);
          }
        }
        fingerString = Integer.toString(finger);
        roundCount++;
        // Prints out the player's name, fingers and sum
        MessageCli.PRINT_INFO_HAND.printMessage(playerName, fingerString, sum);
        // Prints out the AI's fingers and sum
        String aiSum = strategy.getSumStrat(Integer.parseInt(aiFingers), averageInt);
        MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", aiFingers, aiSum);
        int aiSumInt = Integer.parseInt(aiSum);
        // Prints results of the round
        if (finger + aiFingersInt == sumInt) {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
        } else if (finger + aiFingersInt == aiSumInt) {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
        } else {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
        }

        inputValid = true;
      } else {
        MessageCli.INVALID_INPUT.printMessage();
        MessageCli.ASK_INPUT.printMessage();
      }
    }
  }

  public void showStats() {}
}
