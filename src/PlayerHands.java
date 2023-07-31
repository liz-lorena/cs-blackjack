






public class PlayerHands
{
  int score;
  double bankBalance;
  double bet;
  String name;
  boolean lostGame;
  boolean wonGame;
  boolean down;
  boolean split;
  int splitScore;
  double splitBet;
  boolean splitLost;
  boolean splitWon;
  boolean splitDown;
  boolean insurance;
  
  public PlayerHands(String name, double balance, int score, double bet, boolean lost, boolean won, boolean down, boolean split, int splitScore, boolean splitLost, boolean splitWon, double splitBet, boolean splitDown, boolean insurance)
  {
    this.name = name;
    bankBalance = balance;
    this.score = score;
    this.bet = bet;
    lostGame = lost;
    wonGame = won;
    this.down = down;
    this.insurance = insurance;
    this.split = split;
    this.splitScore = splitScore;
    this.splitBet = splitBet;
    this.splitLost = splitLost;
    this.splitWon = splitWon;
    this.splitDown = splitDown;
  }
  


  public String toString()
  {
    return String.format("Player: " + getName() + ", Current Bank: $" + getBalance() + ", Score: " + getScore() + ", Current Bet: $" + getBet(), new Object[0]);
  }
  

  public void setScore(int score)
  {
    this.score = score;
  }
  


  public void setBalance(double balance)
  {
    bankBalance = balance;
  }
  


  public void setBet(double bet)
  {
    this.bet = bet;
  }
  


  public void setWon(boolean won)
  {
    wonGame = won;
  }
  


  public void setLost(boolean lost)
  {
    lostGame = lost;
  }
  


  public void setDoubleDown(boolean down)
  {
    this.down = down;
  }
  


  public void setSplit(boolean split)
  {
    this.split = split;
  }
  


  public void setSplitScore(int splitScore)
  {
    this.splitScore = splitScore;
  }
  

  public void setSplitBet(double splitBet)
  {
    this.splitBet = splitBet;
  }
  

  public void setSplitWon(boolean splitWon)
  {
    this.splitWon = splitWon;
  }
  

  public void setSplitLost(boolean splitLost)
  {
    this.splitLost = splitLost;
  }
  

  public void setSplitDown(boolean splitDown)
  {
    this.splitDown = splitDown;
  }
  


  public void setIsInsured(boolean insurance)
  {
    this.insurance = insurance;
  }
  

  public String getName()
  {
    return name;
  }
  
  public double getBalance()
  {
    return bankBalance;
  }
  
  public int getScore()
  {
    return score;
  }
  
  public double getBet()
  {
    return bet;
  }
  

  public boolean getLost()
  {
    return lostGame;
  }
  

  public boolean getWon()
  {
    return wonGame;
  }
  

  public boolean getDoubleDown()
  {
    return down;
  }
  


  public boolean getSplit()
  {
    return split;
  }
  


  public int getSplitScore()
  {
    return splitScore;
  }
  

  public double getSplitBet()
  {
    return splitBet;
  }
  

  public boolean getSplitLost()
  {
    return splitLost;
  }
  
  public boolean getSplitWon()
  {
    return splitWon;
  }
  
  public boolean getSplitDown()
  {
    return splitDown;
  }
  

  public boolean getIfInsured()
  {
    return insurance;
  }
  


  public double winMoney(double money)
  {
    bankBalance += money;
    return bankBalance;
  }
  

  public double loseMoney(double money)
  {
    bankBalance -= money;
    return bankBalance;
  }
}
