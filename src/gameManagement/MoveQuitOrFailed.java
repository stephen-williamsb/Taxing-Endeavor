package gameManagement;

public class MoveQuitOrFailed extends Exception {

  public MoveQuitOrFailed(String errorMessage) {
    super(errorMessage);
  }
}
