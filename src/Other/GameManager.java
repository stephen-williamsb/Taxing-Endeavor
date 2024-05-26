package Other;

import allies.Ally;
import allies.AllyClass;
import allies.Richarch;
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

  }

  public Ally getAllyAt(int pos) {
    return allyParty[pos - 1];
  }

  public void createAlly(AllyClass ally, int startCash) {
    Ally allyCreated;
    switch (ally) {
      case Son -> allyCreated = new Richarch(this);
      case Secretary -> allyCreated = new Richarch(this);
      case Lawyer -> allyCreated = new Richarch(this);
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
      System.out.println("[" + (i + 1) + "]: " + allyParty[i].getAllyType() + "   |   Current "
          + "cash: " + allyParty[i].currentMoney());
    }
  }


}
