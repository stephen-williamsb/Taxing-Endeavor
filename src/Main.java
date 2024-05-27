import Other.GameManager;
import allies.Richarch;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello world!");
    GameManager manager = new GameManager();
    Richarch rich = new Richarch(manager);
    rich.startTurn();
    manager.printPartyCheck();
    System.out.println(rich.currentMoney());
  }

}