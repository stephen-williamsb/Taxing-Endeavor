package gameManagement.commands;

public class TextCommand {
  private String name;
  private String contents;
  public TextCommand(String name, String contents){
    this.name = name;
    this.contents = contents;
  }
  /**
   * executes command
   */
  public void printContents(){
    System.out.println(contents);
    }

  /**
   * gives the name of the command
   *
   * @return the commands name as a string
   */
  public String getName(){
    return name;
  }
}
