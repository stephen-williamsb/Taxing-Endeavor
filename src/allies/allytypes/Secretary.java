package allies.allytypes;

import allies.AllyClass;
import allies.actions.Action;
import allies.actions.AnalyzeAgent;
import allies.actions.DigUpDirt;
import gameManagement.Billion;
import gameManagement.GameManager;
import java.util.ArrayList;

public class Secretary extends templateClass {

  public Secretary(Billion startCash, GameManager manager) {
    super(startCash, manager, "\nThe Secretary types away! ", AllyClass.Secretary);

    ArrayList<Action> actions = new ArrayList<>();
    actions.add(new DigUpDirt());
    actions.add(new AnalyzeAgent());
    this.setActions(actions);
  }
}