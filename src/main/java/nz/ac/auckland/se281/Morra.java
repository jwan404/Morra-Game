package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.Difficulty;

public class Morra {

  private ArrayList<Finger> fingerHuman = new ArrayList<Finger>();
  private float cumulativeHumanFingers = 0;
  private String playerName;
  private Difficulty difficulty;
  private int roundCount = 0;
  private Strategy strategy;
  private int pointsToWin;
  private int count1 = 0;
  private int count2 = 0;
  private int count3 = 0;
  private int count4 = 0;
  private int count5 = 0;
  private int mostCommon = 0;
  private int humanWin = 0;
  private int aiWin = 0;
  private int count11 = 0;
  private int count22 = 0;
  private int count33 = 0;
  private int count44 = 0;
  private int count55 = 0;

  public Morra() {}

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) { // starts new game
    fingerHuman.clear();
    roundCount = 0;
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);
    playerName = options[0];
    this.difficulty = difficulty;
    this.pointsToWin = pointsToWin;
  }

  public void play() { // plays the game
    if (playerName == null) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    MessageCli.START_ROUND.printMessage(Integer.toString(roundCount + 1));
    MessageCli.ASK_INPUT.printMessage();

    boolean inputValid = true;

    while (inputValid) {
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
        fingerHuman.add(new Finger(finger, sumInt));
        if (finger == 1) {
          count1++;
        }
        if (finger == 2) {
          count2++;
        }
        if (finger == 3) {
          count3++;
        }
        if (finger == 4) {
          count4++;
        }
        if (finger == 5) {
          count5++;
        }
        int currentFinger = finger; // current finger
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
          int lastFinger = fingerHuman.get(fingerHuman.size() - 1).getFinger();
          if (lastFinger == 1) {
            count11 = count1 - 1;
            count22 = count2;
            count33 = count3;
            count44 = count4;
            count55 = count5;
          } else if (lastFinger == 2) {
            count22 = count2 - 1;
            count11 = count1;
            count33 = count3;
            count44 = count4;
            count55 = count5;
          } else if (lastFinger == 3) {
            count33 = count3 - 1;
            count11 = count1;
            count22 = count2;
            count44 = count4;
            count55 = count5;
          } else if (lastFinger == 4) {
            count44 = count4 - 1;
            count11 = count1;
            count22 = count2;
            count33 = count3;
            count55 = count5;
          } else if (lastFinger == 5) {
            count55 = count5 - 1;
            count11 = count1;
            count22 = count2;
            count33 = count3;
            count44 = count4;
          }
          int[] numbers = {count11, count22, count33, count44, count55};

          int maxnumber = numbers[0];
          for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maxnumber) {
              maxnumber = numbers[i];
            }
          }
          if (count11 == maxnumber) {
            mostCommon = 1;
          } else if (count22 == maxnumber) {
            mostCommon = 2;
          } else if (count33 == maxnumber) {
            mostCommon = 3;
          } else if (count44 == maxnumber) {
            mostCommon = 4;
          } else if (count55 == maxnumber) {
            mostCommon = 5;
          }
          if (this.difficulty == Difficulty.EASY) {
            RandomStrategy strategy = new RandomStrategy();
            String aiSum = strategy.getSumStrat(aiFingersInt, averageInt, mostCommon);
            int aiSumInt = Integer.parseInt(aiSum);
            MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", aiFingers, aiSum);
            if ((finger + aiFingersInt == sumInt) && (finger + aiFingersInt == aiSumInt)) {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
            } else if (finger + aiFingersInt == sumInt) {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
              humanWin++;
            } else if (finger + aiFingersInt == aiSumInt) {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
              aiWin++;
            } else {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
            }
          } else if (this.difficulty == Difficulty.MEDIUM) {
            AverageStrategy strategy = new AverageStrategy();
            String aiSum = strategy.getSumStrat(aiFingersInt, averageInt, mostCommon);
            int aiSumInt = Integer.parseInt(aiSum);
            MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", aiFingers, aiSum);
            if ((finger + aiFingersInt == sumInt) && (finger + aiFingersInt == aiSumInt)) {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
            } else if (finger + aiFingersInt == sumInt) {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
              humanWin++;
            } else if (finger + aiFingersInt == aiSumInt) {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
              aiWin++;
            } else {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
            }
          } else if (this.difficulty == Difficulty.HARD) {
            TopStrategy strategy = new TopStrategy();
            String aiSum = strategy.getSumStrat(aiFingersInt, averageInt, mostCommon);
            int aiSumInt = Integer.parseInt(aiSum);
            MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", aiFingers, aiSum);
            if ((finger + aiFingersInt == sumInt) && (finger + aiFingersInt == aiSumInt)) {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
            } else if (finger + aiFingersInt == sumInt) {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
              humanWin++;
            } else if (finger + aiFingersInt == aiSumInt) {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
              aiWin++;
            } else {
              MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
            }
          } else if (this.difficulty == Difficulty.MASTER) {
            if (roundCount % 2 == 0) {
              AverageStrategy strategy = new AverageStrategy();
              String aiSum = strategy.getSumStrat(aiFingersInt, averageInt, mostCommon);
              int aiSumInt = Integer.parseInt(aiSum);
              MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", aiFingers, aiSum);
              if ((finger + aiFingersInt == sumInt) && (finger + aiFingersInt == aiSumInt)) {
                MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
              } else if (finger + aiFingersInt == sumInt) {
                MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
                humanWin++;
              } else if (finger + aiFingersInt == aiSumInt) {
                MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
                aiWin++;
              } else {
                MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
              }
            }
            if (roundCount % 2 != 0) {
              TopStrategy strategy = new TopStrategy();
              String aiSum = strategy.getSumStrat(aiFingersInt, averageInt, mostCommon);
              int aiSumInt = Integer.parseInt(aiSum);
              MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", aiFingers, aiSum);
              if ((finger + aiFingersInt == sumInt) && (finger + aiFingersInt == aiSumInt)) {
                MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
              } else if (finger + aiFingersInt == sumInt) {
                MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
                humanWin++;
              } else if (finger + aiFingersInt == aiSumInt) {
                MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
                aiWin++;
              } else {
                MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
              }
            }
          }
        } else {
          RandomStrategy strategy = new RandomStrategy();
          String aiSum = strategy.getSumStrat(Integer.parseInt(aiFingers), averageInt, mostCommon);
          int aiSumInt = Integer.parseInt(aiSum);
          MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", aiFingers, aiSum);
          if ((finger + aiFingersInt == sumInt) && (finger + aiFingersInt == aiSumInt)) {
            MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
          } else if (finger + aiFingersInt == sumInt) {
            MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
            humanWin++;
          } else if (finger + aiFingersInt == aiSumInt) {
            MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
            aiWin++;
          } else {
            MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
          }
        }

        inputValid = false;
      } else {
        MessageCli.INVALID_INPUT.printMessage();
        MessageCli.ASK_INPUT.printMessage();
      }
      if (humanWin == this.pointsToWin) {
        MessageCli.END_GAME.printMessage(playerName, Integer.toString(roundCount));
        playerName = null;
        difficulty = null;
        humanWin = 0;
        return;
      } else if (aiWin == this.pointsToWin) {
        MessageCli.END_GAME.printMessage("Jarvis", Integer.toString(roundCount));
        playerName = null;
        difficulty = null;
        aiWin = 0;
        return;
      }
    }
  }

  public void showStats() { // prints out the stats
    if (playerName == null) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    int roundsLeftHuman = pointsToWin - humanWin;
    int roundsLeftAi = pointsToWin - aiWin;
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        playerName, Integer.toString(humanWin), Integer.toString(roundsLeftHuman));
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        "Jarvis", Integer.toString(aiWin), Integer.toString(roundsLeftAi));
  }
}
