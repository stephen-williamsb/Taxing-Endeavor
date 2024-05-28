package gameManagement.commands;

import java.util.ArrayList;

public class CommandHandler {

  private final ArrayList<TextCommand> textCommandList;

  public CommandHandler() {
    textCommandList = new ArrayList<>();
    textCommandList.add(new TextCommand("/info allymoney",
        "Ally Money essentially functions like health in other turn based games in that the "
            + "agent will subtract money from your allies each time their turn comes. But apart from "
            + "that, there are some added twists, like how discharging interacts with money, and that "
            + "money is siphoned from Richarch– with tax. "));

    textCommandList.add(new TextCommand("/info discharge", "Discharging allies: \n"
        + "\n"
        + "When Richarch discharges an ally, the ally leaves the party. The higher their pay when discharged, the happier they will be. The lower their pay when discharged, the angrier they will be. Allies that go broke and leave are the angriest.\n"
        + "\n"
        + "The more angry allies you discharge, the higher % of money each new summon will pocket. For example, if you summon a Secretary for 1.4bil (remember, the cost of summoning is random between 1bil-2bil) it might normally start with 1.23bil money from pocketing some of it, but if you have had many angry discharges, it might start with 1bil. Lastly, the more allies you discharge happily, the lower the % each newly summoned ally will take.\n"
        + "\n"
        + "This carries over each fight.\n"));

    textCommandList.add(new TextCommand("/round structure",
        "Each round begins with Richarch’s turn, then proceeds to the next ally until there are no more allies. Then, the IRS agent goes."));

    textCommandList.add(new TextCommand("/RicharchMoves",
        "Summoning an ally: Choose an ally to summon, then they take 1-2 billion out of your money pool to create their own.\n"
            + "\n"
            + "Paying an ally: The chosen ally is given money from your pool equal to half their maximum money. If the ally is already over half their money, all excess money gained past its maximum is lost.\n"
            + "\n"
            + "Discharging an ally:\n"
            + "\n"
            + "When Richarch discharges an ally, the ally leaves the party. The higher their pay when"
            + " discharged, the happier they will be. The lower their pay when discharged, the angrier they will be. Allies that go broke and leave are the angriest. See /WordOfMouth.\n"));

    textCommandList.add(new TextCommand("/SecretaryMoves",
        "Dig Up Dirt: “The secretary digs up dirt with a megaton shovel! This is some real good ammunition! The next Blackmail type move will deal double damage!”\n"
            + "\n"
            + " (60 damage. Neutral element. Doubles the next Blackmail element attack’s damage.)\n"
            + "\n"
            + "Analyze Agent: “Through scrutinous use of a magnifying glass, the secretary deduces that the IRS Agent’s current weakness is (current weakness) and current resistance (current resistance)!”\n"
            + "(20 damage. Logic element. Reveals the IRS agent’s current weakness and resistance.) \n"));

    textCommandList.add(new TextCommand("/SonMoves",
        " (random age from 3 to 13 is pulled. for “It’s a family business!”-- 3-5: 10 damage, 6-8: 50 damage, 9-10: 60 damage, 10-13: 110 damage, and 14-15: 160 damage) \n"
            + "Special summoning text: “A servant raced to the orphanage and brought over a (x) year old!\n"
            + "\n"
            + "But it's a family business!: \n"
            + "If 3-5 years old: “The toddler babbles incoherently!”\n"
            + "6-8: “The kid’s bad acting makes it obvious he’s not Richarch’s real son, but he’s trying dammit!”\n"
            + "9-10: “The child delivers a heartwarming speech on the importance of family business!”\n"
            + "10-13: “The youth’s honest treatise brings a tear to the agent’s eye!”\n"
            + "14-15: “The young man’s gut-wrenching oration on the psychological impact of over-exposing kids to taxes has the agent in shambles!”\n"
            + "\n"
            + "10/60/110/160 emotional damage, based on age\n"
            + "\n"
            + "Study hard: “The boy undergoes a training montage and grows up by one year! He’s now (x) years old! (increase Son’s age by 1.)\n"));

    textCommandList.add(new TextCommand("/LawyerMoves",
        "I'm the Important One Here: “The lawyer oozes charisma: “Listen up here, eyes on me, I’ll take care of this.” Due to his overwhelming presence, the IRS agent is forced to only target the lawyer on their next turn.”\n"
            + "(60 emotional damage. The agent’s next attack will hit the lawyer and no one else.)\n"
            + "\n"
            + "Damning Evidence: 120 blackmail damage. Fails if Blackmail is not the agent’s current weakness. \n"
            + "\n"
            + "Lawyer Lariat: “It’s a bird! It’s a plane! No, it’s a lawyer?! The lawyer’s mega-slam is so intense it is an entirely random element! This time it’s (element)!”\n"
            + "(70 damage, random element.)\n"));

    textCommandList.add(new TextCommand("/FinancialAdvisorMoves",
        "Tax Writeoff: “The financial advisor knows that what the world needs is another finished movie to get wiped from the planet. Because TAX.” \n"
            + "(80 logic damage. )\n"
            + "\n"
            + "Creating Money: “The financial advisor’s answer to the screaming, painful cries of their allies is to get neck-deep into paperwork. Ba-boom and cha-ching! All allies receive (x) money.”\n"
            + "(Heal every ally’s money stat by 15% of their max money.)\n"));

    textCommandList.add(new TextCommand("/IRSAgentInfo",
        "The IRS agent will take around .6 billion dollars from a random ally on each of its turns. The agent also has a randomly assigned strength and weakness from the three types of damage (logic, blackmail, emotion). Every four times a strong move is used on them, both strength and weakness will randomly change.\n"
            + "\n"
            + "The day 1 IRS agent has 1000 sanity points (health). The day 2 agent has 1300, and day 3 has 1600.\n"));

    textCommandList.add(new TextCommand("/GameStructure",
        "The game takes place over three days, arguing against three IRS agents. Richarch starts the first confrontation with 14 billion dollars, and each subsequent day adds 10 billion to his wallet. Try to beat the game using as little money as possible.\n"));

    textCommandList.add(new TextCommand("/WordOfMouth",
        "Whenever you discharge an ally or they run out of pay, they will affect your word of mouth score. This in turn affects how little or how much money each new summoned ally will pocket.\n"
            + "The more angry allies you discharge, the higher % of money each new summon will pocket. For example, if you summon a Secretary for 1.4bil (remember, the cost of summoning is random between 1bil-2bil) it might normally start with 1.23bil money from pocketing some of it, but if you have had many angry discharges, it might start with 1bil.\n"
            + "\n"
            + " Lastly, the more allies you discharge happily, the lower the % each newly summoned ally will take. It is entirely possible to have such good word of mouth that allies will *create* more money when summoned.\n"
            + "\n"
            + "This does NOT reset each day.\n"));

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Possible commands are: \n");
    for (TextCommand command : textCommandList) {
      stringBuilder.append(command.getName()).append("\n");
    }
    stringBuilder.append("/party\n/partycheck\n");
    textCommandList.add(new TextCommand("/info", stringBuilder.toString()));
    textCommandList.add(new TextCommand("/info help", stringBuilder.toString()));
  }

  /**
   * Checks if the input is a command known by this class
   *
   * @param input the string command the user has given
   * @return true if a command was found and used
   */
  public boolean handleInput(String input) {
    input = input.toLowerCase();
    for (TextCommand command : textCommandList) {
      if (input.equals(command.getName().toLowerCase())) {
        command.printContents();
        return true;
      }
    }
    return false;
  }
}
