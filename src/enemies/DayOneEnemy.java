package enemies;

import gameManagement.Billion;
import gameManagement.GameManager;

public class DayOneEnemy extends EnemyTemplate {


  public DayOneEnemy(GameManager manager) {
    super(manager, "IRS AGENT 1", 1000, 4, new Billion(0.6), 0.0006);
  }
}
