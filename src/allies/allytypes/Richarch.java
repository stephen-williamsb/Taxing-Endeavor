package allies.allytypes;

import static gameManagement.GameManager.getUserInWithQuit;

import allies.Ally;
import allies.AllyClass;
import allies.actions.Action;
import allies.actions.PayRaise;
import gameManagement.Billion;
import gameManagement.DamageType;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Richarch implements Ally {

  private final GameManager manager;
  Scanner scanner;
  private final Billion cashOnHand;
  private List<Action> actions;

  public Richarch(Billion startCash, GameManager manager) {
    cashOnHand = startCash;
    this.manager = manager;
    scanner = new Scanner(System.in);
    actions = new ArrayList<>();
    actions.add(new PayRaise());
  }

  @Override
  public void intro() {
    System.out.println("It is now Richarch's Turn! You have " + cashOnHand + " cash on hand. You"
        + " can do the following:");
    for (int i = 0; i < actions.size(); i++) {
      System.out.println("[" + i + "]: " + actions.get(i).getName());
    }
    System.out.println("To select a move type the name of the move: ");
  }

  @Override
  public void actions(String actionName) throws MoveQuitOrFailed {
    for (Action action : actions) {
      if (action.getName().equalsIgnoreCase(actionName)) {
        action.act(manager, this);
        action.getFlavorText(this);
        return;
      }
    }
    throw new MoveQuitOrFailed(
        "There is no action that has the name " + actionName);

  }

  @Override
  public void adjustMoney(Billion adjustBy) {
    cashOnHand.add(adjustBy);
    if (cashOnHand.getCash() <= 0) {
      throw new IllegalStateException("Game Over! How in the world did you lose?!");
    }
  }

  @Override
  public Billion currentMoney() {
    return cashOnHand;
  }

  /**
   * Richarch does not deal damage
   *
   * @return DamageType.None
   */
  @Override
  public DamageType getDamageType() {
    return DamageType.None;
  }

  @Override
  public AllyClass getAllyType() {
    return AllyClass.Richarch;
  }


  private void checkForQuit(String input) throws MoveQuitOrFailed {

  }

  //Move set

  private void handlePayRaise() throws MoveQuitOrFailed {

  }


  private void handleSummonAlly() throws MoveQuitOrFailed {
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
          summonedAlly + " wants to be hired for $" + hireCost + ". You have " + rerolls
              + " rerolls left, type 'reroll' to reroll. Type 'accept' to hire.");
      userAnswer = scanner.nextLine();
      if (userAnswer.equalsIgnoreCase("accept")) {
        break;
      }
      rerolls--;
    }
    if (cashOnHand.getCash() - hireCost < 0) {
      System.out.println("insufficient payment");
      return;
    }
    cashOnHand.sub(hireCost);
    manager.createAlly(summonedAlly, new Billion(hireCost));
  }
}
