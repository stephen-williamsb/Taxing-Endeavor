package allies.allytypes;

import allies.AllyClass;
import allies.actions.Action;
import allies.actions.Discharge;
import gameManagement.Billion;
import gameManagement.GameManager;
import java.util.ArrayList;

public class Son extends templateClass {

  public Son(Billion startCash, GameManager manager) {
    super(startCash, manager, "\nThe orphan... I mean Son wants icecream and promotes the family "
            + "buisness! ",
        AllyClass.Son);

    ArrayList<Action> actions = new ArrayList<>();
    actions.add(new Discharge());

    this.setActions(actions);
  }
}
