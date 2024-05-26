package allies;

import Other.DamageType;
import Other.MoveQuitOrFailed;

public interface Ally {

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
  void adjustMoney(int adjustBy);

  /**
   * @return the current amount of money this ally has
   */
  int currentMoney();

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
