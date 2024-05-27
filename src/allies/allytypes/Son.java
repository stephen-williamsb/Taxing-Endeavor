package allies.allytypes;

import allies.Ally;
import allies.AllyClass;
import gameManagement.Billion;
import gameManagement.DamageType;
import gameManagement.MoveQuitOrFailed;

public class Son implements Ally {

  public Son(Billion startCash) {

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
  public DamageType getDamageType() {
    return null;
  }

  @Override
  public AllyClass getAllyType() {
    return AllyClass.Son;
  }
}
