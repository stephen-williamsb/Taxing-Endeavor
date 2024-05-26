package Other;

import allies.Ally;
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
