package allies.actions;

import allies.Ally;
import gameManagement.DamageType;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;
import java.util.Random;

public class LawyerLariat implements Action {

  private DamageType damageType = DamageType.Neutral;
  final DamageType[] damageList = {DamageType.Logic, DamageType.Emotional,
      DamageType.Blackmail, DamageType.Neutral};

  @Override
  public String getName() {
    return "Lawyer Lariat";
  }

  @Override
  public String getFlavorText(Ally user) {
    return "It’s a bird! It’s a plane! No, it’s a lawyer?! The lawyer’s mega-slam is so intense "
        + "it is an entirely random element! This time it’s " + damageType + "!";
  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {
    Random rand = new Random();
    damageType = damageList[rand.nextInt(0, damageList.length - 1)];
    manager.dealDamage(70, damageType);
  }
}
