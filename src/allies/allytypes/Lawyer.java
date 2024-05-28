package allies.allytypes;

import allies.AllyClass;
import allies.actions.Action;
import allies.actions.Discharge;
import gameManagement.Billion;
import gameManagement.GameManager;
import java.util.ArrayList;

public class Lawyer extends templateClass {

  public Lawyer(Billion startCash, GameManager manager) {
    super(startCash, manager, "\nThe Lawyer prepares the paperwork! ", AllyClass.Lawyer);

    ArrayList<Action> actions = new ArrayList<>();
    actions.add(new Discharge());

    this.setActions(actions);
  }
}
