package enemies;

import static gameManagement.GameManager.round;

import allies.Ally;
import gameManagement.Billion;
import gameManagement.DamageType;
import gameManagement.GameManager;
import java.util.Random;

public abstract class EnemyTemplate implements Enemy {

  private final GameManager manager;
  private final String name;
  private final DamageType[] damageList = {DamageType.Logic, DamageType.Emotional,
      DamageType.Blackmail};
  private final Billion defaultDamage;
  private final double damageReduction;
  private int sanity;
  //when this hit 4 the weakness and strength is randomized.
  private int turnsTillSwap;
  private int defaultTurnsTillSwap;
  private DamageType strongAgainst;
  private DamageType weakAgainst;
  private Billion currentDamage;
  private int damageTaken;
  private Ally target;

  public EnemyTemplate(GameManager manager, String name, int sanity, int turnsTillSwap,
      Billion defaultDamage, double damageReduction) {
    determineRandomTyping();
    this.name = name;
    this.manager = manager;
    this.sanity = sanity;
    this.turnsTillSwap = turnsTillSwap;
    this.defaultDamage = new Billion(defaultDamage.getCash());
    currentDamage = new Billion(defaultDamage.getCash());
    damageTaken = 0;
    this.damageReduction = damageReduction;
  }


  @Override
  public void act() {
    Random rand = new Random();
    Ally[] allyParty = manager.fetchAllyParty();
    Ally target = null;
    if (manager.allyCount() == 1) {
      target = allyParty[0];
      System.out.println("target locked!");
    } else if (this.target != null) {
      target = this.target;
    } else {
      while (target == null) {
        target = allyParty[rand.nextInt(1, 4)];
      }

    }

    currentDamage.add(new Billion(damageTaken * damageReduction));
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
      turnsTillSwap = defaultTurnsTillSwap;
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
    return name + ", Current Sanity: " + getSanity();
  }

  @Override
  public String getName() {
    return name;
  }
}
