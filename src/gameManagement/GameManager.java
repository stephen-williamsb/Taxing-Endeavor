package gameManagement;

import allies.Ally;
import allies.AllyClass;
import allies.allytypes.Advisor;
import allies.allytypes.Lawyer;
import allies.allytypes.Richarch;
import allies.allytypes.Secretary;
import allies.allytypes.Son;
import enemies.DayOneEnemy;
import enemies.DayThreeEnemy;
import enemies.DayTwoEnemy;
import enemies.Enemy;
import gameManagement.commands.CommandHandler;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class GameManager {

  private final Ally[] allyParty;
  private Enemy currentFoe;
  private int day;
  private final CommandHandler commandHandler;
  private final Billion richarchDailyGains = new Billion(10);
  private final Scanner scanner;
  private double allyHappiness = 0;
  private ArrayList<DamageType> boostedDamage;

  public GameManager() {
    scanner = new Scanner(System.in);
    commandHandler = new CommandHandler();
    day = 1;
    allyParty = new Ally[4];
    Billion richarchStartCash = new Billion(4);
    allyParty[0] = new Richarch(richarchStartCash, this);
    boostedDamage = new ArrayList<>();
  }

  private Enemy GetFoeAtDay(int day) {
    return switch (day) {
      case 1 -> new DayOneEnemy(this);
      case 2 -> new DayTwoEnemy(this);
      case 3 -> new DayThreeEnemy(this);
      default -> null;
    };
  }

  public void StartGame() {
    introText();
    firstDayText();
    startDay(day);
    day++;
    startDay(day);
    day++;
    startDay(day);
    System.out.println("Congrats! You beat the game! Richarch will stay rich and was left with "
        + allyParty[0].currentMoney() + " in his wallet!");
  }

  public void startDay(int day) {
    currentFoe = GetFoeAtDay(day);
    allyParty[0].adjustMoney(richarchDailyGains);

    System.out.println("The Battle Begins! ");
    int currentPartMember = 0;
    while (currentFoe.isAlive()) {
      String currentAction = "";
      if (currentPartMember == 4 || allyParty[currentPartMember] == null) {
        currentFoe.act();
        currentPartMember = 0;
        printPartyCheck();
        continue;
      }
      try {
        allyParty[currentPartMember].intro();
        currentAction = handleInput();
        allyParty[currentPartMember].actions(currentAction);
        printPartyCheck();
        currentPartMember++;
      } catch (MoveQuitOrFailed ignored) {
        if (currentAction.equalsIgnoreCase("skip")) {
          currentPartMember++;
        }
      }
    }
  }

  private String handleInput() throws MoveQuitOrFailed {
    String userinput = scanner.nextLine();
    if (userinput == null || userinput.equals("")) {
      System.out.println("Please provide input");
      throw new MoveQuitOrFailed("User did not give input");
    }
    if (commandHandler.handleInput(userinput)) {
      pressEnterToContinue();
      throw new MoveQuitOrFailed("/info command given, return user to menu");
    }
    if (userinput.equalsIgnoreCase("/happiness")) {
      System.out.println("Current happiness: " + allyHappiness);
    }
    if (userinput.equalsIgnoreCase("/partycheck") || userinput.equalsIgnoreCase("/party")) {
      printPartyCheck();
      pressEnterToContinue();
      throw new MoveQuitOrFailed("party check command given, return user to menu");
    }

    //userinput passed successfully
    return userinput;
  }


  public Ally getAllyAt(int pos) {
    return allyParty[pos - 1];
  }

  public double getAllyHappiness() {
    return allyHappiness;
  }

  public static double numHelper(double num) {
    Random rand = new Random();
    num = num * rand.nextDouble(.95, 1.05);
    return round(num);
  }

  public static double round(double num) {
    Random rand = new Random();
    BigDecimal bd = new BigDecimal(Double.toString(num));
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  public static Billion round(Billion num) {
    return new Billion(round(num.getCash()));
  }

  public void dismissAlly(Ally ally) {
    double happinessCalc = 0;
    for (int i = 0; i < allyParty.length; i++) {
      if (ally == allyParty[i]) {
        happinessCalc =
            (ally.currentMoney().getCash() / ally.maxCash().getCash()) * 4 - 2;
        System.out.println(
            ally.getType() + " was dismissed and gave a happiness rating of " + happinessCalc
                + " on a scale of -2 to 2");
        allyHappiness += happinessCalc;
        //clear slot
        allyParty[i] = null;
        return;
      }
    }
    System.out.println(ally.getType() + " was not found.");
  }

  public void createAlly(AllyClass ally, Billion startCash) {
    Ally allyCreated;
    switch (ally) {
      case Son -> allyCreated = new Son(startCash, this);
      case Secretary -> allyCreated = new Secretary(startCash, this);
      case Lawyer -> allyCreated = new Lawyer(startCash, this);
      case Advisor -> allyCreated = new Advisor(startCash, this);
      default -> allyCreated = new Richarch(new Billion(-1000), this);
    }

    for (int i = 1; i < allyParty.length; i++) {
      if (allyParty[i] == null) {
        allyParty[i] = allyCreated;
        return;
      }
    }
  }

  public int allyCount() {
    int count = 0;
    for (Ally ally : allyParty) {
      if (ally != null) {
        count++;
      }
    }
    return count;
  }


  public Enemy getCurrentEnemy() {
    return currentFoe;
  }

  public Ally[] fetchAllyParty() {
    return allyParty;
  }

  public void printPartyAsOptions() {
    for (int i = 0; i < allyParty.length; i++) {
      if (allyParty[i] == null) {
        System.out.println("[" + (i + 1) + "]: empty");
        continue;
      }
      System.out.println("[" + (i + 1) + "]: " + allyParty[i].getType());
    }
  }

  public void printPartyCheck() {
    System.out.println();
    for (int i = 0; i < allyParty.length; i++) {
      if (allyParty[i] == null) {
        System.out.println("[" + (i + 1) + "]: empty");
        continue;
      }
      System.out.println(
          "[" + (i + 1) + "]: " + allyParty[i].getType() + "   |   Current " + "cash: "
              + allyParty[i].currentMoney());
    }
    System.out.println(currentFoe);
    System.out.println();
  }

  private void pressEnterToContinue() {
    System.out.println("[Press Enter key to continue...]");
    try {
      System.in.read();
    } catch (Exception ignored) {
    }
  }

  public static String getUserInWithQuit() throws MoveQuitOrFailed {
    Scanner scanner = new Scanner(System.in);
    String userAnswer = scanner.nextLine();
    if (userAnswer.equalsIgnoreCase("q") || userAnswer.equalsIgnoreCase("quit")) {
      System.out.println("\nQuitting current action\n");
      throw new MoveQuitOrFailed("Quit or q was typed");
    }
    return userAnswer;
  }

  public int countFinancialAdvisors() {
    Ally[] allies = fetchAllyParty();
    int advisors = 0;
    for (int i = 1; i < allies.length; i++) {
      if (allies[i] == null) {
        continue;
      }
      if (allies[i].getType() == AllyClass.Advisor) {
        advisors++;
      }
    }
    return advisors;
  }

  public void boostDamage(DamageType type) {
    if (boostedDamage.contains(type)) {
      System.out.println(type + " damage is already boosted...");
      return;
    }
    boostedDamage.add(type);
  }

  /**
   * Deals damage to the current foe
   */
  public void dealDamage(int amount, DamageType type) {
    int currentAmount = amount;
    if (boostedDamage.contains(type)) {
      currentAmount *= 2;
    }

    currentFoe.adjustSanity(-currentAmount, type);
  }

  //Text blocks beyond this point!!


  private void introText() {
    System.out.print(
        "Welcome to [Game Name Pending]. In this game, you (the good high class guy) engage in "
            + "verbal sparring with the bad guys (IRS agents). Three encounters, three days: Hire "
            + "allies like financial advisors and secreted-away sons to scare off the agents…while "
            + "paying said allies as little of your hard-won (hedge funds are complicated, man) "
            + "money as possible. \n");
    pressEnterToContinue();
    System.out.print("""
        Why don't we get started with introducing the cas–

        *bzzzrt.* *bzzzrt.* new message for: Narrator 1. One kind patron on the cast list has paid for all the cast slots (and more). Emphasis on the (and more). They want to be the only one on the cast list… oblige and we can spread some of this dough around, ‘kay?\s
        From: Narrator Council

        Hmm…\s

        WA-WA-wait, dear player, haha, you didn't see anything! About what I just said; cast list– ahem– I didn't mean cast, I meant *class,* because this is a Class-One, A-List *individual.*
        """);
    pressEnterToContinue();
    System.out.println("""
        Relaxing in the spa by day, and sleeping soundly by night…  give it up for entrepreneur Richarch Rich, fondly known as “patriarch Richarch” by his indentured servants! They don't even know they're mispronouncing “Richarch” because they're not educated!

        And the others aren't as important, because they all revolve around Richarch and his pile of money anyway! The real reason Splash Mountain closed was because it paled in comparison to his STASH MOUNTAIN! The other characters are so unimportant, that the game takes place not only at the entrance to Richarch’s driveway, below His Holiness, but the ENTRANCE TO THE ENTRANCE to the driveway! I'm really making this sound appealing, aren't I! Let's get a move on!\s
        """);
    pressEnterToContinue();
  }

  private void firstDayText() {
    System.out.println("""
        DAY 1

        Richarch settles into his custom-built spa chair, loosening his muscles to fill the mold– chiseled generously after the shape of the equally generous man– like liquid. Since there is no room for earthly possessions on his holy throne, he whispers sweet words to his wallet before placing it lovingly into its own mold, only a hair’s breadth away from his fingers yet multiple feet away from his heart, and I mean that literally.\s
        """);
    pressEnterToContinue();
    System.out.println("""
        Suddenly, a raging bout of thundering noise disrupts the voluptuous man's very important thoughts: something between a ring-a-ding-ding and hum-buzz, yet louder than both; it comes from the nearby intercom!\s

        Moments earlier, a servant of undisclosed age with brilliant ears had heard the crackle-pop of the silver box’s startup, its metaphorical breath before the literal screech, and had zip-dashed to the speaker lest the screech become TRULY literal.\s

        But dear player (or reader, at this point), from the twice-above paragraph we already know this lithe little bat of a servant’s effort is in vain! For he does not get to the shiny speaking-box in time to receive the message personally and, especially, for the sake of his already meager pay, *quietly.*
        """);
    pressEnterToContinue();
    System.out.println("""
        “SERVAAAANTS!” Bellows the patriarch with no blood ties. This cry and the shrieking of the intercom join forces to create a magnitude-one aural earthquake. Every impoverished soul in the room knows they are now Impoverished with a capital I.

        Despite the setback, our bat-boy does the rest of his job with due diligence and presses the receiving-end button, popping his shoulder in reaching it but reaching it all the same.
        """);
    System.out.println("""
        “A word for Mr. Rich,” comes a cool voice, “but Mr. Rich is busy,” squeaks bat-boy, “I will be his relay.” Poor choice of words; as if by karma he *literally* becomes Richarch’s relay: From hereon every crumb of cash the IRS agent–for that is who spoke so cooly– sequesters from Richarch will be personally delivered to the agent by bat-boy, from the spa that holds Richarch’s wallet of the day to the foot-of-the-foot-of-the-driveway where the agent lurks. The reason for this is that the wallet mustn’t be removed from its seat, for Richarch will go into withdrawal should it ever leave his side.

        “But I must fight back,” the large-and-in-charge entrepreneur mutters. He can’t have all 13 billion cash dollars in his wallet of the day nicked away by the malicious enemy. He must hire allies to march to the front lines at the foot-of-the-foot-of-the driveway and beat sense into the agent– verbally, of course.
        """);
    pressEnterToContinue();
    System.out.println("""
        To the allies, one and all: the call of a long-forged bond speaks to them all!

        The Lawyer, Chieftain of the (Paid-Off) Justice!

        The Ever-Brilliant Financial Advisor!

        The ‘Son,’ Faux-Filial Companion!

        And the Secretary, the All Knowing!

        Like a queen bee to her hive, Richarch delegates the menial bickering about taxes to his hired hands. Let the games begin.
        """);
    pressEnterToContinue();
    System.out.println("""
        ABOUT BATTLES

        The game consists of three battles at the foot-of-the-foot-of-the-driveway. Each battle starts with just Richarch and an IRS agent. Richarch himself has no means of attacking. Instead, he manages allies. On his turn, you can: a) summon an ally, b) pay an ally, or c) discharge an ally. \s

        Everything Richarch does costs various sums of personal money from his wallet. Sure, what is in his wallet is but a fraction of his total stash, but Richarch is stingy and you will fail if all the wallet money goes down the drain! Richarch starts with 1,400,000,000 dollars. That means ‘1.4 billion’ for the lazy people. Each day, before each fight, he adds 1,000,000,000 more to his wallet.\s

        If you’re too lazy to figure out what that number is, then you should probably stop playing this game and get some help.
        """);
    pressEnterToContinue();
    System.out.println(
        "Whenever you summon an ally, they will ask for a sum between 1 to 2 billion. This is subtracted from Richarch’s wallet. The ally will pocket some of it (dastardly people), and then the rest of the money will be on their display. If they ever run out of money, they will get immediately discharged. Playtesters said “money is basically your health stat,” but I don't play games so I don't know what that means. \n");
    pressEnterToContinue();
    System.out.println(
        "Each round, Richarch and each of his allies take one turn, and then the IRS agent takes their turn. You can have up to three allies summoned at one time. Your sanity damage dealt to the IRS agent by your allies each round will in turn determine how much money the agent extorts when their turn comes around. Make a lot of arguments, whether Emotional, Logical or Threatening, and the more damage you do with them, the less money will get taken from you. ");
    pressEnterToContinue();
    System.out.println("""
        Richarch has a king of the hill mentality, so any money extorted by the agent comes straight from a random ally’s pay. Whoopsie! Make sure an ally doesn't lose all their money, because if they do, they will leave and give bad word of mouth to future allies.\s

        For more info, type /info. If you would like to skip a turn type 'skip' and if you would like to quit a turn type 'q' or 'quit'.
        """);

  }
}
