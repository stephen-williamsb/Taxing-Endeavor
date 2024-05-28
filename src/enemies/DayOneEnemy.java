package enemies;

import static gameManagement.GameManager.round;

import allies.Ally;
import gameManagement.Billion;
import gameManagement.DamageType;
import gameManagement.GameManager;
import java.util.Random;

public class DayOneEnemy implements Enemy {

  private final GameManager manager;
  private final String name = "IRS AGENT 1";
  private int sanity;
  //when this hit 4 the weakness and strength is randomized.
  private int turnsTillSwap;
  private final DamageType[] damageList = {DamageType.Logic, DamageType.Emotional,
      DamageType.Blackmail};
  private DamageType strongAgainst;
  private DamageType weakAgainst;
  private Billion defaultDamage;
  private Billion currentDamage;
  private int damageTaken;
  private Ally target;
  private final double damageReduction = 0.0006;

  public DayOneEnemy(GameManager manager) {
    determineRandomTyping();
    this.manager = manager;
    sanity = 1000;
    turnsTillSwap = 4;
    defaultDamage = new Billion(0.6);
    currentDamage = defaultDamage;
    damageTaken = 0;
  }


  @Override
  public void act() {
    Random rand = new Random();
    Ally[] allyParty = manager.fetchAllyParty();
    Ally target;
    if (manager.allyCount() == 1) {
      target = allyParty[0];
      System.out.println("target locked!");
    } else if (this.target != null) {
      target = this.target;
    } else {
      target = allyParty[rand.nextInt(1, manager.allyCount())];
    }

    currentDamage.add(new Billion(damageTaken * 0.0006));
    currentDamage = round(currentDamage);
    System.out.println(
        name + " wanted to take .6 billion, but due to your arguments this turn, they only took "
            + currentDamage + " from " + target.getType() + "!");

    target.adjustMoney(new Billion(currentDamage.getCash() * -1));
    damageTaken = 0;
    currentDamage = new Billion(defaultDamage.getCash());
    this.target = null;
  }

  @Override
  public void adjustSanity(int adjustBy, DamageType damageType) {
    if (adjustBy > 0) {
      sanity += adjustBy;
      return;
    }
    if (damageType == weakAgainst) {
      adjustBy *= 2;
      turnsTillSwap--;
    }
    if (damageType == strongAgainst) {
      adjustBy /= 2;
    }
    sanity += adjustBy;
    damageTaken += adjustBy;

    if (turnsTillSwap == 0) {
      turnsTillSwap = 4;
      determineRandomTyping();
    }
  }

  @Override
  public DamageType getStrength() {
    return strongAgainst;
  }

  @Override
  public DamageType getWeakness() {
    return weakAgainst;
  }

  @Override
  public boolean isAlive() {
    return (sanity > 0);
  }

  private void determineRandomTyping() {
    Random rand = new Random();
    while (weakAgainst == null || strongAgainst == null) {
      DamageType current = damageList[rand.nextInt(0, damageList.length - 1)];
      if (strongAgainst == null && weakAgainst != current) {
        strongAgainst = current;
      }
      if (weakAgainst == null && strongAgainst != current) {
        weakAgainst = current;
      }
    }
  }

  public void forcetarget(Ally target) {
    this.target = target;
  }

  @Override
  public int getSanity() {
    return sanity;
  }

  @Override
  public String toString() {
    return name + ", moral: " + getSanity();
  }
}
