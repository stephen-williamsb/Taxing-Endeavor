package allies;

import Other.DamageType;

public class Lawyer implements Ally {

  private int currentCash;
  private DamageType damageType;

  public Lawyer(int cashGiven) {
    currentCash = cashGiven;
    damageType = DamageType.Emotional;
  }

  @Override
  public void actions(int actionNumber) {
    System.out.println("Performing action" + actionNumber);
  }

  @Override
  public void adjustMoney(int adjustBy) {
    currentCash += adjustBy;
    if (currentCash <= 0) {
      throw new IllegalStateException("Game Over! How in the world did you lose?!");
    }
  }


  @Override
  public DamageType getDamageType() {
    return damageType;
  }

  @Override
  public AllyClass getAllyType() {
    return AllyClass.Lawyer;
  }
}
