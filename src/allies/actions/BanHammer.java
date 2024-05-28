package allies.actions;

import allies.Ally;
import gameManagement.DamageType;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public class BanHammer implements Action {

  @Override
  public String getName() {
    return "Banhammer";
  }

  @Override
  public String getFlavorText(Ally user) {
    return user + " obliterated a foe.";
  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {
    manager.dealDamage(9999999, DamageType.Neutral);
  }
}
