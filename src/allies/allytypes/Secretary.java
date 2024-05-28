package allies.allytypes;

import allies.AllyClass;
import allies.actions.Action;
import allies.actions.Discharge;
import gameManagement.Billion;
import gameManagement.GameManager;
import java.util.ArrayList;

public class Secretary extends templateClass {

  public Secretary(Billion startCash, GameManager manager) {
    super(startCash, manager, "\nThe Secretary types away! ", AllyClass.Secretary);

    ArrayList<Action> actions = new ArrayList<>();
    actions.add(new Discharge());

    this.setActions(actions);
  }
}