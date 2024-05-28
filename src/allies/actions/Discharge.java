package allies.actions;

import static gameManagement.GameManager.getUserInWithQuit;

import allies.Ally;
import allies.AllyClass;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public class Discharge implements Action {

  @Override
  public String getName() {
    return "Discharge";
  }

  @Override
  public String getFlavorText(Ally user) {
    return user.getType() + " has discharged an ally!";
  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {
    Ally currentAlly = null;
    String userAnswer;
    while (currentAlly == null || currentAlly.getType() == AllyClass.Richarch) {
      System.out.println("Which ally would you like to fire? : ");
      manager.printPartyAsOptions();
      userAnswer = getUserInWithQuit();
      currentAlly = manager.getAllyAt(Integer.parseInt(userAnswer));
      if (currentAlly.getType() == AllyClass.Richarch) {
        System.out.println("Cannot choose self");
      }
    }
    manager.dismissAlly(currentAlly);
  }
}
