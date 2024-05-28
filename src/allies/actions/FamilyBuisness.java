package allies.actions;

import allies.Ally;
import gameManagement.DamageType;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public class FamilyBuisness implements Action {

  private int age = 0;

  public FamilyBuisness(int age) {
    this.age = age;
  }

  @Override
  public String getName() {
    return "But it's a family business!";
  }

  @Override
  public String getFlavorText(Ally user) {
    if (age <= 5) {
      return user.getType() + "(" + age + "years old)" + "The toddler babbles incoherently!";
    }
    if (age <= 8) {
      return user.getType() + "(" + age + "years old)"
          + "The kid’s bad acting makes it obvious he’s not Richarch’s real son, but he’s trying dammit!";
    }
    if (age <= 10) {
      return user.getType() + "(" + age + "years old)"
          + "The child delivers a heartwarming speech on the importance of family business!";
    }
    if (age <= 13) {
      return user.getType() + "(" + age + "years old)"
          + "The youth’s honest treatise brings a tear to the agent’s eye!";
    } else {
      return user.getType() + "(" + age + "years old)"
          + "The young man’s gut-wrenching oration on the psychological impact of over-exposing "
          + "kids to taxes has the agent in shambles!";
    }
  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {
    if (age <= 5) {
      manager.dealDamage(10, DamageType.Emotional);
    } else if (age <= 8) {
      manager.dealDamage(50, DamageType.Emotional);
    } else if (age <= 10) {
      manager.dealDamage(60, DamageType.Emotional);
    } else if (age <= 13) {
      manager.dealDamage(110, DamageType.Emotional);
    } else {
      manager.dealDamage(160, DamageType.Emotional);
    }
  }
}
