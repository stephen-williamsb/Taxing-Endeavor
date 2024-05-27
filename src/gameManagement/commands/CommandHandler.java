package gameManagement.commands;

import java.util.ArrayList;

public class CommandHandler {

  private ArrayList<TextCommand> textCommandList;

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
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Possible commands are: \n");
    for (TextCommand command : textCommandList) {
      stringBuilder.append(command.getName()).append("\n");
    }
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
      if (input.equals(command.getName())) {
        command.printContents();
        return true;
      }
    }
    return false;
  }
}
