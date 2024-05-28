package allies.actions;

import static gameManagement.GameManager.numHelper;

import allies.Ally;
import gameManagement.Billion;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public class CreatingMoney implements Action {

  private Billion moneyGiven;

  @Override
  public String getName() {
    return "Creating Money";
  }

  @Override
  public String getFlavorText(Ally user) {
    return "The financial advisor’s answer to the screaming, painful cries of their allies is to "
        + "get neck-deep into paperwork. Ba-boom and cha-ching! All allies receive " + moneyGiven
        + "";
  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {
    double percentIncrease = self.maxCash().getCash() * .15;
    percentIncrease = numHelper(percentIncrease);
    moneyGiven = new Billion(percentIncrease);
    Ally[] party = manager.fetchAllyParty();
    for (int i = 0; i < manager.allyCount(); i++) {
      party[i].adjustMoney(moneyGiven);
    }
  }
}
