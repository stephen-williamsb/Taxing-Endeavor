package allies.actions;

import static gameManagement.GameManager.getUserInWithQuit;

import allies.Ally;
import allies.AllyClass;
import gameManagement.Billion;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public class PayRaise implements Action {

  private Ally target;

  @Override
  public String getName() {
    return "Pay Raise";
  }

  @Override
  public String getFlavorText(Ally user) {
    return user.getAllyType() + " has increased the pay of " + target.getAllyType() + ".";
  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {
    Ally currentAlly = null;
    String userAnswer;
    while (currentAlly == null || currentAlly.getAllyType() == AllyClass.Richarch) {
      System.out.println("Which ally? : ");
      manager.printPartyAsOptions();
      userAnswer = getUserInWithQuit();
      currentAlly = manager.getAllyAt(Integer.parseInt(userAnswer));
      if (currentAlly.getAllyType() == AllyClass.Richarch) {
        System.out.println("Cannot choose self");
      }
    }
    System.out.println("How much money? Keep in mind Richarch handles cash in billions: ");
    userAnswer = getUserInWithQuit();
    int amountTransfered = Integer.parseInt(userAnswer);
    while (self.currentMoney().getCash() - amountTransfered < 0 || amountTransfered < 0) {
      System.out.println("Invalid amount. How much money? : ");
      userAnswer = getUserInWithQuit();
      amountTransfered = Integer.parseInt(userAnswer);
    }
    self.currentMoney().sub(amountTransfered);
    amountTransfered = (int) (amountTransfered * (.75 + (.15 * manager.countFinancialAdvisors())));
    target = currentAlly;
    currentAlly.adjustMoney(new Billion(amountTransfered));
  }

}
