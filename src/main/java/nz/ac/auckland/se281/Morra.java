package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.Difficulty;

public class Morra {

  private ArrayList<Finger> fingerCount = new ArrayList<Finger>();
  private ArrayList<Player> players = new ArrayList<Player>();
  
  private int roundCount = 0;
  private Strategy strategy;

  public Morra() {}

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);
    players.add(new Player(options[0]));
    if (difficulty == Difficulty.EASY) {
      strategy = new RandomStrategy();
    }
    else if (difficulty == Difficulty.MEDIUM) {
      if (roundCount >= 3) {
        strategy = new RandomStrategy();
      }
      else {
        strategy = new AverageStrategy();
        
      }

    }
   /*  else if (difficulty == Difficulty.HARD) {
      strategy = new RandomStrategy();
    }
    else if (difficulty == Difficulty.MASTER) {
      strategy = new RandomStrategy(); 
    }*/

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
        fingerString = Integer.toString(finger);
        fingerCount.add(new Finger(finger)); // add finger to arraylist
        roundCount++;
        
        // Prints out the player's name, fingers and sum
        MessageCli.PRINT_INFO_HAND.printMessage(players.get(0).getPlayerName(), fingerString, sum);
        // Prints out the AI's fingers and sum
        String aiFingers = strategy.getFingers();
        String aiSum = strategy.getSum(Integer.parseInt(aiFingers));
        MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", aiFingers, aiSum);
        int aiFingersInt = Integer.parseInt(aiFingers);
        int aiSumInt = Integer.parseInt(aiSum);
        // Prints results of the round
        if (finger + aiFingersInt == sumInt) {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
        }
        else if (finger + aiFingersInt == aiSumInt) {
          MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
        }
        else {
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
