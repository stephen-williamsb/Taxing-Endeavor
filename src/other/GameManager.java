package other;

import allies.Advisor;
import allies.Ally;
import allies.AllyClass;
import allies.Lawyer;
import allies.Richarch;
import allies.Secretary;
import allies.Son;
import enemies.DayOneEnemy;
import enemies.Enemy;


public class GameManager {

  private Ally[] allyParty;
  private Enemy currentFoe;
  private int day;
  private final int richarchDailyGains = 10000000;


  public GameManager() {
    day = 1;
    allyParty = new Ally[4];
    allyParty[0] = new Richarch(this);
    StartGame();
  }

  private Enemy GetFoeAtDay(int day) {
    return switch (day) {
      case 1 -> new DayOneEnemy(this);
      case 2 -> null;
      case 3 -> null;
      default -> null;
    };
  }

  public void StartGame() {
    introText();
    allyParty[0].startTurn();
  }

  public Ally getAllyAt(int pos) {
    return allyParty[pos - 1];
  }

  public void createAlly(AllyClass ally, int startCash) {
    Ally allyCreated;
    switch (ally) {
      case Son -> allyCreated = new Son(startCash);
      case Secretary -> allyCreated = new Secretary(startCash);
      case Lawyer -> allyCreated = new Lawyer(startCash);
      case Advisor -> allyCreated = new Advisor(startCash);
      default -> allyCreated = new Richarch(this);
    }

    for (int i = 1; i < allyParty.length; i++) {
      if (allyParty[i] == null) {
        allyParty[i] = allyCreated;
        return;
      }
    }
  }

  public int allyCount() {
    int count = 0;
    for (Ally ally : allyParty) {
      if (ally != null) {
        count++;
      }
    }
    return count;
  }


  public Enemy getCurrentEnemy() {
    return currentFoe;
  }

  public Ally[] fetchAllyParty() {
    return allyParty;
  }

  public void printPartyAsOptions() {
    for (int i = 0; i < allyParty.length; i++) {
      if (allyParty[i] == null) {
        System.out.println("[" + (i + 1) + "]: empty");
        continue;
      }
      System.out.println("[" + (i + 1) + "]: " + allyParty[i].getAllyType());
    }
  }

  public void printPartyCheck() {
    for (int i = 0; i < allyParty.length; i++) {
      if (allyParty[i] == null) {
        System.out.println("[" + (i + 1) + "]: empty");
        continue;
      }
      System.out.println(
          "[" + (i + 1) + "]: " + allyParty[i].getAllyType() + "   |   Current " + "cash: "
              + allyParty[i].currentMoney());
    }
  }

  private void pressEnterToContinue() {
    System.out.println("[Press Enter key to continue...]");
    try {
      System.in.read();
    } catch (Exception ignored) {
    }
  }

  private void introText() {
    System.out.print(
        "Welcome to [Game Name Pending]. In this game, you (the good high class guy) engage in "
            + "verbal sparring with the bad guys (IRS agents). Three encounters, three days: Hire "
            + "allies like financial advisors and secreted-away sons to scare off the agents…while "
            + "paying said allies as little of your hard-won (hedge funds are complicated, man) "
            + "money as possible. \n");
    pressEnterToContinue();
    System.out.print("Why don't we get started with introducing the cas–\n" + "\n"
        + "*bzzzrt.* *bzzzrt.* new message for: Narrator 1. One kind patron on the cast list has paid for all the cast slots (and more). Emphasis on the (and more). They want to be the only one on the cast list… oblige and we can spread some of this dough around, ‘kay? \n"
        + "From: Narrator Council\n" + "\n" + "Hmm… \n" + "\n"
        + "WA-WA-wait, dear player, haha, you didn't see anything! About what I just said; cast list– ahem– I didn't mean cast, I meant *class,* because this is a Class-One, A-List *individual.*\n");
    pressEnterToContinue();
    System.out.println(
        "Relaxing in the spa by day, and sleeping soundly by night…  give it up for entrepreneur Richarch Rich, fondly known as “patriarch Richarch” by his indentured servants! They don't even know they're mispronouncing “Richarch” because they're not educated!\n"
            + "\n"
            + "And the others aren't as important, because they all revolve around Richarch and his pile of money anyway! The real reason Splash Mountain closed was because it paled in comparison to his STASH MOUNTAIN! The other characters are so unimportant, that the game takes place not only at the entrance to Richarch’s driveway, below His Holiness, but the ENTRANCE TO THE ENTRANCE to the driveway! I'm really making this sound appealing, aren't I! Let's get a move on! \n");
    pressEnterToContinue();
  }
}
