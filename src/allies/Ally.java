package allies;

import Other.DamageType;

public interface Ally {

  /**
   * Makes the ally perform the action on the numbered slot.
   *
   * @param actionNumber the number for the action.
   */
  void actions(int actionNumber);

  /**
   * Adjusts the allies health by the adjustBy value.
   *
   * @param adjustBy the amount of money to be adjusted.
   */
  void adjustMoney(int adjustBy);

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
