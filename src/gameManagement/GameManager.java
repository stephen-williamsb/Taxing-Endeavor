package gameManagement;

import allies.Advisor;
import allies.Ally;
import allies.AllyClass;
import allies.Lawyer;
import allies.Richarch;
import allies.Secretary;
import allies.Son;
import enemies.DayOneEnemy;
import enemies.Enemy;
import gameManagement.commands.CommandHandler;


public class GameManager {

  private Ally[] allyParty;
  private Enemy currentFoe;
  private int day;
  private CommandHandler commandHandler;
  private final Billion richarchStartCash = new Billion(4);
  private final Billion richarchDailyGains = new Billion(10);


  public GameManager() {
    commandHandler = new CommandHandler();
    day = 1;
    allyParty = new Ally[4];
    allyParty[0] = new Richarch(richarchStartCash, this);
    StartGame();
  }

  private Enemy GetFoeAtDay(int day) {
    return switch (day) {
      case 1 -> new DayOneEnemy(this);
      case 2 -> null;
      case 3 -> null;
      default -> null;
    };
  }

  public void StartGame() {
    introText();
    firstDayText();
    allyParty[0].startTurn();
    day++;
  }

  public Ally getAllyAt(int pos) {
    return allyParty[pos - 1];
  }

  public void createAlly(AllyClass ally, Billion startCash) {
    Ally allyCreated;
    switch (ally) {
      case Son -> allyCreated = new Son(startCash);
      case Secretary -> allyCreated = new Secretary(startCash);
      case Lawyer -> allyCreated = new Lawyer(startCash);
      case Advisor -> allyCreated = new Advisor(startCash);
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
      System.out.println("[" + (i + 1) + "]: " + allyParty[i].getAllyType());
    }
  }

  public void printPartyCheck() {
    for (int i = 0; i < allyParty.length; i++) {
      if (allyParty[i] == null) {
        System.out.println("[" + (i + 1) + "]: empty");
        continue;
      }
      System.out.println(
          "[" + (i + 1) + "]: " + allyParty[i].getAllyType() + "   |   Current " + "cash: "
              + allyParty[i].currentMoney());
    }
  }

  private void pressEnterToContinue() {
    System.out.println("[Press Enter key to continue...]");
    try {
      System.in.read();
    } catch (Exception ignored) {
    }
  }

  private void introText() {
    System.out.print(
        "Welcome to [Game Name Pending]. In this game, you (the good high class guy) engage in "
            + "verbal sparring with the bad guys (IRS agents). Three encounters, three days: Hire "
            + "allies like financial advisors and secreted-away sons to scare off the agents…while "
            + "paying said allies as little of your hard-won (hedge funds are complicated, man) "
            + "money as possible. \n");
    pressEnterToContinue();
    System.out.print("Why don't we get started with introducing the cas–\n" + "\n"
        + "*bzzzrt.* *bzzzrt.* new message for: Narrator 1. One kind patron on the cast list has paid for all the cast slots (and more). Emphasis on the (and more). They want to be the only one on the cast list… oblige and we can spread some of this dough around, ‘kay? \n"
        + "From: Narrator Council\n" + "\n" + "Hmm… \n" + "\n"
        + "WA-WA-wait, dear player, haha, you didn't see anything! About what I just said; cast list– ahem– I didn't mean cast, I meant *class,* because this is a Class-One, A-List *individual.*\n");
    pressEnterToContinue();
    System.out.println(
        "Relaxing in the spa by day, and sleeping soundly by night…  give it up for entrepreneur Richarch Rich, fondly known as “patriarch Richarch” by his indentured servants! They don't even know they're mispronouncing “Richarch” because they're not educated!\n"
            + "\n"
            + "And the others aren't as important, because they all revolve around Richarch and his pile of money anyway! The real reason Splash Mountain closed was because it paled in comparison to his STASH MOUNTAIN! The other characters are so unimportant, that the game takes place not only at the entrance to Richarch’s driveway, below His Holiness, but the ENTRANCE TO THE ENTRANCE to the driveway! I'm really making this sound appealing, aren't I! Let's get a move on! \n");
    pressEnterToContinue();
  }

  private void firstDayText() {
    System.out.println("""
        DAY 1

        Richarch settles into his custom-built spa chair, loosening his muscles to fill the mold– chiseled generously after the shape of the equally generous man– like liquid. Since there is no room for earthly possessions on his holy throne, he whispers sweet words to his wallet before placing it lovingly into its own mold, only a hair’s breadth away from his fingers yet multiple feet away from his heart, and I mean that literally.\s
        """);
    pressEnterToContinue();
    System.out.println(
        "Suddenly, a raging bout of thundering noise disrupts the voluptuous man's very important thoughts: something between a ring-a-ding-ding and hum-buzz, yet louder than both; it comes from the nearby intercom! \n"
            + "\n"
            + "Moments earlier, a servant of undisclosed age with brilliant ears had heard the crackle-pop of the silver box’s startup, its metaphorical breath before the literal screech, and had zip-dashed to the speaker lest the screech become TRULY literal. \n"
            + "\n"
            + "But dear player (or reader, at this point), from the twice-above paragraph we already know this lithe little bat of a servant’s effort is in vain! For he does not get to the shiny speaking-box in time to receive the message personally and, especially, for the sake of his already meager pay, *quietly.*\n");
    pressEnterToContinue();
    System.out.println(
        "“SERVAAAANTS!” Bellows the patriarch with no blood ties. This cry and the shrieking of the intercom join forces to create a magnitude-one aural earthquake. Every impoverished soul in the room knows they are now Impoverished with a capital I.\n"
            + "\n"
            + "Despite the setback, our bat-boy does the rest of his job with due diligence and presses the receiving-end button, popping his shoulder in reaching it but reaching it all the same.\n");
    System.out.println(
        "“A word for Mr. Rich,” comes a cool voice, “but Mr. Rich is busy,” squeaks bat-boy, “I will be his relay.” Poor choice of words; as if by karma he *literally* becomes Richarch’s relay: From hereon every crumb of cash the IRS agent–for that is who spoke so cooly– sequesters from Richarch will be personally delivered to the agent by bat-boy, from the spa that holds Richarch’s wallet of the day to the foot-of-the-foot-of-the-driveway where the agent lurks. The reason for this is that the wallet mustn’t be removed from its seat, for Richarch will go into withdrawal should it ever leave his side.\n"
            + "\n"
            + "“But I must fight back,” the large-and-in-charge entrepreneur mutters. He can’t have all 13 billion cash dollars in his wallet of the day nicked away by the malicious enemy. He must hire allies to march to the front lines at the foot-of-the-foot-of-the driveway and beat sense into the agent– verbally, of course.\n");
    pressEnterToContinue();
    System.out.println(
        "To the allies, one and all: the call of a long-forged bond speaks to them all!\n"
            + "\n"
            + "The Lawyer, Chieftain of the (Paid-Off) Justice!\n"
            + "\n"
            + "The Ever-Brilliant Financial Advisor!\n"
            + "\n"
            + "The ‘Son,’ Faux-Filial Companion!\n"
            + "\n"
            + "And the Secretary, the All Knowing!\n"
            + "\n"
            + "Like a queen bee to her hive, Richarch delegates the menial bickering about taxes to his hired hands. Let the games begin.\n");
    pressEnterToContinue();
    System.out.println("ABOUT BATTLES\n"
        + "\n"
        + "The game consists of three battles at the foot-of-the-foot-of-the-driveway. Each battle starts with just Richarch and an IRS agent. Richarch himself has no means of attacking. Instead, he manages allies. On his turn, you can: a) summon an ally, b) pay an ally, or c) discharge an ally.  \n"
        + "\n"
        + "Everything Richarch does costs various sums of personal money from his wallet. Sure, what is in his wallet is but a fraction of his total stash, but Richarch is stingy and you will fail if all the wallet money goes down the drain! Richarch starts with 1,400,000,000 dollars. That means ‘1.4 billion’ for the lazy people. Each day, before each fight, he adds 1,000,000,000 more to his wallet. \n"
        + "\n"
        + "If you’re too lazy to figure out what that number is, then you should probably stop playing this game and get some help.\n");
    pressEnterToContinue();
    System.out.println(
        "Whenever you summon an ally, they will ask for a sum between 1 to 2 billion. This is subtracted from Richarch’s wallet. The ally will pocket some of it (dastardly people), and then the rest of the money will be on their display. If they ever run out of money, they will get immediately discharged. Playtesters said “money is basically your health stat,” but I don't play games so I don't know what that means. \n");
    pressEnterToContinue();
    System.out.println(
        "Each round, Richarch and each of his allies take one turn, and then the IRS agent takes their turn. You can have up to three allies summoned at one time. Your sanity damage dealt to the IRS agent by your allies each round will in turn determine how much money the agent extorts when their turn comes around. Make a lot of arguments, whether Emotional, Logical or Threatening, and the more damage you do with them, the less money will get taken from you. ");
    pressEnterToContinue();
    System.out.println(
        "Richarch has a king of the hill mentality, so any money extorted by the agent comes straight from a random ally’s pay. Whoopsie! Make sure an ally doesn't lose all their money, because if they do, they will leave and give bad word of mouth to future allies. \n"
            + "\n"
            + "For more info, type /info.\n");

  }
}
