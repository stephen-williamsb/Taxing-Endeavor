package enemies;

import Other.DamageType;

public interface Enemy {

  /**
   * Makes the enemies.Enemy perform the action on the numbered slot.
   *
   *
   */
  void act();

  /**
   * Adjusts the Enemies health by the adjustBy value.
   *
   * @param adjustBy the amount of money to be adjusted.
   */
  void adjustSanity(int adjustBy);

  /**
   * Gets the damage type of the enemies.Enemy.
   *
   * @return Other.DamageType of enemies.Enemy
   */
  DamageType getDamageType();

  /**
   * Checks to see if the enemy still lives
   *
   * @return true if enemy live, false if enemy is dead;
   */
  boolean isAlive();
}
