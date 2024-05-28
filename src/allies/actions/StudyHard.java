package allies.actions;

import allies.Ally;
import allies.allytypes.Son;
import gameManagement.GameManager;
import gameManagement.MoveQuitOrFailed;

public class StudyHard implements Action {

  public int age;

  public StudyHard(int age, Son son) {
    this.age = age;

  }

  @Override
  public String getName() {
    return "Study Hard";
  }

  @Override
  public String getFlavorText(Ally user) {
    return "The boy undergoes a training montage and grows up by one year! He’s now " + (age + 1)
        + " " + "years " + "old! (increase Son’s age by 1.)";
  }

  @Override
  public void act(GameManager manager, Ally self) throws MoveQuitOrFailed {

  }
}
