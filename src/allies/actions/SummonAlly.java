package allies.actions;

import static gameManagement.GameManager.getUserInWithQuit;

import allies.Ally;
import allies.AllyClass;
import gameManagement.Billion;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;
import java.util.Random;
import java.util.Scanner;

public class SummonAlly implements Action {

  Scanner scanner = new Scanner(System.in);
  AllyClass ally = AllyClass.Richarch;

  @Override
  public String getName() {
    return "Summon Ally";
  }

  @Override
  public String getFlavorText(Ally user) {
    return user.getAllyType() + " hired a new helping hand! " + ally + " has 'willingly' joined "
        + "the team";
  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {
    if (manager.allyCount() == 4) {
      System.out.println("Party is full!");

      return;
    }
    Random rand = new Random();
    String userAnswer = "";

    //Calculate rerolls
    int rerolls = 1 + manager.countFinancialAdvisors();
    AllyClass summonedAlly = null;
    while (summonedAlly == null) {
      System.out.println("""
          Input the number of the ally you would like to summon.
           [1]Son
           [2]Lawyer
           [3]Secretary
           [4]Advisor
           \s""");
      userAnswer = getUserInWithQuit();

      summonedAlly = switch (Integer.parseInt(userAnswer)) {
        case 1 -> AllyClass.Son;
        case 2 -> AllyClass.Lawyer;
        case 3 -> AllyClass.Secretary;
        case 4 -> AllyClass.Advisor;
        default -> null;
      };
    }

    //Handle cost
    double hireCost = 0;
    while (rerolls >= 0) {
      hireCost = (rand.nextInt(10) + 10.0) / 10.0;
      System.out.println(
          summonedAlly + " wants to be hired for $" + hireCost + " Billion. You have " + rerolls
              + " rerolls left, type 'reroll' to reroll. Type 'accept' to hire.");
      userAnswer = scanner.nextLine();
      if (userAnswer.equalsIgnoreCase("accept")) {
        break;
      }
      rerolls--;
    }
    if (self.currentMoney().getCash() - hireCost < 0) {
      System.out.println("insufficient payment");
      return;
    }
    self.currentMoney().sub(hireCost);
    manager.createAlly(summonedAlly, new Billion(hireCost));
    ally = summonedAlly;
  }
}
