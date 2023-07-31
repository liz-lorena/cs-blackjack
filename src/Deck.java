import java.io.PrintStream;






public class Deck
{
  private Card[] cards;
  int cardsUsed;
  int i;
  
  public Deck()
  {
    i = 51;
    cards = new Card[52];
    int x = 0;
    
    for (int a = 1; a <= 4; a++)
    {
      for (int b = 1; b <= 13; b++) {
        cards[x] = new Card(a, b);
        x++;
      }
    }
  }
  
  public void printDeckTest()
  {
    for (int i = 0; i < cards.length; i++) {
      System.out.println(cards[i].toString());
    }
  }
  

  public Card deal()
  {
    Card cardBeingDealt = null;
    cardsUsed += 1;
    if (cardsUsed <= 52) {
      cardBeingDealt = cards[(cardsUsed - 1)];
    }
    return cardBeingDealt;
  }
  

  public boolean isEmpty()
  {
    boolean isEmpty = false;
    
    if (cardsUsed >= 52) {
      isEmpty = true;
    }
    
    return isEmpty;
  }
  
  public void shuffle()
  {
    for (int i = 0; i < cards.length; i++) {
      int index = (int)(Math.random() * cards.length);
      
      Card temp = cards[i];
      cards[i] = cards[index];
      cards[index] = temp;
    }
  }
  

  public void nextRound()
  {
    cardsUsed = 0;
  }
}
