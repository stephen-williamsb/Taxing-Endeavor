package allies.actions;

import allies.Ally;
import gameManagement.DamageType;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public class AnalyzeAgent implements Action {

  private DamageType strength = DamageType.Neutral;
  private DamageType weakness = DamageType.Neutral;

  @Override
  public String getName() {
    return "Analyze Agent";
  }

  @Override
  public String getFlavorText(Ally user) {
    return "Through scrutinous use of a magnifying glass, the secretary deduces that the IRS "
        + "Agent’s current weakness is " + weakness + " and current resistance " + strength + "!";
  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {
    manager.dealDamage(20, DamageType.Logic);
    weakness = manager.getCurrentEnemy().getWeakness();
    strength = manager.getCurrentEnemy().getStrength();
  }
}
