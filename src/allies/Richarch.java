package allies;

import Other.DamageType;
import Other.GameManager;
import java.util.Random;
import java.util.Scanner;

public class Richarch implements Ally {

  private final int CashPerDay = 10000000;
  private int cashOnHand;
  private final GameManager manager;
  Scanner scanner;

  public Richarch(GameManager manager) {
    cashOnHand = 13000000;
    this.manager = manager;
    scanner = new Scanner(System.in);
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
    String userAnswer = scanner.nextLine();
    System.out.println();
    actions(Integer.parseInt(userAnswer));
  }

  @Override
  public void actions(int actionNumber) {
    switch (actionNumber) {
      case 1:
        handlePayRaise();
        System.out.println();
        break;
      case 2:
        handleSummonAlly();
        System.out.println();
        break;
      case 3:
        System.out.println("User chose: " + actionNumber + "... This action has not been assigned");
        break;
      case 4:
        System.out.println("User chose: " + actionNumber + "... This action has not been assigned");
        break;
      case 5:
        manager.printPartyCheck();
        System.out.println();
        startTurn();
        break;
      default:
        throw new IllegalArgumentException(
            "There is no action that has the number " + actionNumber);
    }

  }

  @Override
  public void adjustMoney(int adjustBy) {
    cashOnHand += adjustBy;
    if (cashOnHand <= 0) {
      throw new IllegalStateException("Game Over! How in the world did you lose?!");
    }
  }

  @Override
  public int currentMoney() {
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

  private int countFinancialAdvisors() {
    Ally[] allies = manager.fetchAllyParty();
    int advisors = 0;
    for (int i = 1; i < allies.length; i++) {
      if (allies[i] == null) {
        continue;
      }
      if (allies[i].getAllyType() == AllyClass.Secretary) {
        advisors++;
      }
    }
    return advisors;
  }

  private void checkForQuit(String input) {
    if (input.toLowerCase().equals("q") || input.toLowerCase().equals("quit")) {
      System.out.println("\nQuitting current action\n");
      startTurn();
    }
  }

  private String getUserIn() {
    String userAnswer = scanner.nextLine();
    checkForQuit(userAnswer);
    return userAnswer;
  }
  //Move set

  private void handlePayRaise() {
    Ally currentAlly = null;
    String userAnswer;
    while (currentAlly == null || currentAlly.getAllyType() == AllyClass.Richarch) {
      System.out.println("Which ally? : ");
      manager.printPartyAsOptions();
      userAnswer = getUserIn();
      currentAlly = manager.getAllyAt(Integer.parseInt(userAnswer));
      if (currentAlly.getAllyType() == AllyClass.Richarch) {
        System.out.println("Cannot choose self");
      }
    }

    System.out.println("How much money? : ");
    userAnswer = getUserIn();
    int amountTransfered = Integer.parseInt(userAnswer);
    while (cashOnHand - amountTransfered < 0 || amountTransfered < 0) {
      System.out.println("Invalid amount. How much money? : ");
      userAnswer = getUserIn();
      amountTransfered = Integer.parseInt(userAnswer);
    }
    cashOnHand -= amountTransfered;
    amountTransfered = (int) (amountTransfered * (.75 + (.15 * countFinancialAdvisors())));
    currentAlly.adjustMoney(amountTransfered);

  }


  private void handleSummonAlly() {
    Random rand = new Random();
    String userAnswer = "";

    //Calculate rerolls
    int rerolls = 1 + countFinancialAdvisors();
    AllyClass summonedAlly = null;
    while (summonedAlly == null) {
      System.out.println("""
          Input the number of the ally you would like to summon.
           [1]Son
          , [2]Lawyer
          , [3]Secretary
          )? :\s""");
      userAnswer = getUserIn();

      summonedAlly = switch (Integer.parseInt(userAnswer)) {
        case 1 -> AllyClass.Son;
        case 2 -> AllyClass.Lawyer;
        case 3 -> AllyClass.Secretary;
        default -> null;
      };
    }
    while (rerolls != 0) {
      int hireCost = rand.nextInt(1000000) + 1000000;
      System.out.println(
          summonedAlly + " wants to be hired for $" + hireCost + ". You have " + rerolls
              + " rerolls left, type 'reroll' to reroll. Type 'accept' to hire.");
      userAnswer = getUserIn();
      if(userAnswer.equalsIgnoreCase("accept")){
        break;
      }
      rerolls--;
    }


  }
}
