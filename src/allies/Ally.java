package allies;

import gameManagement.Billion;
import gameManagement.MoveQuitOrFailed;

public interface Ally {

  /**
   * Introduces the current ally and their abilities
   */
  void intro();


  /**
   * Makes the ally perform the action on the numbered slot.
   *
   * @param actionName the number for the action.
   */
  void actions(String actionName) throws MoveQuitOrFailed;

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
   * @return the max amount of money this can have
   */
  Billion maxCash();


  /**
   * Gets the damage type of the ally.
   *
   * @return Other.DamageType of ally
   */
  AllyClass getType();
}
