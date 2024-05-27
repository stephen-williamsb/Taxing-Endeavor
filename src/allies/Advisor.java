package allies;

import gameManagement.Billion;
import gameManagement.DamageType;
import gameManagement.MoveQuitOrFailed;

public class Advisor implements Ally {

  public Advisor(Billion startCash) {

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
  public Billion currentMoney() {
    return new Billion(0);
  }

  @Override
  public DamageType getDamageType() {
    return null;
  }

  @Override
  public AllyClass getAllyType() {
    return AllyClass.Advisor;
  }
}
