package allies;

import gameManagement.Billion;
import gameManagement.DamageType;
import gameManagement.MoveQuitOrFailed;

public interface Ally {

  /**
   * Starts the turn of this ally
   */
  void startTurn();

  /**
   * Makes the ally perform the action on the numbered slot.
   *
   * @param actionNumber the number for the action.
   */
  void actions(int actionNumber) throws MoveQuitOrFailed;

  /**
   * Adjusts the allies health by the adjustBy value.
   *
   * @param adjustBy the amount of money to be adjusted.
   */
  void adjustMoney(Billion adjustBy);


  /**
   * @return the current amount of money this ally has
   */
  Billion currentMoney();

  /**
   * Gets the damage type of the ally.
   *
   * @return Other.DamageType of ally
   */
  DamageType getDamageType();

  /**
   * Gets the damage type of the ally.
   *
   * @return Other.DamageType of ally
   */
  AllyClass getAllyType();
}
