package allies.allytypes;

import allies.Ally;
import allies.AllyClass;
import allies.actions.Action;
import allies.actions.Discharge;
import allies.actions.PayRaise;
import allies.actions.SummonAlly;
import gameManagement.Billion;
import gameManagement.DamageType;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Richarch implements Ally {

  private final GameManager manager;
  Scanner scanner;
  private final Billion cashOnHand;
  private final List<Action> actions;

  public Richarch(Billion startCash, GameManager manager) {
    cashOnHand = startCash;
    this.manager = manager;
    scanner = new Scanner(System.in);
    actions = new ArrayList<>();
    actions.add(new PayRaise());
    actions.add(new SummonAlly());
    actions.add(new Discharge());
  }

  @Override
  public void intro() {
    System.out.println("\nIt is now Richarch's Turn! You have " + cashOnHand + " cash on hand. You"
        + " can do the following:");
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
    throw new MoveQuitOrFailed(
        "There is no action that has the name " + actionName);

  }

  @Override
  public void adjustMoney(Billion adjustBy) {
    cashOnHand.add(adjustBy);
    if (cashOnHand.getCash() <= 0) {
      throw new IllegalStateException("Game Over! How in the world did you lose?!");
    }
  }

  @Override
  public Billion currentMoney() {
    return cashOnHand;
  }

  @Override
  public Billion maxCash() {
    return new Billion(Double.POSITIVE_INFINITY);
  }

  /**
   * Richarch does not deal damage
   *
   * @return DamageType.None
   */
  @Override
  public DamageType getDamageType() {
    return DamageType.None;
  }

  @Override
  public AllyClass getAllyType() {
    return AllyClass.Richarch;
  }
}
