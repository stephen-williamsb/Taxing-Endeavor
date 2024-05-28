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
    System.out.println(
        currentAlly + " will be payed half their max pay which is " + currentAlly.maxCash()
            + " billion. Excess "
            + "will be lost. Type q or quit to stop any other keys will accept the payment.");
    userAnswer = getUserInWithQuit();
    double amountTransfered = (currentAlly.maxCash().getCash() / 2);
    while (self.currentMoney().getCash() - amountTransfered < 0 || amountTransfered < 0) {
      System.out.println("Invalid amount. How much money? : ");
      userAnswer = getUserInWithQuit();
      amountTransfered = Integer.parseInt(userAnswer);
    }
    self.currentMoney().sub(amountTransfered);
    amountTransfered = (amountTransfered * (.75 + (.15 * manager.countFinancialAdvisors())));
    target = currentAlly;
    currentAlly.adjustMoney(new Billion(amountTransfered));
  }

}
