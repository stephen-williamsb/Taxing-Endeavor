package enemies;

import Other.DamageType;
import Other.GameManager;

public class DayOneEnemy implements Enemy{
  private GameManager manager;
  public DayOneEnemy(GameManager manager){
    this.manager = manager;
  }

  @Override
  public void act() {

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
