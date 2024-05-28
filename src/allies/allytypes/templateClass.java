package allies.allytypes;

import allies.Ally;
import allies.AllyClass;
import allies.actions.Action;
import gameManagement.Billion;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class templateClass implements Ally {

  private final GameManager manager;
  Scanner scanner;
  private final Billion maxCash;
  private Billion currentCash;
  private final String introText;
  private List<Action> actions;
  private final AllyClass thisAllyClass;


  public templateClass(Billion maxCash, GameManager manager,
      String introText, AllyClass allyClass) {
    this.maxCash = maxCash;
    currentCash = maxCash;
    this.manager = manager;
    scanner = new Scanner(System.in);
    this.introText = introText;
    thisAllyClass = allyClass;
  }

  protected void setActions(ArrayList<Action> providedActions) {
    actions = providedActions;
  }

  @Override
  public void intro() {
    System.out.println(introText);
    for (int i = 0; i < actions.size(); i++) {
      System.out.println("[" + (i + 1) + "]: " + actions.get(i).getName());
    }
    System.out.println("To select a move type the name of the move: ");
  }

  @Override
  public void actions(String actionName) throws MoveQuitOrFailed {
    int actionNameAsNum = -1;
    try {
      actionNameAsNum = Integer.parseInt(actionName);
      actionNameAsNum--;
    } catch (Exception ignored) {
    }
    for (Action action : actions) {
      if (action.getName().equalsIgnoreCase(actionName)
          || actions.indexOf(action) == actionNameAsNum) {
        action.act(manager, this);
        System.out.println(action.getFlavorText(this));
        return;
      }
    }
    throw new MoveQuitOrFailed("There is no action that has the name " + actionName);

  }

  @Override
  public void adjustMoney(Billion adjustBy) {
    maxCash.add(adjustBy);
    if (maxCash.getCash() <= 0) {
      System.out.println(this.getAllyType() + " has fled due to lack of cash.");
      manager.dismissAlly(this);
    }
  }

  @Override
  public Billion currentMoney() {
    return currentCash;
  }

  @Override
  public Billion maxCash() {
    return maxCash;
  }


  @Override
  public AllyClass getAllyType() {
    return thisAllyClass;
  }
}

