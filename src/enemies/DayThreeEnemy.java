package enemies;

import gameManagement.Billion;
import gameManagement.GameManager;

public class DayThreeEnemy extends EnemyTemplate {


  public DayThreeEnemy(GameManager manager) {
    super(manager, "JOHN IRS", 1600, 3, new Billion(0.7), 0.0005);
  }
}
