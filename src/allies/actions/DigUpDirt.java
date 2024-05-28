package allies.actions;

import allies.Ally;
import gameManagement.DamageType;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public class DigUpDirt implements Action {

  @Override
  public String getName() {
    return "Dig Up Dirt";
  }

  @Override
  public String getFlavorText(Ally user) {
    return "The secretary digs up dirt with a megaton shovel! This is some real good ammunition! The next Blackmail type move will deal double damage!";
  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {
    manager.dealDamage(60, DamageType.Neutral);
    manager.boostDamage(DamageType.Blackmail);
  }
}
