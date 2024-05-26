package allies;

import Other.DamageType;
import Other.GameManager;
import java.util.Scanner;

public class Richarch implements Ally {

  private final int CashPerDay = 10000000;
  private int cashOnHand;
  private GameManager manager;

  public Richarch(GameManager manager) {
    cashOnHand = 3000000;
    this.manager = manager;
  }

  public void startTurn() {
    System.out.println("It is now Richarch's Turn! You have $" + cashOnHand + " cash on hand. You"
        + " can do the following:");
    System.out.println(
        "[1] Pay raise: remove money from Richarch’s pocket and add 75% of it to an ally. For each "
            + "Financial Advisor ally in play, add 15% to that. (note: this can make it go over "
            + "100%) ");
    System.out.println("[2] Summon ally: Richarch calls an ally and sends them to the front of his "
        + "driveway. The ally will haggle a random price between 1 and 2 million. You can choose to"
        + " reroll this once, plus 1 more time per Financial Advisor in play. ");
    System.out.println("[3] [move 3] ");
    System.out.println("[4] [move 4] ");
    System.out.println("[5] Check Ally's: Gives you the current list of allies and their current "
        + "cash (This does not use the turn)");
    System.out.print("To select a move type the number: ");
    Scanner myObj = new Scanner(System.in);
    String userAnswer = myObj.nextLine();
    actions(Integer.parseInt(userAnswer));
    System.out.println();
  }

  @Override
  public void actions(int actionNumber) {
    switch (actionNumber) {
      case 1:
        System.out.println("Which ally? : ");
        manager.printParty();
        Scanner myObj = new Scanner(System.in);
        String userAnswer = myObj.nextLine();
        Ally currentAlly = manager.getAllyAt(Integer.parseInt(userAnswer));
        System.out.println("How much money? : ");
        userAnswer = myObj.nextLine();
        currentAlly.adjustMoney(Integer.parseInt(userAnswer));
    }
    System.out.println("User chose: " + actionNumber);
  }

  @Override
  public void adjustMoney(int adjustBy) {
    cashOnHand += adjustBy;
    if (cashOnHand <= 0) {
      throw new IllegalStateException("Game Over! How in the world did you lose?!");
    }
  }

  /**
   * Richarch does not deal damage
   *
   * @return null
   */
  @Override
  public DamageType getDamageType() {
    return DamageType.None;
  }

  @Override
  public AllyClass getAllyType() {
    return AllyClass.Richarch;
  }
}
