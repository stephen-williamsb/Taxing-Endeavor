package gameManagement;

public class Billion {
  private double cash;

  public Billion(double cash){
    this.cash =cash;
  }
  @Override
  public String toString(){
    return "$" + cash + " billion";
  }
  public void sub(double amount){
    cash-=amount;
  }
  public void add(double amount){
    cash+=amount;
  }
  public double getCash(){
    return cash;
  }

}
