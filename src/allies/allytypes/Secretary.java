package allies.allytypes;

import allies.Ally;
import allies.AllyClass;
import gameManagement.Billion;
import gameManagement.DamageType;
import gameManagement.MoveQuitOrFailed;

public class Secretary implements Ally {

  public Secretary(Billion startCash) {

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
    return new Billion(0);
  }

  @Override
  public Billion maxCash() {
    return null;
  }



  @Override
  public AllyClass getAllyType() {
    return AllyClass.Secretary;
  }
}
