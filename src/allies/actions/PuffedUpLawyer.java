package allies.actions;

import allies.Ally;
import gameManagement.DamageType;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public class PuffedUpLawyer implements Action {

  @Override
  public String getName() {
    return "I'm the Important One Here";
  }

  @Override
  public String getFlavorText(Ally user) {
    return "The lawyer oozes charisma: “Listen up here, eyes on me, I’ll take care of this.” Due to his overwhelming presence, the IRS agent is forced to only target the lawyer on their next turn.";
  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {
    manager.dealDamage(60, DamageType.Emotional);
    manager.getCurrentEnemy().forcetarget(self);
  }
}
