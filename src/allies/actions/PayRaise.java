package allies.actions;

import static gameManagement.GameManager.getUserInWithQuit;

import allies.Ally;
import allies.AllyClass;
import enemies.Enemy;
import gameManagement.Billion;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public class PayRaise implements action{

  @Override
  public String getName() {
    return "Pay Raise";
  }

  @Override
  public String getFlavorText(Ally user, String target) {
    return user.getAllyType() + " has increased the pay of " + target + ".";
  }

  @Override
  public void act(GameManager manager, Ally self, Enemy enemy) throws MoveQuitOrFailed {
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
    System.out.println("How much money? : ");
    userAnswer = getUserInWithQuit();
    int amountTransfered = Integer.parseInt(userAnswer);
    while (self.currentMoney().getCash() - amountTransfered < 0 || amountTransfered < 0) {
      System.out.println("Invalid amount. How much money? : ");
      userAnswer = getUserInWithQuit();
      amountTransfered = Integer.parseInt(userAnswer);
    }
    self.currentMoney().sub(amountTransfered);
    amountTransfered = (int) (amountTransfered * (.75 + (.15 * manager.countFinancialAdvisors())));
    currentAlly.adjustMoney(new Billion(amountTransfered));
  }

}
