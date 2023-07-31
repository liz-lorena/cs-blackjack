







public class Card
{
  public static final int SPADES = 0;
  public static final int HEARTS = 1;
  public static final int CLUBS = 2;
  public static final int DIAMONDS = 3;
  public static final int ACE = 1;
  public static final int TWO = 2;
  public static final int THREE = 3;
  public static final int FOUR = 4;
  public static final int FIVE = 5;
  public static final int SIX = 6;
  public static final int SEVEN = 7;
  public static final int EIGHT = 8;
  public static final int NINE = 9;
  public static final int TEN = 10;
  public static final int JACK = 11;
  public static final int QUEEN = 12;
  public static final int KING = 13;
  int suit;
  int face;
  int value;
  private static String[] suits = { "Hearts", "Spades", "Diamonds", "Clubs" };
  private static String[] faces = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
  

  public Card(int cardSuit, int cardFace)
  {
    suit = cardSuit;
    face = cardFace;
  }
  
  public String toString()
  {
    return faces[(face - 1)] + " of " + suits[(suit - 1)];
  }
  

  public int getFace()
  {
    return face;
  }
  

  public int getSuit()
  {
    return suit;
  }
  


  public int getValue()
  {
    if (face == 1) value = 1;
    if (face == 2) value = 2;
    if (face == 3) value = 3;
    if (face == 4) value = 4;
    if (face == 5) value = 5;
    if (face == 6) value = 6;
    if (face == 7) value = 7;
    if (face == 8) value = 8;
    if (face == 9) value = 9;
    if (face == 10) value = 10;
    if (face == 11) value = 10;
    if (face == 12) value = 10;
    if (face == 13) value = 10;
    return value;
  }
}
