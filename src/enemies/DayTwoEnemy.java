package enemies;

import gameManagement.Billion;
import gameManagement.GameManager;

public class DayTwoEnemy extends EnemyTemplate {


  public DayTwoEnemy(GameManager manager) {
    super(manager, "IRS AGENT 2", 1300, 3, new Billion(0.6), 0.0006);
  }
}
