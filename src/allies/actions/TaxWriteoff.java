package allies.actions;

import allies.Ally;
import gameManagement.DamageType;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public class TaxWriteoff implements Action {


  @Override
  public String getName() {
    return "Tax Writeoff";
  }

  @Override
  public String getFlavorText(Ally user) {
    return "The financial advisor knows that what the world needs is another finished movie to get wiped from the planet. Because TAX";
  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {
    manager.dealDamage(80, DamageType.Logic);
  }
}
