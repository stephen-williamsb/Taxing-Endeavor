package allies.allytypes;

import allies.Ally;
import allies.AllyClass;
import gameManagement.Billion;
import gameManagement.DamageType;
import gameManagement.MoveQuitOrFailed;

public class Advisor implements Ally {

  final Billion maxCash;
  Billion currentCash;

  public Advisor(Billion startCash) {
    maxCash = startCash;
    currentCash = maxCash;
  }

  @Override
  public void intro() {

  }


  @Override
  public void actions(String actionNumber) throws MoveQuitOrFailed {

  }

  @Override
  public void adjustMoney(Billion adjustBy) {

  }

  @Override
  public Billion currentMoney() {
    return currentCash;
  }

  @Override
  public Billion maxCash() {
    return maxCash;
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
