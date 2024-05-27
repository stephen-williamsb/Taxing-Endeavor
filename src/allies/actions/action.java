package allies.actions;

import allies.Ally;
import enemies.Enemy;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public interface action {

  /**
   * Get the name of the current action
   *
   * @return the actions name. Expected to be one word or phrase, each new word seperated by a space
   * and capitalized.
   */
  String getName();

  /**
   * The flavor text of what happens when the action is performed
   *
   * @return the flavor text of the action.
   */
  String getFlavorText(Ally user, String target);

  /**
   * The action that is performed such as damage, healing or other effects.
   *
   * @param manager the thing the action is performed on.
   */
  void act(GameManager manager, Ally self, Enemy enemy) throws MoveQuitOrFailed;
}
