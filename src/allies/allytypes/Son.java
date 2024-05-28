package allies.allytypes;

import allies.AllyClass;
import allies.actions.Action;
import allies.actions.FamilyBuisness;
import allies.actions.StudyHard;
import gameManagement.Billion;
import gameManagement.GameManager;
import java.util.ArrayList;

public class Son extends templateClass {

  private int age = 3;

  public Son(Billion startCash, GameManager manager) {
    super(startCash, manager, "\nThe orphan... I mean Son wants icecream and promotes the family "
            + "buisness! ",
        AllyClass.Son);
    age = (int) ((startCash.getCash() / 2.0) * 10) + age;
    ArrayList<Action> actions = new ArrayList<>();
    actions.add(new FamilyBuisness(age));
    actions.add(new StudyHard(age, this));
    this.setActions(actions);
  }

  public void adjustAge() {
    age++;
    ArrayList<Action> actions = new ArrayList<>();
    actions.add(new FamilyBuisness(age));
    actions.add(new StudyHard(age, this));
    this.setActions(actions);
  }
}
