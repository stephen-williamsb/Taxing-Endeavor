package allies.allytypes;

import allies.Ally;
import allies.AllyClass;
import gameManagement.Billion;
import gameManagement.DamageType;
import gameManagement.MoveQuitOrFailed;

public class Lawyer implements Ally {

  public Lawyer(Billion startCash) {

  }

  @Override
  public void intro() {
    System.out.println("It is now The Lawyer's Turn! The Laywer may perform the following "
        + "actions:");
    System.out.println(
        "[1] Pay raise");
    System.out.println(
        "[2] Summon ally");
    System.out.println("[3] Discharge");
    System.out.println("To select a move type the number: ");
  }

  @Override
  public void actions(String actionNumber) throws MoveQuitOrFailed {

  }

  @Override
  public void adjustMoney(Billion adjustBy) {

  }

  @Override
  public Billion currentMoney() {
    return new Billion(0);
  }

  @Override
  public DamageType getDamageType() {
    return null;
  }

  @Override
  public AllyClass getAllyType() {
    return AllyClass.Lawyer;
  }
}
