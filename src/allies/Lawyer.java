package allies;

import other.DamageType;
import other.MoveQuitOrFailed;

public class Lawyer implements Ally {

  public Lawyer(int startCash) {

  }

  @Override
  public void startTurn() {

  }

  @Override
  public void actions(int actionNumber) throws MoveQuitOrFailed {

  }

  @Override
  public void adjustMoney(int adjustBy) {

  }

  @Override
  public int currentMoney() {
    return 0;
  }

  @Override
  public DamageType getDamageType() {
    return null;
  }

  @Override
  public AllyClass getAllyType() {
    return AllyClass.Lawyer;
  }
}
