package allies;

import Other.DamageType;
import Other.MoveQuitOrFailed;

public class Son implements Ally{

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
    return AllyClass.Son;
  }
}
