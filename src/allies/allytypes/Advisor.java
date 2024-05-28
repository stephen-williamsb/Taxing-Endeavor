package allies.allytypes;

import allies.AllyClass;
import allies.actions.Action;
import allies.actions.CreatingMoney;
import allies.actions.TaxWriteoff;
import gameManagement.Billion;
import gameManagement.GameManager;
import java.util.ArrayList;

public class Advisor extends templateClass {

  public Advisor(Billion startCash, GameManager manager) {
    super(startCash, manager, "\nThe Advisor arrives to the scene! ", AllyClass.Advisor);

    ArrayList<Action> actions = new ArrayList<>();
    actions.add(new TaxWriteoff());
    actions.add(new CreatingMoney());

    this.setActions(actions);
  }


}
