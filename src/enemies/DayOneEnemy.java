package enemies;

import gameManagement.DamageType;
import gameManagement.GameManager;

public class DayOneEnemy implements Enemy {

  private GameManager manager;

  public DayOneEnemy(GameManager manager) {
    this.manager = manager;
  }

  @Override
  public void act() {
    System.out.println("enemy attacked");
  }

  @Override
  public void adjustSanity(int adjustBy) {

  }

  @Override
  public DamageType getDamageType() {
    return null;
  }

  @Override
  public boolean isAlive() {
    return true;
  }
}
