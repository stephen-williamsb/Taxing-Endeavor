package allies;

import gameManagement.Billion;
import gameManagement.DamageType;
import gameManagement.MoveQuitOrFailed;

public class Lawyer implements Ally {

  public Lawyer(Billion startCash) {

  }

  @Override
  public void intro() {

  }

  @Override
  public void startTurn() {

  }

  @Override
  public void actions(int actionNumber) throws MoveQuitOrFailed {

  }

  @Override
  public void adjustMoney(Billion adjustBy) {

  }

  @Override
  public Billion currentMoney() {
    return new Billion(0);
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
