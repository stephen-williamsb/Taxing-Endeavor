package allies.actions;

import allies.Ally;
import enemies.Enemy;
import gameManagement.DamageType;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public class DamningEvidence implements Action {

  private boolean suceed = false;

  @Override
  public String getName() {
    return "Damning Evidence";
  }

  @Override
  public String getFlavorText(Ally user) {
    if (suceed) {
      return "The Lawyer opens a briefcase with look of glee to show the IRS agent some blackmail"
          + ". The IRS agent pales";
    }
    return "The Laywer opens a briefcase with look of glee to show the IRS agent some blackmail. "
        + "The IRS agent scoffs at the failed attempt.";

  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {
    Enemy currentFoe = manager.getCurrentEnemy();
    if (currentFoe.getWeakness() == DamageType.Blackmail) {
      suceed = true;
      manager.dealDamage(120, DamageType.Blackmail);
    }
  }
}
