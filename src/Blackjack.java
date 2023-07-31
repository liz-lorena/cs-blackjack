import java.io.PrintStream;

public class Blackjack {
  public Blackjack() {}
  
  public static void main(String[] args) { 
    System.out.println("\n    _/_/_/    _/          _/_/      _/_/_/  _/    _/   ");
    System.out.println("   _/    _/  _/        _/    _/  _/        _/  _/      ");
    System.out.println("  _/_/_/    _/        _/_/_/_/  _/        _/_/         ");
    System.out.println(" _/    _/  _/        _/    _/  _/        _/  _/        ");
    System.out.println("_/_/_/    _/_/_/_/  _/    _/    _/_/_/  _/    _/       ");
    System.out.println("\n\n");
    System.out.println("         _/    _/_/      _/_/_/  _/    _/   ");
    System.out.println("        _/  _/    _/  _/        _/  _/      ");
    System.out.println("       _/  _/_/_/_/  _/        _/_/         ");
    System.out.println("_/    _/  _/    _/  _/        _/  _/        ");
    System.out.println(" _/_/    _/    _/    _/_/_/  _/    _/       ");
    System.out.println("\n\n");
    
    System.out.println("\n@irisoflys on Github");
    System.out.println("ascii font lean from http://www.network-science.de/ascii/");
    Deck deck = new Deck();
    System.out.println("\nType Y for Instructions or any other character to continue");
    char instr = IO.readChar();
    if ((instr == 'Y') || (instr == 'y')) {
      System.out.println("\nTake a minute to peruse the rules of the game provided by this website here: \nhttp://www.gambling-systems.com/blackjackbasics.html");
    }
    
    System.out.println("\nHow many players?");
    int numPlayers = IO.readInt();
    

    int score = 0;
    double bet = 0.0D;
    double balance = 0.0D;
    String playerName = "";
    

    boolean lost = false;
    boolean win = false;
    

    boolean down = false;
    

    boolean insurance = false;
    
    Card placeholder = null;
    int valueOfCard = 0;
    int dealerScore = 0;
    int round = 1;
    


    int countOne = 20;
    int countZero = 12;
    int countNegOne = 20;
    int countValues = 0;
    

    boolean split = false;
    int splitScore = 0;
    double splitBet = 0.0D;
    boolean splitLost = false;
    boolean splitWon = false;
    boolean splitDown = false;
    


    if ((numPlayers > 6) || (numPlayers <= 0)) {
      do
      {
        IO.reportBadInput();
        System.out.println("Max is 6 players and min is 1 player!");
        System.out.println("\nHow many players?");
        numPlayers = IO.readInt();
      }
      while ((numPlayers > 6) || (numPlayers <= 0));
    }
    
    PlayerHands[] players = new PlayerHands[numPlayers];
    
    System.out.println("\nStarting amount for all players?");
    balance = IO.readDouble();
    score = 0;
    
    System.out.println("The starting amount for all players is $" + balance + ".");
    System.out.println("The starting score for all players is 0 points.");
    

    for (int i = 0; i < numPlayers; i++)
    {
      System.out.println("\nPlayer name?");
      playerName = IO.readString();
      System.out.println("How much do you bet?");
      bet = IO.readDouble();
      
      if ((bet > balance) || (bet <= 0.0D)) {
        do {
          IO.reportBadInput();
          System.out.println("Players must make a bet, but they can not bet more than they have!");
          System.out.println("\nHow much do you bet?");
          bet = IO.readDouble();
        } while ((bet > balance) || (bet <= 0.0D));
      }
      players[i] = new PlayerHands(playerName, balance, score, bet, lost, win, down, split, splitScore, splitLost, splitWon, splitBet, splitDown, insurance);
    }
    




    System.out.println("\nROUND " + round);
    
    System.out.println("\nThe players: ");
    
    for (int i = 0; i < players.length; i++) {
      System.out.println(i + 1 + ". " + players[i]);
    }
    
    System.out.println("\nEnter any character to continue...");
    char waiting = IO.readChar();
    
    System.out.println("\nShuffling the deck...");
    deck.shuffle();
    
    for (int j = 0; j < players.length; j++)
    {
      score = 0;
      System.out.println("\nDealing two cards to player " + players[j].getName() + "...");
      placeholder = deck.deal();
      countValues = placeholder.getValue();
      if ((countValues == 7) || (countValues == 8) || (countValues == 9))
      {
        countZero--;
      }
      
      if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
      {
        countOne--;
      }
      
      if ((countValues == 1) || (countValues == 10))
      {
        countNegOne--;
      }
      
      System.out.println("Player received " + placeholder);
      
      valueOfCard = placeholder.getValue();
      if (valueOfCard == 1) score += 11; else
        score += valueOfCard;
      players[j].setScore(score);
      
      placeholder = deck.deal();
      countValues = placeholder.getValue();
      
      if ((countValues == 7) || (countValues == 8) || (countValues == 9))
      {
        countZero--;
      }
      

      if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
      {
        countOne--;
      }
      

      if ((countValues == 1) || (countValues == 10))
      {
        countNegOne--;
      }
      
      System.out.println("Player received " + placeholder);
      
      if ((placeholder.getValue() == valueOfCard) && (players[j].getBet() <= players[j].getBalance() / 2.0D)) {
        System.out.println("\nPlayer received two cards of the same value and has enough to split the bet. Split[S] or not[any key]?");
        char splitResponse = IO.readChar();
        
        if ((splitResponse == 'S') || (splitResponse == 's'))
        {
          splitScore = 0;
          players[j].setSplitBet(players[j].getBet());
          
          System.out.println("Player " + players[j].getName() + " splits cards into two hands.");
          players[j].setSplit(true);
          if (placeholder.getValue() == 1) splitScore += 11; else
            splitScore += placeholder.getValue();
          players[j].setSplitScore(splitScore);
          

          placeholder = deck.deal();
          countValues = placeholder.getValue();
          
          if ((countValues == 7) || (countValues == 8) || (countValues == 9))
          {
            countZero--;
          }
          

          if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
          {
            countOne--;
          }
          

          if ((countValues == 1) || (countValues == 10))
          {
            countNegOne--;
          }
          System.out.println("Player received " + placeholder + " for first hand.");
          valueOfCard = placeholder.getValue();
          if ((valueOfCard == 1) && (players[j].getScore() != 11)) score += 11; else
            score += valueOfCard;
          players[j].setScore(score);
          System.out.println("Player " + players[j].getName() + "'s first hand score is currently " + players[j].getScore());
          

          placeholder = deck.deal();
          countValues = placeholder.getValue();
          if ((countValues == 7) || (countValues == 8) || (countValues == 9))
          {
            countZero--;
          }
          

          if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
          {
            countOne--;
          }
          

          if ((countValues == 1) || (countValues == 10))
          {
            countNegOne--;
          }
          System.out.println("Player received " + placeholder + " for second hand.");
          valueOfCard = placeholder.getValue();
          if ((valueOfCard == 1) && (players[j].getSplitScore() != 11)) splitScore += 11; else
            splitScore += valueOfCard;
          players[j].setSplitScore(splitScore);
          System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
          
          System.out.println("\nEnter any character to continue...");
          waiting = IO.readChar();
        }
        else {
          valueOfCard = placeholder.getValue();
          if ((valueOfCard == 1) && (score != 11)) valueOfCard = 11;
          score += valueOfCard;
          players[j].setScore(score);
          
          System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
          
          System.out.println("\nEnter any character to continue...");
          waiting = IO.readChar();
        }
      }
      else
      {
        System.out.println("Player " + players[j].getName() + " not eligible for a split.");
        valueOfCard = placeholder.getValue();
        if ((valueOfCard == 1) && (score != 11)) valueOfCard = 11;
        score += valueOfCard;
        players[j].setScore(score);
        
        System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
        
        System.out.println("\nEnter any character to continue...");
        waiting = IO.readChar();
      }
    }
    



    System.out.println("\nDealer dealing two cards to himself...");
    placeholder = deck.deal();
    countValues = placeholder.getValue();
    if ((countValues == 7) || (countValues == 8) || (countValues == 9))
    {
      countZero--;
    }
    

    if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
    {
      countOne--;
    }
    

    if ((countValues == 1) || (countValues == 10))
    {
      countNegOne--;
    }
    if (placeholder.getValue() == 1) dealerScore += 11; else
      dealerScore += placeholder.getValue();
    System.out.println("Dealer received " + placeholder);
    
    placeholder = deck.deal();
    countValues = placeholder.getValue();
    if ((countValues == 7) || (countValues == 8) || (countValues == 9))
    {
      countZero--;
    }
    

    if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
    {
      countOne--;
    }
    

    if ((countValues == 1) || (countValues == 10))
    {
      countNegOne--;
    }
    Card hiddenCard = placeholder;
    System.out.println("Dealer received [card face down].");
    System.out.println("Dealer's current score is unknown.");
    
    System.out.println("\nEnter any character to continue...");
    waiting = IO.readChar();
    

    if (dealerScore == 11) {
      System.out.println("\nDealer's known card is an Ace.");
      
      for (int i = 0; i < players.length; i++)
      {
        System.out.println("Player " + players[i].getName() + ", take insurance bet on whether dealer's face-down card is valued 10[Y]?");
        char insured = IO.readChar();
        
        if ((insured == 'Y') || (insured == 'y')) {
          players[i].setIsInsured(true);
        }
      }
    }
    






    System.out.println("\nPlayer hit-or-stand begins...");
    


    for (int j = 0; j < players.length; j++)
    {
      System.out.println("\nPlayer " + players[j].getName() + "'s score is currently " + players[j].getScore());
      if (players[j].getSplit()) {
        System.out.println("And player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
      }
      

      System.out.println("\n" + players[j].getName() + ", play[P], or Stand[any key]?");
      System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
      System.out.println("Hint[Y]?");
      char hint = IO.readChar();
      if ((hint == 'Y') || (hint == 'y')) {
        System.out.println("\nHintBot is thinking...");
        System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
        System.out.println("Choose wisely: ");
      } else { System.out.println("Choose wisely: "); }
      char response = IO.readChar();
      
      if ((response == 'P') || (response == 'p'))
      {
        System.out.println("\nDouble Down[D] or Hit[any key]?");
        char downResponse = IO.readChar();
        
        if ((players[j].getBet() + players[j].getSplitBet() == players[j].getBalance()) || (players[j].getBet() >= players[j].getBalance() - players[j].getSplitBet()))
        {
          System.out.println("\nPlayer " + players[j].getName() + " not eligible for doubling down due to limited balance.");
          downResponse = 'n';
        }
        if ((downResponse == 'D') || (downResponse == 'd')) {
          players[j].setDoubleDown(true);
          System.out.println("Old bet: $" + players[j].getBet());
          if (players[j].getSplit()) System.out.println("Second hand bet: $" + players[j].getSplitBet());
          System.out.println("Current Balance: $" + players[j].getBalance());
          System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getSplitBet()));
          System.out.println("What is your new bet?");
          bet = IO.readDouble();
          if ((bet <= players[j].getBet()) || (bet > players[j].getBalance() - players[j].getSplitBet())) {
            do
            {
              IO.reportBadInput();
              System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
              System.out.println("\nWhat is your new bet?");
              bet = IO.readDouble();
            } while ((bet <= players[j].getBet()) || (bet > players[j].getBalance() - players[j].getSplitBet()));
          }
          
          players[j].setBet(bet);
        }
        
        placeholder = deck.deal();
        countValues = placeholder.getValue();
        if ((countValues == 7) || (countValues == 8) || (countValues == 9))
        {
          countZero--;
        }
        

        if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
        {
          countOne--;
        }
        

        if ((countValues == 1) || (countValues == 10))
        {
          countNegOne--;
        }
        System.out.println("Player received " + placeholder);
        valueOfCard = placeholder.getValue();
        if ((placeholder.getValue() == 1) && (players[j].getScore() + 11 <= 21)) {
          valueOfCard = players[j].getScore() + 11;
        } else
          valueOfCard = players[j].getScore() + placeholder.getValue();
        players[j].setScore(valueOfCard);
        System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
        
        if (players[j].getScore() > 21) {
          System.out.println("Player " + players[j].getName() + " BUSTED!");
          players[j].setLost(true);
        }
        

        if ((!players[j].getLost()) || (players[j].getSplit())) {
          if ((players[j].getLost()) && (players[j].getSplit()))
          {


            System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
            
            System.out.println("\n" + players[j].getName() + ", play[P] your second hand, or Stand[any key]?");
            System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
            System.out.println("Hint[Y]?");
            hint = IO.readChar();
            if ((hint == 'Y') || (hint == 'y')) {
              System.out.println("\nHintBot is thinking...");
              System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
              System.out.println("Choose wisely: ");
            } else { System.out.println("Choose wisely: "); }
            response = IO.readChar();
            
            if ((response != 'P') && (response != 'p'))
              continue;
            System.out.println("\nDouble Down[D] or Hit[any key]?");
            downResponse = IO.readChar();
            if ((players[j].getSplitBet() + players[j].getBet() == players[j].getBalance()) || (players[j].getSplitBet() >= players[j].getBalance() - players[j].getBet()))
            {
              System.out.println("\nPlayer " + players[j].getName() + "'s second hand not eligible for doubling down due to limited balance.");
              downResponse = 'n';
            }
            if ((downResponse == 'D') || (downResponse == 'd')) {
              players[j].setSplitDown(true);
              System.out.println("Old bet: $" + players[j].getSplitBet());
              if (players[j].getSplit()) System.out.println("First hand bet: $" + players[j].getBet());
              System.out.println("Current Balance: $" + players[j].getBalance());
              System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getBet()));
              System.out.println("What is your new bet?");
              bet = IO.readDouble();
              if ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet())) {
                do
                {
                  IO.reportBadInput();
                  System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                  System.out.println("\nWhat is your new bet?");
                  bet = IO.readDouble();
                } while ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet()));
              }
              
              players[j].setSplitBet(bet);
            }
            
            placeholder = deck.deal();
            countValues = placeholder.getValue();
            if ((countValues == 7) || (countValues == 8) || (countValues == 9))
            {
              countZero--;
            }
            

            if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
            {
              countOne--;
            }
            

            if ((countValues == 1) || (countValues == 10))
            {
              countNegOne--;
            }
            System.out.println("Player received " + placeholder);
            valueOfCard = placeholder.getValue();
            if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
              valueOfCard = players[j].getSplitScore() + 11;
            } else
              valueOfCard = players[j].getSplitScore() + placeholder.getValue();
            players[j].setSplitScore(valueOfCard);
            System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
            
            if (players[j].getSplitScore() > 21) {
              System.out.println("Player " + players[j].getName() + "'s second hand BUSTED!");
              players[j].setSplitLost(true);
              continue;
            }
            
            if (players[j].getSplitDown())
            {
              System.out.println("Double down ended the play for player " + players[j].getName() + "'s second hand.");
              continue;
            }
            


            System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
            
            System.out.println("Hint[Y]?");
            hint = IO.readChar();
            if ((hint == 'Y') || (hint == 'y')) {
              System.out.println("\nHintBot is thinking...");
              System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
              System.out.println("Choose wisely: ");
            } else { System.out.println("Choose wisely: "); }
            response = IO.readChar();
            

            if ((response == 'H') || (response == 'h')) {
              do {
                placeholder = deck.deal();
                countValues = placeholder.getValue();
                if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                {
                  countZero--;
                }
                

                if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                {
                  countOne--;
                }
                

                if ((countValues == 1) || (countValues == 10))
                {
                  countNegOne--;
                }
                System.out.println("Player received " + placeholder);
                if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                  valueOfCard = players[j].getSplitScore() + 11;
                } else
                  valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                players[j].setSplitScore(valueOfCard);
                System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                
                if (players[j].getSplitScore() > 21) {
                  System.out.println("Player " + players[j].getName() + " BUSTED!");
                  players[j].setSplitLost(true);
                  break;
                }
                
                System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                System.out.println("Hint[Y]?");
                hint = IO.readChar();
                if ((hint == 'Y') || (hint == 'y')) {
                  System.out.println("\nHintBot is thinking...");
                  System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                  System.out.println("Choose wisely: ");
                } else { System.out.println("Choose wisely: ");
                }
                response = IO.readChar();
              } while ((response == 'H') || (response == 'h'));
              
              if (((response != 'H') && (response != 'h')) || (players[j].getSplitLost()))
              {
                System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                continue;
              }
            }
            else {
              System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
              continue;
            }
          }
          






          if ((players[j].getDoubleDown()) && (!players[j].getSplit()))
          {
            System.out.println("Double down ended the play for player " + players[j].getName());
          }
          else {
            if ((players[j].getDoubleDown()) && (players[j].getSplit()))
            {
              System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
              
              System.out.println("\n" + players[j].getName() + ", play[P] your second hand, or Stand[any key]?");
              System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
              System.out.println("Hint[Y]?");
              hint = IO.readChar();
              if ((hint == 'Y') || (hint == 'y')) {
                System.out.println("\nHintBot is thinking...");
                System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                System.out.println("Choose wisely: ");
              } else { System.out.println("Choose wisely: "); }
              response = IO.readChar();
              
              if ((response != 'P') && (response != 'p'))
                continue;
              System.out.println("\nDouble Down[D] or Hit[any key]?");
              downResponse = IO.readChar();
              if ((players[j].getSplitBet() + players[j].getBet() == players[j].getBalance()) || (players[j].getSplitBet() >= players[j].getBalance() - players[j].getBet()))
              {
                System.out.println("\nPlayer " + players[j].getName() + "'s second hand not eligible for doubling down due to limited balance.");
                downResponse = 'n';
              }
              if ((downResponse == 'D') || (downResponse == 'd')) {
                players[j].setSplitDown(true);
                System.out.println("Old bet: $" + players[j].getSplitBet());
                if (players[j].getSplit()) System.out.println("First hand bet: $" + players[j].getBet());
                System.out.println("Current Balance: $" + players[j].getBalance());
                System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getBet()));
                System.out.println("What is your new bet?");
                bet = IO.readDouble();
                if ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet())) {
                  do
                  {
                    IO.reportBadInput();
                    System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                    System.out.println("\nWhat is your new bet?");
                    bet = IO.readDouble();
                  } while ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet()));
                }
                
                players[j].setSplitBet(bet);
              }
              
              placeholder = deck.deal();
              countValues = placeholder.getValue();
              if ((countValues == 7) || (countValues == 8) || (countValues == 9))
              {
                countZero--;
              }
              

              if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
              {
                countOne--;
              }
              

              if ((countValues == 1) || (countValues == 10))
              {
                countNegOne--;
              }
              System.out.println("Player received " + placeholder);
              valueOfCard = placeholder.getValue();
              if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                valueOfCard = players[j].getSplitScore() + 11;
              } else
                valueOfCard = players[j].getSplitScore() + placeholder.getValue();
              players[j].setSplitScore(valueOfCard);
              System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
              
              if (players[j].getSplitScore() > 21) {
                System.out.println("Player " + players[j].getName() + "'s second hand BUSTED!");
                players[j].setSplitLost(true);
                continue;
              }
              
              if (players[j].getSplitDown())
              {
                System.out.println("Double down ended the play for player " + players[j].getName() + "'s second hand.");
                continue;
              }
              


              System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
              
              System.out.println("Hint[Y]?");
              hint = IO.readChar();
              if ((hint == 'Y') || (hint == 'y')) {
                System.out.println("\nHintBot is thinking...");
                System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                System.out.println("Choose wisely: ");
              } else { System.out.println("Choose wisely: "); }
              response = IO.readChar();
              

              if ((response == 'H') || (response == 'h')) {
                do {
                  placeholder = deck.deal();
                  countValues = placeholder.getValue();
                  if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                  {
                    countZero--;
                  }
                  

                  if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                  {
                    countOne--;
                  }
                  

                  if ((countValues == 1) || (countValues == 10))
                  {
                    countNegOne--;
                  }
                  System.out.println("Player received " + placeholder);
                  if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                    valueOfCard = players[j].getSplitScore() + 11;
                  } else
                    valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                  players[j].setSplitScore(valueOfCard);
                  System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                  
                  if (players[j].getSplitScore() > 21) {
                    System.out.println("Player " + players[j].getName() + " BUSTED!");
                    players[j].setSplitLost(true);
                    break;
                  }
                  
                  System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                  System.out.println("Hint[Y]?");
                  hint = IO.readChar();
                  if ((hint == 'Y') || (hint == 'y')) {
                    System.out.println("\nHintBot is thinking...");
                    System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                    System.out.println("Choose wisely: ");
                  } else { System.out.println("Choose wisely: ");
                  }
                  response = IO.readChar();
                } while ((response == 'H') || (response == 'h'));
                
                if (((response != 'H') && (response != 'h')) || (players[j].getSplitLost()))
                {
                  System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                  continue;
                }
              }
              else {
                System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                continue;
              }
            }
            










            System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
            
            System.out.println("Hint[Y]?");
            hint = IO.readChar();
            if ((hint == 'Y') || (hint == 'y')) {
              System.out.println("\nHintBot is thinking...");
              System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
              System.out.println("Choose wisely: ");
            } else { System.out.println("Choose wisely: "); }
            response = IO.readChar();
            
            if ((response == 'H') || (response == 'h')) {
              do {
                placeholder = deck.deal();
                countValues = placeholder.getValue();
                if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                {
                  countZero--;
                }
                

                if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                {
                  countOne--;
                }
                

                if ((countValues == 1) || (countValues == 10))
                {
                  countNegOne--;
                }
                System.out.println("Player received " + placeholder);
                if ((placeholder.getValue() == 1) && (players[j].getScore() + 11 <= 21)) {
                  valueOfCard = players[j].getScore() + 11;
                } else
                  valueOfCard = players[j].getScore() + placeholder.getValue();
                players[j].setScore(valueOfCard);
                System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
                
                if (players[j].getScore() > 21) {
                  System.out.println("Player " + players[j].getName() + " BUSTED!");
                  players[j].setLost(true);
                }
                

                if ((players[j].getLost()) && (!players[j].getSplit())) break;
                if ((players[j].getLost()) && (players[j].getSplit()))
                {

                  System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
                  
                  System.out.println("\n" + players[j].getName() + ", play[P] your second hand, or Stand[any key]?");
                  System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
                  System.out.println("Hint[Y]?");
                  hint = IO.readChar();
                  if ((hint == 'Y') || (hint == 'y')) {
                    System.out.println("\nHintBot is thinking...");
                    System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                    System.out.println("Choose wisely: ");
                  } else { System.out.println("Choose wisely: "); }
                  response = IO.readChar();
                  
                  if ((response == 'P') || (response == 'p'))
                  {
                    System.out.println("\nDouble Down[D] or Hit[any key]?");
                    downResponse = IO.readChar();
                    if ((players[j].getSplitBet() + players[j].getBet() == players[j].getBalance()) || (players[j].getSplitBet() >= players[j].getBalance() - players[j].getBet()))
                    {
                      System.out.println("\nPlayer " + players[j].getName() + "'s second hand not eligible for doubling down due to limited balance.");
                      downResponse = 'n';
                    }
                    if ((downResponse == 'D') || (downResponse == 'd')) {
                      players[j].setSplitDown(true);
                      System.out.println("Old bet: $" + players[j].getSplitBet());
                      if (players[j].getSplit()) System.out.println("First hand bet: $" + players[j].getBet());
                      System.out.println("Current Balance: $" + players[j].getBalance());
                      System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getBet()));
                      System.out.println("What is your new bet?");
                      bet = IO.readDouble();
                      if ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet())) {
                        do
                        {
                          IO.reportBadInput();
                          System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                          System.out.println("\nWhat is your new bet?");
                          bet = IO.readDouble();
                        } while ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet()));
                      }
                      
                      players[j].setSplitBet(bet);
                    }
                    
                    placeholder = deck.deal();
                    countValues = placeholder.getValue();
                    if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                    {
                      countZero--;
                    }
                    

                    if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                    {
                      countOne--;
                    }
                    

                    if ((countValues == 1) || (countValues == 10))
                    {
                      countNegOne--;
                    }
                    System.out.println("Player received " + placeholder);
                    valueOfCard = placeholder.getValue();
                    if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                      valueOfCard = players[j].getSplitScore() + 11;
                    } else
                      valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                    players[j].setSplitScore(valueOfCard);
                    System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                    
                    if (players[j].getSplitScore() > 21) {
                      System.out.println("Player " + players[j].getName() + "'s second hand BUSTED!");
                      players[j].setSplitLost(true);
                      continue;
                    }
                    
                    if (players[j].getSplitDown())
                    {
                      System.out.println("Double down ended the play for player " + players[j].getName() + "'s second hand.");
                      continue;
                    }
                    


                    System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                    
                    System.out.println("Hint[Y]?");
                    hint = IO.readChar();
                    if ((hint == 'Y') || (hint == 'y')) {
                      System.out.println("\nHintBot is thinking...");
                      System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                      System.out.println("Choose wisely: ");
                    } else { System.out.println("Choose wisely: "); }
                    response = IO.readChar();
                    

                    if ((response == 'H') || (response == 'h')) {
                      do {
                        placeholder = deck.deal();
                        countValues = placeholder.getValue();
                        if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                        {
                          countZero--;
                        }
                        

                        if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                        {
                          countOne--;
                        }
                        

                        if ((countValues == 1) || (countValues == 10))
                        {
                          countNegOne--; }
                        System.out.println("Player received " + placeholder);
                        if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                          valueOfCard = players[j].getSplitScore() + 11;
                        } else
                          valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                        players[j].setSplitScore(valueOfCard);
                        System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                        
                        if (players[j].getSplitScore() > 21) {
                          System.out.println("Player " + players[j].getName() + " BUSTED!");
                          players[j].setSplitLost(true);
                          break;
                        }
                        
                        System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                        System.out.println("Hint[Y]?");
                        hint = IO.readChar();
                        if ((hint == 'Y') || (hint == 'y')) {
                          System.out.println("\nHintBot is thinking...");
                          System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                          System.out.println("Choose wisely: ");
                        } else { System.out.println("Choose wisely: ");
                        }
                        response = IO.readChar();
                      } while ((response == 'H') || (response == 'h'));
                      
                      if (((response != 'H') && (response != 'h')) || (players[j].getSplitLost()))
                      {
                        System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                        break;
                      }
                    }
                    else {
                      System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                      break;
                    }
                  } else {
                    System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                    break;
                  }
                }
                
                System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                System.out.println("Hint[Y]?");
                hint = IO.readChar();
                if ((hint == 'Y') || (hint == 'y')) {
                  System.out.println("\nHintBot is thinking...");
                  System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                  System.out.println("Choose wisely: ");
                } else { System.out.println("Choose wisely: ");
                }
                response = IO.readChar();
              } while ((response == 'H') || (response == 'h'));
              


              if ((response != 'H') && (response != 'h'))
              {
                System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
                
                if (players[j].getSplit())
                {

                  System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
                  
                  System.out.println("\n" + players[j].getName() + ", play[P] your second hand, or Stand[any key]?");
                  System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
                  System.out.println("Hint[Y]?");
                  hint = IO.readChar();
                  if ((hint == 'Y') || (hint == 'y')) {
                    System.out.println("\nHintBot is thinking...");
                    System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                    System.out.println("Choose wisely: ");
                  } else { System.out.println("Choose wisely: "); }
                  response = IO.readChar();
                  
                  if ((response == 'P') || (response == 'p'))
                  {
                    System.out.println("\nDouble Down[D] or Hit[any key]?");
                    downResponse = IO.readChar();
                    if ((players[j].getSplitBet() + players[j].getBet() == players[j].getBalance()) || (players[j].getSplitBet() >= players[j].getBalance() - players[j].getBet()))
                    {
                      System.out.println("\nPlayer " + players[j].getName() + "'s second hand not eligible for doubling down due to limited balance.");
                      downResponse = 'n';
                    }
                    if ((downResponse == 'D') || (downResponse == 'd')) {
                      players[j].setSplitDown(true);
                      System.out.println("Old bet: $" + players[j].getSplitBet());
                      if (players[j].getSplit()) System.out.println("First hand bet: $" + players[j].getBet());
                      System.out.println("Current Balance: $" + players[j].getBalance());
                      System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getBet()));
                      System.out.println("What is your new bet?");
                      bet = IO.readDouble();
                      if ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet())) {
                        do
                        {
                          IO.reportBadInput();
                          System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                          System.out.println("\nWhat is your new bet?");
                          bet = IO.readDouble();
                        } while ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet()));
                      }
                      
                      players[j].setSplitBet(bet);
                    }
                    
                    placeholder = deck.deal();
                    countValues = placeholder.getValue();
                    if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                    {
                      countZero--;
                    }
                    

                    if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                    {
                      countOne--;
                    }
                    

                    if ((countValues == 1) || (countValues == 10))
                    {
                      countNegOne--;
                    }
                    System.out.println("Player received " + placeholder);
                    valueOfCard = placeholder.getValue();
                    if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                      valueOfCard = players[j].getSplitScore() + 11;
                    } else
                      valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                    players[j].setSplitScore(valueOfCard);
                    System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                    
                    if (players[j].getSplitScore() > 21) {
                      System.out.println("Player " + players[j].getName() + "'s second hand BUSTED!");
                      players[j].setSplitLost(true);


                    }
                    else if (players[j].getSplitDown())
                    {
                      System.out.println("Double down ended the play for player " + players[j].getName() + "'s second hand.");

                    }
                    else
                    {

                      System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                      
                      System.out.println("Hint[Y]?");
                      hint = IO.readChar();
                      if ((hint == 'Y') || (hint == 'y')) {
                        System.out.println("\nHintBot is thinking...");
                        System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                        System.out.println("Choose wisely: ");
                      } else { System.out.println("Choose wisely: "); }
                      response = IO.readChar();
                      

                      if ((response == 'H') || (response == 'h')) {
                        do {
                          placeholder = deck.deal();
                          countValues = placeholder.getValue();
                          if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                          {
                            countZero--;
                          }
                          

                          if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                          {
                            countOne--;
                          }
                          

                          if ((countValues == 1) || (countValues == 10))
                          {
                            countNegOne--;
                          }
                          System.out.println("Player received " + placeholder);
                          if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                            valueOfCard = players[j].getSplitScore() + 11;
                          } else
                            valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                          players[j].setSplitScore(valueOfCard);
                          System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                          
                          if (players[j].getSplitScore() > 21) {
                            System.out.println("Player " + players[j].getName() + " BUSTED!");
                            players[j].setSplitLost(true);
                            break;
                          }
                          
                          System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                          System.out.println("Hint[Y]?");
                          hint = IO.readChar();
                          if ((hint == 'Y') || (hint == 'y')) {
                            System.out.println("\nHintBot is thinking...");
                            System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                            System.out.println("Choose wisely: ");
                          } else { System.out.println("Choose wisely: ");
                          }
                          response = IO.readChar();
                        } while ((response == 'H') || (response == 'h'));
                        
                        if (((response != 'H') && (response != 'h')) || (players[j].getSplitLost()))
                        {
                          System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                        }
                      }
                      else
                      {
                        System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());

                      }
                      

                    }
                    
                  }
                  
                }
                
              }
              

            }
            else
            {

              System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
              
              if (players[j].getSplit())
              {

                System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
                
                System.out.println("\n" + players[j].getName() + ", play[P] your second hand, or Stand[any key]?");
                System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
                System.out.println("Hint[Y]?");
                hint = IO.readChar();
                if ((hint == 'Y') || (hint == 'y')) {
                  System.out.println("\nHintBot is thinking...");
                  System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                  System.out.println("Choose wisely: ");
                } else { System.out.println("Choose wisely: "); }
                response = IO.readChar();
                
                if ((response == 'P') || (response == 'p'))
                {
                  System.out.println("\nDouble Down[D] or Hit[any key]?");
                  downResponse = IO.readChar();
                  if ((players[j].getSplitBet() + players[j].getBet() == players[j].getBalance()) || (players[j].getSplitBet() >= players[j].getBalance() - players[j].getBet()))
                  {
                    System.out.println("\nPlayer " + players[j].getName() + "'s second hand not eligible for doubling down due to limited balance.");
                    downResponse = 'n';
                  }
                  if ((downResponse == 'D') || (downResponse == 'd')) {
                    players[j].setSplitDown(true);
                    System.out.println("Old bet: $" + players[j].getSplitBet());
                    if (players[j].getSplit()) System.out.println("First hand bet: $" + players[j].getBet());
                    System.out.println("Current Balance: $" + players[j].getBalance());
                    System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getBet()));
                    System.out.println("What is your new bet?");
                    bet = IO.readDouble();
                    if ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet())) {
                      do
                      {
                        IO.reportBadInput();
                        System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                        System.out.println("\nWhat is your new bet?");
                        bet = IO.readDouble();
                      } while ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet()));
                    }
                    
                    players[j].setSplitBet(bet);
                  }
                  
                  placeholder = deck.deal();
                  countValues = placeholder.getValue();
                  if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                  {
                    countZero--;
                  }
                  

                  if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                  {
                    countOne--;
                  }
                  

                  if ((countValues == 1) || (countValues == 10))
                  {
                    countNegOne--;
                  }
                  System.out.println("Player received " + placeholder);
                  valueOfCard = placeholder.getValue();
                  if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                    valueOfCard = players[j].getSplitScore() + 11;
                  } else
                    valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                  players[j].setSplitScore(valueOfCard);
                  System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                  
                  if (players[j].getSplitScore() > 21) {
                    System.out.println("Player " + players[j].getName() + "'s second hand BUSTED!");
                    players[j].setSplitLost(true);


                  }
                  else if (players[j].getSplitDown())
                  {
                    System.out.println("Double down ended the play for player " + players[j].getName() + "'s second hand.");

                  }
                  else
                  {

                    System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                    
                    System.out.println("Hint[Y]?");
                    hint = IO.readChar();
                    if ((hint == 'Y') || (hint == 'y')) {
                      System.out.println("\nHintBot is thinking...");
                      System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                      System.out.println("Choose wisely: ");
                    } else { System.out.println("Choose wisely: "); }
                    response = IO.readChar();
                    

                    if ((response == 'H') || (response == 'h')) {
                      do {
                        placeholder = deck.deal();
                        countValues = placeholder.getValue();
                        if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                        {
                          countZero--;
                        }
                        

                        if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                        {
                          countOne--;
                        }
                        

                        if ((countValues == 1) || (countValues == 10))
                        {
                          countNegOne--; }
                        System.out.println("Player received " + placeholder);
                        if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                          valueOfCard = players[j].getSplitScore() + 11;
                        } else
                          valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                        players[j].setSplitScore(valueOfCard);
                        System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                        
                        if (players[j].getSplitScore() > 21) {
                          System.out.println("Player " + players[j].getName() + " BUSTED!");
                          players[j].setSplitLost(true);
                          break;
                        }
                        
                        System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                        System.out.println("Hint[Y]?");
                        hint = IO.readChar();
                        if ((hint == 'Y') || (hint == 'y')) {
                          System.out.println("\nHintBot is thinking...");
                          System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                          System.out.println("Choose wisely: ");
                        } else { System.out.println("Choose wisely: ");
                        }
                        response = IO.readChar();
                      } while ((response == 'H') || (response == 'h'));
                      
                      if (((response != 'H') && (response != 'h')) || (players[j].getSplitLost()))
                      {
                        System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                      }
                    }
                    else
                    {
                      System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                    }
                    
                  }
                  
                }
                
              }
              
            }
          }
        }
      }
      else
      {
        System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
        
        if (players[j].getSplit())
        {

          System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
          
          System.out.println("\n" + players[j].getName() + ", play[P] your second hand, or Stand[any key]?");
          System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
          System.out.println("Hint[Y]?");
          hint = IO.readChar();
          if ((hint == 'Y') || (hint == 'y')) {
            System.out.println("\nHintBot is thinking...");
            System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
            System.out.println("Choose wisely: ");
          } else { System.out.println("Choose wisely: "); }
          response = IO.readChar();
          
          if ((response == 'P') || (response == 'p'))
          {
            System.out.println("\nDouble Down[D] or Hit[any key]?");
            char downResponse = IO.readChar();
            if ((players[j].getSplitBet() + players[j].getBet() == players[j].getBalance()) || (players[j].getSplitBet() >= players[j].getBalance() - players[j].getBet()))
            {
              System.out.println("\nPlayer " + players[j].getName() + "'s second hand not eligible for doubling down due to limited balance.");
              downResponse = 'n';
            }
            if ((downResponse == 'D') || (downResponse == 'd')) {
              players[j].setSplitDown(true);
              System.out.println("Old bet: $" + players[j].getSplitBet());
              if (players[j].getSplit()) System.out.println("First hand bet: $" + players[j].getBet());
              System.out.println("Current Balance: $" + players[j].getBalance());
              System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getBet()));
              System.out.println("What is your new bet?");
              bet = IO.readDouble();
              if ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet())) {
                do
                {
                  IO.reportBadInput();
                  System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                  System.out.println("\nWhat is your new bet?");
                  bet = IO.readDouble();
                } while ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet()));
              }
              
              players[j].setSplitBet(bet);
            }
            
            placeholder = deck.deal();
            countValues = placeholder.getValue();
            if ((countValues == 7) || (countValues == 8) || (countValues == 9))
            {
              countZero--;
            }
            

            if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
            {
              countOne--;
            }
            

            if ((countValues == 1) || (countValues == 10))
            {
              countNegOne--; }
            System.out.println("Player received " + placeholder);
            valueOfCard = placeholder.getValue();
            if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
              valueOfCard = players[j].getSplitScore() + 11;
            } else
              valueOfCard = players[j].getSplitScore() + placeholder.getValue();
            players[j].setSplitScore(valueOfCard);
            System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
            
            if (players[j].getSplitScore() > 21) {
              System.out.println("Player " + players[j].getName() + "'s second hand BUSTED!");
              players[j].setSplitLost(true);


            }
            else if (players[j].getSplitDown())
            {
              System.out.println("Double down ended the play for player " + players[j].getName() + "'s second hand.");

            }
            else
            {

              System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
              
              System.out.println("Hint[Y]?");
              hint = IO.readChar();
              if ((hint == 'Y') || (hint == 'y')) {
                System.out.println("\nHintBot is thinking...");
                System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                System.out.println("Choose wisely: ");
              } else { System.out.println("Choose wisely: "); }
              response = IO.readChar();
              

              if ((response == 'H') || (response == 'h')) {
                do {
                  placeholder = deck.deal();
                  countValues = placeholder.getValue();
                  if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                  {
                    countZero--;
                  }
                  

                  if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                  {
                    countOne--;
                  }
                  

                  if ((countValues == 1) || (countValues == 10))
                  {
                    countNegOne--; }
                  System.out.println("Player received " + placeholder);
                  if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                    valueOfCard = players[j].getSplitScore() + 11;
                  } else
                    valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                  players[j].setSplitScore(valueOfCard);
                  System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                  
                  if (players[j].getSplitScore() > 21) {
                    System.out.println("Player " + players[j].getName() + " BUSTED!");
                    players[j].setSplitLost(true);
                    break;
                  }
                  
                  System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                  System.out.println("Hint[Y]?");
                  hint = IO.readChar();
                  if ((hint == 'Y') || (hint == 'y')) {
                    System.out.println("\nHintBot is thinking...");
                    System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                    System.out.println("Choose wisely: ");
                  } else { System.out.println("Choose wisely: ");
                  }
                  response = IO.readChar();
                } while ((response == 'H') || (response == 'h'));
                
                if (((response != 'H') && (response != 'h')) || (players[j].getSplitLost()))
                {
                  System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                }
              }
              else
              {
                System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
              }
            }
          }
        }
      }
    }
    










    System.out.println("\nDealer reveals his card...");
    System.out.println("The hidden card was " + hiddenCard);
    if ((hiddenCard.getValue() == 1) && (dealerScore < 11)) {
      dealerScore += 11;
    } else
      dealerScore += hiddenCard.getValue();
    System.out.println("Dealer's current score is " + dealerScore);
    
    if ((dealerScore == 21) && (hiddenCard.getValue() == 10))
    {
      System.out.println("\nDealer has BLACKJACK 21!");
      
      System.out.println("\nEnter any character to continue...");
      waiting = IO.readChar();
      
      System.out.println("The following players who took insurance will be paid back: ");
      
      for (int i = 0; i < players.length; i++)
      {
        if (players[i].getIfInsured())
        {
          System.out.println(players[i].getName());
        }
        
      }
      
    }
    else
    {
      System.out.println("\nDealer will hit until score at least 17 and less than 21.");
      
      while (dealerScore < 17) {
        System.out.println("Dealer hits...");
        placeholder = deck.deal();
        countValues = placeholder.getValue();
        if ((countValues == 7) || (countValues == 8) || (countValues == 9))
        {
          countZero--;
        }
        

        if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
        {
          countOne--;
        }
        

        if ((countValues == 1) || (countValues == 10))
        {
          countNegOne--;
        }
        System.out.println("Dealer received " + placeholder);
        if ((placeholder.getValue() == 1) && (dealerScore + 11 <= 21)) {
          dealerScore += 11;
        } else
          dealerScore += placeholder.getValue();
        System.out.println("Dealer's current score is " + dealerScore);
      }
    }
    
    System.out.println("Dealer stands...");
    

    if (dealerScore > 21) {
      System.out.println(dealerScore + " is over 21.");
      System.out.println("Dealer BUSTED!");
      
      System.out.println("\nEnter any character to continue...");
      waiting = IO.readChar();
      
      System.out.println("\nPlayers still in the game win!");
      
      System.out.println("Player Winners: ");
      int counter = 0;
      for (int j = 0; j < players.length; j++)
      {
        if (!players[j].getLost()) {
          counter++;
          players[j].setWon(true);
          System.out.println("\nPlayer " + players[j].getName() + " with a score of " + players[j].getScore() + " vs " + dealerScore);
          System.out.println("Player " + players[j].getName() + " bet $" + players[j].getBet());
          players[j].winMoney(players[j].getBet());
          System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());
          
          if ((players[j].getSplit()) && (!players[j].getSplitLost())) {
            players[j].setSplitWon(true);
            System.out.println("\nPlayer " + players[j].getName() + "'s second hand with a score of " + players[j].getSplitScore() + " vs " + dealerScore);
            System.out.println("Player " + players[j].getName() + "'s second hand bet $" + players[j].getSplitBet());
            players[j].winMoney(players[j].getSplitBet());
            System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());
          }
        }
      }
      


      if (counter == 0) { System.out.println("NO WINNERS! HAHA!");
      }
    }
    

    for (int j = 0; j < players.length; j++) {
      if ((!players[j].getLost()) && (!players[j].getWon()))
      {
        if (players[j].getScore() > dealerScore)
        {
          players[j].setWon(true);
          System.out.println("\nPlayer " + players[j].getName() + " WINS with a score of " + players[j].getScore() + " vs " + dealerScore);
          System.out.println("Player " + players[j].getName() + " bet $" + players[j].getBet());
          players[j].winMoney(players[j].getBet());
          System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());
        }
        else if (players[j].getScore() < dealerScore)
        {
          players[j].setWon(false);
          System.out.println("\nPlayer " + players[j].getName() + " LOSES with a score of " + players[j].getScore() + " vs " + dealerScore);
          System.out.println("Player " + players[j].getName() + " bet $" + players[j].getBet());
          players[j].loseMoney(players[j].getBet());
          System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());

        }
        else if (players[j].getScore() == dealerScore)
        {
          System.out.println("\nPlayer " + players[j].getName() + " PUSHES with the dealer since both have a score of " + dealerScore);
          System.out.println("Player " + players[j].getName() + "'s balance remains at $" + players[j].getBalance());
        }
      }
      



      if ((players[j].getSplit()) && (!players[j].getSplitLost()) && (!players[j].getSplitWon()))
      {
        if (players[j].getSplitScore() > dealerScore)
        {
          players[j].setSplitWon(true);
          System.out.println("\nPlayer " + players[j].getName() + "'s second hand WINS with a score of " + players[j].getSplitScore() + " vs " + dealerScore);
          System.out.println("Player " + players[j].getName() + "'s second hand bet $" + players[j].getSplitBet());
          players[j].winMoney(players[j].getSplitBet());
          System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());
        }
        else if (players[j].getSplitScore() < dealerScore)
        {
          players[j].setSplitWon(false);
          System.out.println("\nPlayer " + players[j].getName() + "'s second hand LOSES with a score of " + players[j].getSplitScore() + " vs " + dealerScore);
          System.out.println("Player " + players[j].getName() + "'s second hand bet $" + players[j].getSplitBet());
          players[j].loseMoney(players[j].getSplitBet());
          System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());

        }
        else if (players[j].getSplitScore() == dealerScore)
        {
          System.out.println("\nPlayer " + players[j].getName() + "'s second hand PUSHES with the dealer since both have a score of " + dealerScore);
          System.out.println("Player " + players[j].getName() + "'s balance remains at $" + players[j].getBalance());
        }
      }
      


      if (lostGame)
      {
        System.out.println("\nPlayer " + players[j].getName() + " LOSES with a score of " + players[j].getScore() + " from BUSTING.");
        System.out.println("Player " + players[j].getName() + " bet $" + players[j].getBet());
        players[j].loseMoney(players[j].getBet());
        System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());
      }
      

      if (splitLost)
      {
        System.out.println("\nPlayer " + players[j].getName() + "'s second hand LOSES with a score of " + players[j].getSplitScore() + " from BUSTING.");
        System.out.println("Player " + players[j].getName() + "'s second hand bet $" + players[j].getSplitBet());
        players[j].loseMoney(players[j].getSplitBet());
        System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());
      }
    }
    


    System.out.println("\nEnter any character to continue...");
    waiting = IO.readChar();
    
    if ((dealerScore == 21) && (hiddenCard.getValue() == 10))
    {
      System.out.println("\nPaying out insurance...");
      
      for (int k = 0; k < players.length; k++)
      {
        if (players[k].getIfInsured())
        {
          if ((players[k].getLost()) && (players[k].getScore() <= 21))
          {
            players[k].winMoney(players[k].getBet());
            System.out.println("\nPlayer " + players[k].getName() + " was reinbursed with their first hand bet.");
            System.out.println("Player " + players[k].getName() + "'s current balance is $" + players[k].getBalance());
          }
          

          if ((players[k].getSplitLost()) && (players[k].getSplitScore() <= 21))
          {
            players[k].winMoney(players[k].getSplitBet());
            System.out.println("\nPlayer " + players[k].getName() + " was reinbursed with their second hand's bet.");
            System.out.println("Player " + players[k].getName() + "'s current balance is $" + players[k].getBalance());
          }
        }
      }
    }
    




    for (int j = 0; j < players.length; j++) {
      if (players[j].getBalance() == 0.0D)
      {
        System.out.println("\nPlayer " + players[j].getName() + " ran out of money and is out of the game!");
        numPlayers--;
      }
    }
    
    System.out.println("\nPlayers in the next round: ");
    int countThis = 0;
    for (int j = 0; j < players.length; j++)
    {
      if (players[j].getBalance() != 0.0D) {
        countThis++;
        PlayerHands[] updatedPlayers = new PlayerHands[numPlayers];
        System.out.println(countThis + ". " + players[j].getName());
      }
    }
    



    System.out.println("\nAnother round[Y]?");
    char answer = IO.readChar();
    

    if ((answer == 'Y') || (answer == 'y'))
    {
      do
      {
        round++;
        System.out.println("\nROUND " + round);
        
        dealerScore = 0;
        countValues = 0;
        countOne = 20;
        countZero = 12;
        countNegOne = 20;
        
        System.out.println("\nCollecting cards...");
        deck.nextRound();
        System.out.println("Shuffling deck...");
        deck.shuffle();
        
        for (int j = 0; j < players.length; j++)
        {
          if (players[j].getBalance() != 0.0D)
          {
            System.out.println("\nWhat is your new bet, " + players[j].getName() + "? Current balance: $" + players[j].getBalance());
            bet = IO.readDouble();
            if ((bet > players[j].getBalance()) || (bet <= 0.0D)) {
              do {
                IO.reportBadInput();
                System.out.println("Players must make a bet, but they can not bet more than they have!");
                System.out.println("\nHow much do you bet?");
                bet = IO.readDouble();
              }
              while ((bet > players[j].getBalance()) || (bet <= 0.0D));
            }
            
            players[j].setBet(bet);
          }
        }
        

        for (int j = 0; j < players.length; j++)
        {

          if (players[j].getBalance() == 0.0D) {
            players[j].setBet(0.0D);
          }
          players[j].setScore(0);
          players[j].setLost(false);
          players[j].setWon(false);
          players[j].setDoubleDown(false);
          players[j].setIsInsured(false);
          
          players[j].setSplit(false);
          players[j].setSplitBet(0.0D);
          players[j].setSplitScore(0);
          players[j].setSplitWon(false);
          players[j].setSplitLost(false);
          players[j].setSplitDown(false);
        }
        


        System.out.println("\nThe Players:");
        countThis = 0;
        for (int j = 0; j < players.length; j++) {
          if (players[j].getBalance() != 0.0D) {
            countThis++;
            System.out.println(countThis + ". " + players[j]);
          }
        }
        
        for (int j = 0; j < players.length; j++) {
          if (players[j].getBalance() != 0.0D)
          {
            score = 0;
            System.out.println("\nDealing two cards to player " + players[j].getName() + "...");
            placeholder = deck.deal();
            countValues = placeholder.getValue();
            if ((countValues == 7) || (countValues == 8) || (countValues == 9))
            {
              countZero--;
            }
            

            if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
            {
              countOne--;
            }
            

            if ((countValues == 1) || (countValues == 10))
            {
              countNegOne--; }
            System.out.println("Player received " + placeholder);
            
            valueOfCard = placeholder.getValue();
            if (valueOfCard == 1) score += 11; else
              score += valueOfCard;
            players[j].setScore(score);
            
            placeholder = deck.deal();
            countValues = placeholder.getValue();
            if ((countValues == 7) || (countValues == 8) || (countValues == 9))
            {
              countZero--;
            }
            

            if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
            {
              countOne--;
            }
            

            if ((countValues == 1) || (countValues == 10))
            {
              countNegOne--; }
            System.out.println("Player received " + placeholder);
            
            if ((placeholder.getValue() == valueOfCard) && (players[j].getBet() <= players[j].getBalance() / 2.0D)) {
              System.out.println("\nPlayer received two cards of the same value and has enough to split the bet. Split[S] or not[any key]?");
              char splitResponse = IO.readChar();
              
              if ((splitResponse == 'S') || (splitResponse == 's'))
              {
                splitScore = 0;
                players[j].setSplitBet(players[j].getBet());
                
                System.out.println("Player " + players[j].getName() + " splits cards into two hands.");
                players[j].setSplit(true);
                if (placeholder.getValue() == 1) splitScore += 11; else
                  splitScore += placeholder.getValue();
                players[j].setSplitScore(splitScore);
                

                placeholder = deck.deal();
                countValues = placeholder.getValue();
                if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                {
                  countZero--;
                }
                

                if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                {
                  countOne--;
                }
                

                if ((countValues == 1) || (countValues == 10))
                {
                  countNegOne--; }
                System.out.println("Player received " + placeholder + " for first hand.");
                valueOfCard = placeholder.getValue();
                if ((valueOfCard == 1) && (players[j].getScore() != 11)) score += 11; else
                  score += valueOfCard;
                players[j].setScore(score);
                System.out.println("Player " + players[j].getName() + "'s first hand score is currently " + players[j].getScore());
                

                placeholder = deck.deal();
                countValues = placeholder.getValue();
                if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                {
                  countZero--;
                }
                

                if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                {
                  countOne--;
                }
                

                if ((countValues == 1) || (countValues == 10))
                {
                  countNegOne--; }
                System.out.println("Player received " + placeholder + " for second hand.");
                valueOfCard = placeholder.getValue();
                if ((valueOfCard == 1) && (players[j].getSplitScore() != 11)) splitScore += 11; else
                  splitScore += valueOfCard;
                players[j].setSplitScore(splitScore);
                System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
                
                System.out.println("\nEnter any character to continue...");
                waiting = IO.readChar();
              }
              else {
                valueOfCard = placeholder.getValue();
                if ((valueOfCard == 1) && (score != 11)) valueOfCard = 11;
                score += valueOfCard;
                players[j].setScore(score);
                
                System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
                
                System.out.println("\nEnter any character to continue...");
                waiting = IO.readChar();
              }
            }
            else
            {
              System.out.println("Player " + players[j].getName() + " not eligible for a split.");
              valueOfCard = placeholder.getValue();
              if ((valueOfCard == 1) && (score != 11)) valueOfCard = 11;
              score += valueOfCard;
              players[j].setScore(score);
              
              System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
              
              System.out.println("\nEnter any character to continue...");
              waiting = IO.readChar();
            }
          }
        }
        


        System.out.println("\nDealer dealing two cards to himself...");
        placeholder = deck.deal();
        countValues = placeholder.getValue();
        if ((countValues == 7) || (countValues == 8) || (countValues == 9))
        {
          countZero--;
        }
        

        if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
        {
          countOne--;
        }
        

        if ((countValues == 1) || (countValues == 10))
        {
          countNegOne--;
        }
        if (placeholder.getValue() == 1) dealerScore += 11; else
          dealerScore += placeholder.getValue();
        System.out.println("Dealer received " + placeholder);
        
        placeholder = deck.deal();
        countValues = placeholder.getValue();
        if ((countValues == 7) || (countValues == 8) || (countValues == 9))
        {
          countZero--;
        }
        

        if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
        {
          countOne--;
        }
        

        if ((countValues == 1) || (countValues == 10))
        {
          countNegOne--;
        }
        hiddenCard = placeholder;
        System.out.println("Dealer received [card face down].");
        System.out.println("Dealer's current score is unknown.");
        
        System.out.println("\nEnter any character to continue...");
        waiting = IO.readChar();
        

        if (dealerScore == 11) {
          System.out.println("\nDealer's known card is an Ace.");
          
          for (int i = 0; i < players.length; i++)
          {
            if (players[i].getBalance() != 0.0D)
            {
              System.out.println("Player " + players[i].getName() + ", take insurance bet on whether dealer's face-down card is valued 10[Y]?");
              char insured = IO.readChar();
              
              if ((insured == 'Y') || (insured == 'y')) {
                players[i].setIsInsured(true);
              }
            }
          }
        }
        



        System.out.println("\nPlayer hit-or-stand begins...");
        

        for (int j = 0; j < players.length; j++) {
          if (players[j].getBalance() != 0.0D)
          {

            System.out.println("\nPlayer " + players[j].getName() + "'s score is currently " + players[j].getScore());
            if (players[j].getSplit()) {
              System.out.println("And player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
            }
            

            System.out.println("\n" + players[j].getName() + ", play[P], or Stand[any key]?");
            System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
            System.out.println("Hint[Y]?");
            char hint = IO.readChar();
            if ((hint == 'Y') || (hint == 'y')) {
              System.out.println("\nHintBot is thinking...");
              System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
              System.out.println("Choose wisely: ");
            } else { System.out.println("Choose wisely: "); }
            char response = IO.readChar();
            
            if ((response == 'P') || (response == 'p'))
            {
              System.out.println("\nDouble Down[D] or Hit[any key]?");
              char downResponse = IO.readChar();
              if ((players[j].getBet() + players[j].getSplitBet() == players[j].getBalance()) || (players[j].getBet() >= players[j].getBalance() - players[j].getSplitBet()))
              {
                System.out.println("\nPlayer " + players[j].getName() + " not eligible for doubling down due to limited balance.");
                downResponse = 'n';
              }
              if ((downResponse == 'D') || (downResponse == 'd')) {
                players[j].setDoubleDown(true);
                System.out.println("Old bet: $" + players[j].getBet());
                if (players[j].getSplit()) System.out.println("Second hand bet: $" + players[j].getSplitBet());
                System.out.println("Current Balance: $" + players[j].getBalance());
                System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getSplitBet()));
                System.out.println("What is your new bet?");
                bet = IO.readDouble();
                if ((bet <= players[j].getBet()) || (bet > players[j].getBalance() - players[j].getSplitBet())) {
                  do
                  {
                    IO.reportBadInput();
                    System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                    System.out.println("\nWhat is your new bet?");
                    bet = IO.readDouble();
                  } while ((bet <= players[j].getBet()) || (bet > players[j].getBalance() - players[j].getSplitBet()));
                }
                
                players[j].setBet(bet);
              }
              
              placeholder = deck.deal();
              countValues = placeholder.getValue();
              if ((countValues == 7) || (countValues == 8) || (countValues == 9))
              {
                countZero--;
              }
              

              if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
              {
                countOne--;
              }
              

              if ((countValues == 1) || (countValues == 10))
              {
                countNegOne--;
              }
              System.out.println("Player received " + placeholder);
              valueOfCard = placeholder.getValue();
              if ((placeholder.getValue() == 1) && (players[j].getScore() + 11 <= 21)) {
                valueOfCard = players[j].getScore() + 11;
              } else
                valueOfCard = players[j].getScore() + placeholder.getValue();
              players[j].setScore(valueOfCard);
              System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
              
              if (players[j].getScore() > 21) {
                System.out.println("Player " + players[j].getName() + " BUSTED!");
                players[j].setLost(true);
              }
              

              if ((!players[j].getLost()) || (players[j].getSplit())) {
                if ((players[j].getLost()) && (players[j].getSplit()))
                {


                  System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
                  
                  System.out.println("\n" + players[j].getName() + ", play[P] your second hand, or Stand[any key]?");
                  System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
                  System.out.println("Hint[Y]?");
                  hint = IO.readChar();
                  if ((hint == 'Y') || (hint == 'y')) {
                    System.out.println("\nHintBot is thinking...");
                    System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                    System.out.println("Choose wisely: ");
                  } else { System.out.println("Choose wisely: "); }
                  response = IO.readChar();
                  
                  if ((response != 'P') && (response != 'p'))
                    continue;
                  System.out.println("\nDouble Down[D] or Hit[any key]?");
                  downResponse = IO.readChar();
                  if ((players[j].getSplitBet() + players[j].getBet() == players[j].getBalance()) || (players[j].getSplitBet() >= players[j].getBalance() - players[j].getBet()))
                  {
                    System.out.println("\nPlayer " + players[j].getName() + "'s second hand not eligible for doubling down due to limited balance.");
                    downResponse = 'n';
                  }
                  if ((downResponse == 'D') || (downResponse == 'd')) {
                    players[j].setSplitDown(true);
                    System.out.println("Old bet: $" + players[j].getSplitBet());
                    if (players[j].getSplit()) System.out.println("First hand bet: $" + players[j].getBet());
                    System.out.println("Current Balance: $" + players[j].getBalance());
                    System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getBet()));
                    System.out.println("What is your new bet?");
                    bet = IO.readDouble();
                    if ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet())) {
                      do
                      {
                        IO.reportBadInput();
                        System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                        System.out.println("\nWhat is your new bet?");
                        bet = IO.readDouble();
                      } while ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet()));
                    }
                    
                    players[j].setSplitBet(bet);
                  }
                  
                  placeholder = deck.deal();
                  countValues = placeholder.getValue();
                  if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                  {
                    countZero--;
                  }
                  

                  if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                  {
                    countOne--;
                  }
                  

                  if ((countValues == 1) || (countValues == 10))
                  {
                    countNegOne--;
                  }
                  System.out.println("Player received " + placeholder);
                  valueOfCard = placeholder.getValue();
                  if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                    valueOfCard = players[j].getSplitScore() + 11;
                  } else
                    valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                  players[j].setSplitScore(valueOfCard);
                  System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                  
                  if (players[j].getSplitScore() > 21) {
                    System.out.println("Player " + players[j].getName() + "'s second hand BUSTED!");
                    players[j].setSplitLost(true);
                    continue;
                  }
                  
                  if (players[j].getSplitDown())
                  {
                    System.out.println("Double down ended the play for player " + players[j].getName() + "'s second hand.");
                    continue;
                  }
                  


                  System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                  
                  System.out.println("Hint[Y]?");
                  hint = IO.readChar();
                  if ((hint == 'Y') || (hint == 'y')) {
                    System.out.println("\nHintBot is thinking...");
                    System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                    System.out.println("Choose wisely: ");
                  } else { System.out.println("Choose wisely: "); }
                  response = IO.readChar();
                  

                  if ((response == 'H') || (response == 'h')) {
                    do {
                      placeholder = deck.deal();
                      countValues = placeholder.getValue();
                      if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                      {
                        countZero--;
                      }
                      

                      if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                      {
                        countOne--;
                      }
                      

                      if ((countValues == 1) || (countValues == 10))
                      {
                        countNegOne--;
                      }
                      System.out.println("Player received " + placeholder);
                      if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                        valueOfCard = players[j].getSplitScore() + 11;
                      } else
                        valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                      players[j].setSplitScore(valueOfCard);
                      System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                      
                      if (players[j].getSplitScore() > 21) {
                        System.out.println("Player " + players[j].getName() + " BUSTED!");
                        players[j].setSplitLost(true);
                        break;
                      }
                      
                      System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                      System.out.println("Hint[Y]?");
                      hint = IO.readChar();
                      if ((hint == 'Y') || (hint == 'y')) {
                        System.out.println("\nHintBot is thinking...");
                        System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                        System.out.println("Choose wisely: ");
                      } else { System.out.println("Choose wisely: ");
                      }
                      response = IO.readChar();
                    } while ((response == 'H') || (response == 'h'));
                    
                    if (((response != 'H') && (response != 'h')) || (players[j].getSplitLost()))
                    {
                      System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                      continue;
                    }
                  }
                  else {
                    System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                    continue;
                  }
                }
                






                if ((players[j].getDoubleDown()) && (!players[j].getSplit()))
                {
                  System.out.println("Double down ended the play for player " + players[j].getName());
                }
                else {
                  if ((players[j].getDoubleDown()) && (players[j].getSplit()))
                  {
                    System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
                    
                    System.out.println("\n" + players[j].getName() + ", play[P] your second hand, or Stand[any key]?");
                    System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
                    System.out.println("Hint[Y]?");
                    hint = IO.readChar();
                    if ((hint == 'Y') || (hint == 'y')) {
                      System.out.println("\nHintBot is thinking...");
                      System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                      System.out.println("Choose wisely: ");
                    } else { System.out.println("Choose wisely: "); }
                    response = IO.readChar();
                    
                    if ((response != 'P') && (response != 'p'))
                      continue;
                    System.out.println("\nDouble Down[D] or Hit[any key]?");
                    downResponse = IO.readChar();
                    if ((players[j].getSplitBet() + players[j].getBet() == players[j].getBalance()) || (players[j].getSplitBet() >= players[j].getBalance() - players[j].getBet()))
                    {
                      System.out.println("\nPlayer " + players[j].getName() + "'s second hand not eligible for doubling down due to limited balance.");
                      downResponse = 'n';
                    }
                    if ((downResponse == 'D') || (downResponse == 'd')) {
                      players[j].setSplitDown(true);
                      System.out.println("Old bet: $" + players[j].getSplitBet());
                      if (players[j].getSplit()) System.out.println("First hand bet: $" + players[j].getBet());
                      System.out.println("Current Balance: $" + players[j].getBalance());
                      System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getBet()));
                      System.out.println("What is your new bet?");
                      bet = IO.readDouble();
                      if ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet())) {
                        do
                        {
                          IO.reportBadInput();
                          System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                          System.out.println("\nWhat is your new bet?");
                          bet = IO.readDouble();
                        } while ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet()));
                      }
                      
                      players[j].setSplitBet(bet);
                    }
                    
                    placeholder = deck.deal();
                    countValues = placeholder.getValue();
                    if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                    {
                      countZero--;
                    }
                    

                    if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                    {
                      countOne--;
                    }
                    

                    if ((countValues == 1) || (countValues == 10))
                    {
                      countNegOne--;
                    }
                    System.out.println("Player received " + placeholder);
                    valueOfCard = placeholder.getValue();
                    if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                      valueOfCard = players[j].getSplitScore() + 11;
                    } else
                      valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                    players[j].setSplitScore(valueOfCard);
                    System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                    
                    if (players[j].getSplitScore() > 21) {
                      System.out.println("Player " + players[j].getName() + "'s second hand BUSTED!");
                      players[j].setSplitLost(true);
                      continue;
                    }
                    
                    if (players[j].getSplitDown())
                    {
                      System.out.println("Double down ended the play for player " + players[j].getName() + "'s second hand.");
                      continue;
                    }
                    


                    System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                    
                    System.out.println("Hint[Y]?");
                    hint = IO.readChar();
                    if ((hint == 'Y') || (hint == 'y')) {
                      System.out.println("\nHintBot is thinking...");
                      System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                      System.out.println("Choose wisely: ");
                    } else { System.out.println("Choose wisely: "); }
                    response = IO.readChar();
                    

                    if ((response == 'H') || (response == 'h')) {
                      do {
                        placeholder = deck.deal();
                        countValues = placeholder.getValue();
                        if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                        {
                          countZero--;
                        }
                        

                        if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                        {
                          countOne--;
                        }
                        

                        if ((countValues == 1) || (countValues == 10))
                        {
                          countNegOne--;
                        }
                        System.out.println("Player received " + placeholder);
                        if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                          valueOfCard = players[j].getSplitScore() + 11;
                        } else
                          valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                        players[j].setSplitScore(valueOfCard);
                        System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                        
                        if (players[j].getSplitScore() > 21) {
                          System.out.println("Player " + players[j].getName() + " BUSTED!");
                          players[j].setSplitLost(true);
                          break;
                        }
                        
                        System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                        System.out.println("Hint[Y]?");
                        hint = IO.readChar();
                        if ((hint == 'Y') || (hint == 'y')) {
                          System.out.println("\nHintBot is thinking...");
                          System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                          System.out.println("Choose wisely: ");
                        } else { System.out.println("Choose wisely: ");
                        }
                        response = IO.readChar();
                      } while ((response == 'H') || (response == 'h'));
                      
                      if (((response != 'H') && (response != 'h')) || (players[j].getSplitLost()))
                      {
                        System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                        continue;
                      }
                    }
                    else {
                      System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                      continue;
                    }
                  }
                  









                  System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                  
                  System.out.println("Hint[Y]?");
                  hint = IO.readChar();
                  if ((hint == 'Y') || (hint == 'y')) {
                    System.out.println("\nHintBot is thinking...");
                    System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                    System.out.println("Choose wisely: ");
                  } else { System.out.println("Choose wisely: "); }
                  response = IO.readChar();
                  
                  if ((response == 'H') || (response == 'h')) {
                    do {
                      placeholder = deck.deal();
                      countValues = placeholder.getValue();
                      if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                      {
                        countZero--;
                      }
                      

                      if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                      {
                        countOne--;
                      }
                      

                      if ((countValues == 1) || (countValues == 10))
                      {
                        countNegOne--;
                      }
                      System.out.println("Player received " + placeholder);
                      if ((placeholder.getValue() == 1) && (players[j].getScore() + 11 <= 21)) {
                        valueOfCard = players[j].getScore() + 11;
                      } else
                        valueOfCard = players[j].getScore() + placeholder.getValue();
                      players[j].setScore(valueOfCard);
                      System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
                      
                      if (players[j].getScore() > 21) {
                        System.out.println("Player " + players[j].getName() + " BUSTED!");
                        players[j].setLost(true);
                      }
                      

                      if ((players[j].getLost()) && (!players[j].getSplit())) break;
                      if ((players[j].getLost()) && (players[j].getSplit()))
                      {

                        System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
                        
                        System.out.println("\n" + players[j].getName() + ", play[P] your second hand, or Stand[any key]?");
                        System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
                        System.out.println("Hint[Y]?");
                        hint = IO.readChar();
                        if ((hint == 'Y') || (hint == 'y')) {
                          System.out.println("\nHintBot is thinking...");
                          System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                          System.out.println("Choose wisely: ");
                        } else { System.out.println("Choose wisely: "); }
                        response = IO.readChar();
                        
                        if ((response == 'P') || (response == 'p'))
                        {
                          System.out.println("\nDouble Down[D] or Hit[any key]?");
                          downResponse = IO.readChar();
                          if ((players[j].getSplitBet() + players[j].getBet() == players[j].getBalance()) || (players[j].getSplitBet() >= players[j].getBalance() - players[j].getBet()))
                          {
                            System.out.println("\nPlayer " + players[j].getName() + "'s second hand not eligible for doubling down due to limited balance.");
                            downResponse = 'n';
                          }
                          if ((downResponse == 'D') || (downResponse == 'd')) {
                            players[j].setSplitDown(true);
                            System.out.println("Old bet: $" + players[j].getSplitBet());
                            if (players[j].getSplit()) System.out.println("First hand bet: $" + players[j].getBet());
                            System.out.println("Current Balance: $" + players[j].getBalance());
                            System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getBet()));
                            System.out.println("What is your new bet?");
                            bet = IO.readDouble();
                            if ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet())) {
                              do
                              {
                                IO.reportBadInput();
                                System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                                System.out.println("\nWhat is your new bet?");
                                bet = IO.readDouble();
                              } while ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet()));
                            }
                            
                            players[j].setSplitBet(bet);
                          }
                          
                          placeholder = deck.deal();
                          countValues = placeholder.getValue();
                          if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                          {
                            countZero--;
                          }
                          

                          if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                          {
                            countOne--;
                          }
                          

                          if ((countValues == 1) || (countValues == 10))
                          {
                            countNegOne--;
                          }
                          System.out.println("Player received " + placeholder);
                          valueOfCard = placeholder.getValue();
                          if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                            valueOfCard = players[j].getSplitScore() + 11;
                          } else
                            valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                          players[j].setSplitScore(valueOfCard);
                          System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                          
                          if (players[j].getSplitScore() > 21) {
                            System.out.println("Player " + players[j].getName() + "'s second hand BUSTED!");
                            players[j].setSplitLost(true);
                            continue;
                          }
                          
                          if (players[j].getSplitDown())
                          {
                            System.out.println("Double down ended the play for player " + players[j].getName() + "'s second hand.");
                            continue;
                          }
                          


                          System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                          
                          System.out.println("Hint[Y]?");
                          hint = IO.readChar();
                          if ((hint == 'Y') || (hint == 'y')) {
                            System.out.println("\nHintBot is thinking...");
                            System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                            System.out.println("Choose wisely: ");
                          } else { System.out.println("Choose wisely: "); }
                          response = IO.readChar();
                          

                          if ((response == 'H') || (response == 'h')) {
                            do {
                              placeholder = deck.deal();
                              countValues = placeholder.getValue();
                              if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                              {
                                countZero--;
                              }
                              

                              if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                              {
                                countOne--;
                              }
                              

                              if ((countValues == 1) || (countValues == 10))
                              {
                                countNegOne--; }
                              System.out.println("Player received " + placeholder);
                              if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                                valueOfCard = players[j].getSplitScore() + 11;
                              } else
                                valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                              players[j].setSplitScore(valueOfCard);
                              System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                              
                              if (players[j].getSplitScore() > 21) {
                                System.out.println("Player " + players[j].getName() + " BUSTED!");
                                players[j].setSplitLost(true);
                                break;
                              }
                              
                              System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                              System.out.println("Hint[Y]?");
                              hint = IO.readChar();
                              if ((hint == 'Y') || (hint == 'y')) {
                                System.out.println("\nHintBot is thinking...");
                                System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                                System.out.println("Choose wisely: ");
                              } else { System.out.println("Choose wisely: ");
                              }
                              response = IO.readChar();
                            } while ((response == 'H') || (response == 'h'));
                            
                            if (((response != 'H') && (response != 'h')) || (players[j].getSplitLost()))
                            {
                              System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                              break;
                            }
                          }
                          else {
                            System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                            break;
                          }
                        } else {
                          System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                          break;
                        }
                      }
                      

                      System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                      System.out.println("Hint[Y]?");
                      hint = IO.readChar();
                      if ((hint == 'Y') || (hint == 'y')) {
                        System.out.println("\nHintBot is thinking...");
                        System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                        System.out.println("Choose wisely: ");
                      } else { System.out.println("Choose wisely: ");
                      }
                      response = IO.readChar();
                    } while ((response == 'H') || (response == 'h'));
                    



                    if ((response != 'H') && (response != 'h'))
                    {
                      System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
                      
                      if (players[j].getSplit())
                      {

                        System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
                        
                        System.out.println("\n" + players[j].getName() + ", play[P] your second hand, or Stand[any key]?");
                        System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
                        System.out.println("Hint[Y]?");
                        hint = IO.readChar();
                        if ((hint == 'Y') || (hint == 'y')) {
                          System.out.println("\nHintBot is thinking...");
                          System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                          System.out.println("Choose wisely: ");
                        } else { System.out.println("Choose wisely: "); }
                        response = IO.readChar();
                        
                        if ((response == 'P') || (response == 'p'))
                        {
                          System.out.println("\nDouble Down[D] or Hit[any key]?");
                          downResponse = IO.readChar();
                          if ((players[j].getSplitBet() + players[j].getBet() == players[j].getBalance()) || (players[j].getSplitBet() >= players[j].getBalance() - players[j].getBet()))
                          {
                            System.out.println("\nPlayer " + players[j].getName() + "'s second hand not eligible for doubling down due to limited balance.");
                            downResponse = 'n';
                          }
                          if ((downResponse == 'D') || (downResponse == 'd')) {
                            players[j].setSplitDown(true);
                            System.out.println("Old bet: $" + players[j].getSplitBet());
                            if (players[j].getSplit()) System.out.println("First hand bet: $" + players[j].getBet());
                            System.out.println("Current Balance: $" + players[j].getBalance());
                            System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getBet()));
                            System.out.println("What is your new bet?");
                            bet = IO.readDouble();
                            if ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet())) {
                              do
                              {
                                IO.reportBadInput();
                                System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                                System.out.println("\nWhat is your new bet?");
                                bet = IO.readDouble();
                              } while ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet()));
                            }
                            
                            players[j].setSplitBet(bet);
                          }
                          
                          placeholder = deck.deal();
                          countValues = placeholder.getValue();
                          if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                          {
                            countZero--;
                          }
                          

                          if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                          {
                            countOne--;
                          }
                          

                          if ((countValues == 1) || (countValues == 10))
                          {
                            countNegOne--;
                          }
                          System.out.println("Player received " + placeholder);
                          valueOfCard = placeholder.getValue();
                          if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                            valueOfCard = players[j].getSplitScore() + 11;
                          } else
                            valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                          players[j].setSplitScore(valueOfCard);
                          System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                          
                          if (players[j].getSplitScore() > 21) {
                            System.out.println("Player " + players[j].getName() + "'s second hand BUSTED!");
                            players[j].setSplitLost(true);


                          }
                          else if (players[j].getSplitDown())
                          {
                            System.out.println("Double down ended the play for player " + players[j].getName() + "'s second hand.");

                          }
                          else
                          {

                            System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                            
                            System.out.println("Hint[Y]?");
                            hint = IO.readChar();
                            if ((hint == 'Y') || (hint == 'y')) {
                              System.out.println("\nHintBot is thinking...");
                              System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                              System.out.println("Choose wisely: ");
                            } else { System.out.println("Choose wisely: "); }
                            response = IO.readChar();
                            

                            if ((response == 'H') || (response == 'h')) {
                              do {
                                placeholder = deck.deal();
                                countValues = placeholder.getValue();
                                if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                                {
                                  countZero--;
                                }
                                

                                if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                                {
                                  countOne--;
                                }
                                

                                if ((countValues == 1) || (countValues == 10))
                                {
                                  countNegOne--;
                                }
                                System.out.println("Player received " + placeholder);
                                if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                                  valueOfCard = players[j].getSplitScore() + 11;
                                } else
                                  valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                                players[j].setSplitScore(valueOfCard);
                                System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                                
                                if (players[j].getSplitScore() > 21) {
                                  System.out.println("Player " + players[j].getName() + " BUSTED!");
                                  players[j].setSplitLost(true);
                                  break;
                                }
                                
                                System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                                System.out.println("Hint[Y]?");
                                hint = IO.readChar();
                                if ((hint == 'Y') || (hint == 'y')) {
                                  System.out.println("\nHintBot is thinking...");
                                  System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                                  System.out.println("Choose wisely: ");
                                } else { System.out.println("Choose wisely: ");
                                }
                                response = IO.readChar();
                              } while ((response == 'H') || (response == 'h'));
                              
                              if (((response != 'H') && (response != 'h')) || (players[j].getSplitLost()))
                              {
                                System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                              }
                            }
                            else
                            {
                              System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());

                            }
                            

                          }
                          
                        }
                        
                      }
                      
                    }
                    

                  }
                  else
                  {

                    System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
                    
                    if (players[j].getSplit())
                    {

                      System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
                      
                      System.out.println("\n" + players[j].getName() + ", play[P] your second hand, or Stand[any key]?");
                      System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
                      System.out.println("Hint[Y]?");
                      hint = IO.readChar();
                      if ((hint == 'Y') || (hint == 'y')) {
                        System.out.println("\nHintBot is thinking...");
                        System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                        System.out.println("Choose wisely: ");
                      } else { System.out.println("Choose wisely: "); }
                      response = IO.readChar();
                      
                      if ((response == 'P') || (response == 'p'))
                      {
                        System.out.println("\nDouble Down[D] or Hit[any key]?");
                        downResponse = IO.readChar();
                        if ((players[j].getSplitBet() + players[j].getBet() == players[j].getBalance()) || (players[j].getSplitBet() >= players[j].getBalance() - players[j].getBet()))
                        {
                          System.out.println("\nPlayer " + players[j].getName() + "'s second hand not eligible for doubling down due to limited balance.");
                          downResponse = 'n';
                        }
                        if ((downResponse == 'D') || (downResponse == 'd')) {
                          players[j].setSplitDown(true);
                          System.out.println("Old bet: $" + players[j].getSplitBet());
                          if (players[j].getSplit()) System.out.println("First hand bet: $" + players[j].getBet());
                          System.out.println("Current Balance: $" + players[j].getBalance());
                          System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getBet()));
                          System.out.println("What is your new bet?");
                          bet = IO.readDouble();
                          if ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet())) {
                            do
                            {
                              IO.reportBadInput();
                              System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                              System.out.println("\nWhat is your new bet?");
                              bet = IO.readDouble();
                            } while ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet()));
                          }
                          
                          players[j].setSplitBet(bet);
                        }
                        
                        placeholder = deck.deal();
                        countValues = placeholder.getValue();
                        if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                        {
                          countZero--;
                        }
                        

                        if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                        {
                          countOne--;
                        }
                        

                        if ((countValues == 1) || (countValues == 10))
                        {
                          countNegOne--;
                        }
                        System.out.println("Player received " + placeholder);
                        valueOfCard = placeholder.getValue();
                        if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                          valueOfCard = players[j].getSplitScore() + 11;
                        } else
                          valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                        players[j].setSplitScore(valueOfCard);
                        System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                        
                        if (players[j].getSplitScore() > 21) {
                          System.out.println("Player " + players[j].getName() + "'s second hand BUSTED!");
                          players[j].setSplitLost(true);


                        }
                        else if (players[j].getSplitDown())
                        {
                          System.out.println("Double down ended the play for player " + players[j].getName() + "'s second hand.");

                        }
                        else
                        {

                          System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                          
                          System.out.println("Hint[Y]?");
                          hint = IO.readChar();
                          if ((hint == 'Y') || (hint == 'y')) {
                            System.out.println("\nHintBot is thinking...");
                            System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                            System.out.println("Choose wisely: ");
                          } else { System.out.println("Choose wisely: "); }
                          response = IO.readChar();
                          

                          if ((response == 'H') || (response == 'h')) {
                            do {
                              placeholder = deck.deal();
                              countValues = placeholder.getValue();
                              if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                              {
                                countZero--;
                              }
                              

                              if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                              {
                                countOne--;
                              }
                              

                              if ((countValues == 1) || (countValues == 10))
                              {
                                countNegOne--; }
                              System.out.println("Player received " + placeholder);
                              if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                                valueOfCard = players[j].getSplitScore() + 11;
                              } else
                                valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                              players[j].setSplitScore(valueOfCard);
                              System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                              
                              if (players[j].getSplitScore() > 21) {
                                System.out.println("Player " + players[j].getName() + " BUSTED!");
                                players[j].setSplitLost(true);
                                break;
                              }
                              
                              System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                              System.out.println("Hint[Y]?");
                              hint = IO.readChar();
                              if ((hint == 'Y') || (hint == 'y')) {
                                System.out.println("\nHintBot is thinking...");
                                System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                                System.out.println("Choose wisely: ");
                              } else { System.out.println("Choose wisely: ");
                              }
                              response = IO.readChar();
                            } while ((response == 'H') || (response == 'h'));
                            
                            if (((response != 'H') && (response != 'h')) || (players[j].getSplitLost()))
                            {
                              System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                            }
                          }
                          else
                          {
                            System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                          }
                          
                        }
                        
                      }
                      
                    }
                    
                  }
                }
              }
            }
            else
            {
              System.out.println("Player " + players[j].getName() + "'s score is currently " + players[j].getScore());
              
              if (players[j].getSplit())
              {
                System.out.println("Player " + players[j].getName() + "'s second hand score is currently " + players[j].getSplitScore());
                
                System.out.println("\n" + players[j].getName() + ", play[P] your second hand, or Stand[any key]?");
                System.out.println("(If you choose to play, you will be further asked whether this is a Double Down or a Hit.)");
                System.out.println("Hint[Y]?");
                hint = IO.readChar();
                if ((hint == 'Y') || (hint == 'y')) {
                  System.out.println("\nHintBot is thinking...");
                  System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                  System.out.println("Choose wisely: ");
                } else { System.out.println("Choose wisely: "); }
                response = IO.readChar();
                
                if ((response == 'P') || (response == 'p'))
                {
                  System.out.println("\nDouble Down[D] or Hit[any key]?");
                  char downResponse = IO.readChar();
                  if ((players[j].getSplitBet() + players[j].getBet() == players[j].getBalance()) || (players[j].getSplitBet() >= players[j].getBalance() - players[j].getBet()))
                  {
                    System.out.println("\nPlayer " + players[j].getName() + "'s second hand not eligible for doubling down due to limited balance.");
                    downResponse = 'n';
                  }
                  if ((downResponse == 'D') || (downResponse == 'd')) {
                    players[j].setSplitDown(true);
                    System.out.println("Old bet: $" + players[j].getSplitBet());
                    if (players[j].getSplit()) System.out.println("First hand bet: $" + players[j].getBet());
                    System.out.println("Current Balance: $" + players[j].getBalance());
                    System.out.println("Remaining balance left to bet on: $" + (players[j].getBalance() - players[j].getBet()));
                    System.out.println("What is your new bet?");
                    bet = IO.readDouble();
                    if ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet())) {
                      do
                      {
                        IO.reportBadInput();
                        System.out.println("New bet cannot be less than or equal to old bet or more than the remaining balance available!");
                        System.out.println("\nWhat is your new bet?");
                        bet = IO.readDouble();
                      } while ((bet <= players[j].getSplitBet()) || (bet > players[j].getBalance() - players[j].getBet()));
                    }
                    
                    players[j].setSplitBet(bet);
                  }
                  
                  placeholder = deck.deal();
                  countValues = placeholder.getValue();
                  if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                  {
                    countZero--;
                  }
                  

                  if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                  {
                    countOne--;
                  }
                  

                  if ((countValues == 1) || (countValues == 10))
                  {
                    countNegOne--; }
                  System.out.println("Player received " + placeholder);
                  valueOfCard = placeholder.getValue();
                  if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                    valueOfCard = players[j].getSplitScore() + 11;
                  } else
                    valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                  players[j].setSplitScore(valueOfCard);
                  System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                  
                  if (players[j].getSplitScore() > 21) {
                    System.out.println("Player " + players[j].getName() + "'s second hand BUSTED!");
                    players[j].setSplitLost(true);


                  }
                  else if (players[j].getSplitDown())
                  {
                    System.out.println("Double down ended the play for player " + players[j].getName() + "'s second hand.");

                  }
                  else
                  {

                    System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                    
                    System.out.println("Hint[Y]?");
                    hint = IO.readChar();
                    if ((hint == 'Y') || (hint == 'y')) {
                      System.out.println("\nHintBot is thinking...");
                      System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                      System.out.println("Choose wisely: ");
                    } else { System.out.println("Choose wisely: "); }
                    response = IO.readChar();
                    

                    if ((response == 'H') || (response == 'h')) {
                      do {
                        placeholder = deck.deal();
                        countValues = placeholder.getValue();
                        if ((countValues == 7) || (countValues == 8) || (countValues == 9))
                        {
                          countZero--;
                        }
                        

                        if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
                        {
                          countOne--;
                        }
                        

                        if ((countValues == 1) || (countValues == 10))
                        {
                          countNegOne--; }
                        System.out.println("Player received " + placeholder);
                        if ((placeholder.getValue() == 1) && (players[j].getSplitScore() + 11 <= 21)) {
                          valueOfCard = players[j].getSplitScore() + 11;
                        } else
                          valueOfCard = players[j].getSplitScore() + placeholder.getValue();
                        players[j].setSplitScore(valueOfCard);
                        System.out.println("Player " + players[j].getName() + "'s second hand's score is currently " + players[j].getSplitScore());
                        
                        if (players[j].getSplitScore() > 21) {
                          System.out.println("Player " + players[j].getName() + " BUSTED!");
                          players[j].setSplitLost(true);
                          break;
                        }
                        
                        System.out.println("\n" + players[j].getName() + ", Hit[H] or Stand[any key]?");
                        System.out.println("Hint[Y]?");
                        hint = IO.readChar();
                        if ((hint == 'Y') || (hint == 'y')) {
                          System.out.println("\nHintBot is thinking...");
                          System.out.println("There is a " + hintOne(countNegOne, countZero, countOne) + "% chance that you will receive a card valued 1, 10, or 11,\n a " + hintTwo(countNegOne, countZero, countOne) + "% chance you will receive a card valued 7 to 9,\n and there is a " + hintThree(countNegOne, countZero, countOne) + "% chance you will receive a card valued 2 to 8.");
                          System.out.println("Choose wisely: ");
                        } else { System.out.println("Choose wisely: ");
                        }
                        response = IO.readChar();
                      } while ((response == 'H') || (response == 'h'));
                      
                      if (((response != 'H') && (response != 'h')) || (players[j].getSplitLost()))
                      {
                        System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                      }
                    }
                    else
                    {
                      System.out.println("Player " + players[j].getName() + "'s second hand's score is " + players[j].getSplitScore());
                    }
                  }
                }
              }
            }
          }
        }
        










        System.out.println("\nDealer reveals his card...");
        System.out.println("The hidden card was " + hiddenCard);
        if ((hiddenCard.getValue() == 1) && (dealerScore < 11)) {
          dealerScore += 11;
        } else
          dealerScore += hiddenCard.getValue();
        System.out.println("Dealer's current score is " + dealerScore);
        
        if ((dealerScore == 21) && (hiddenCard.getValue() == 10))
        {
          System.out.println("\nDealer has BLACKJACK 21!");
          
          System.out.println("\nEnter any character to continue...");
          waiting = IO.readChar();
          
          System.out.println("The following players who took insurance will be paid back: ");
          
          for (int i = 0; i < players.length; i++)
          {
            if ((players[i].getIfInsured()) && (players[i].getScore() != 0))
            {
              System.out.println(players[i].getName());
            }
            
          }
          
        }
        else
        {
          System.out.println("\nDealer will hit until score at least 17 and less than 21.");
          
          while (dealerScore < 17) {
            System.out.println("Dealer hits...");
            placeholder = deck.deal();
            countValues = placeholder.getValue();
            if ((countValues == 7) || (countValues == 8) || (countValues == 9))
            {
              countZero--;
            }
            

            if ((countValues == 2) || (countValues == 3) || (countValues == 4) || (countValues == 5) || (countValues == 6))
            {
              countOne--;
            }
            

            if ((countValues == 1) || (countValues == 10))
            {
              countNegOne--;
            }
            System.out.println("Dealer received " + placeholder);
            if ((placeholder.getValue() == 1) && (dealerScore + 11 <= 21)) {
              dealerScore += 11;
            } else
              dealerScore += placeholder.getValue();
            System.out.println("Dealer's current score is " + dealerScore);
          }
        }
        
        System.out.println("Dealer stands...");
        

        if (dealerScore > 21) {
          System.out.println(dealerScore + " is over 21.");
          System.out.println("Dealer BUSTED!");
          
          System.out.println("\nEnter any character to continue...");
          waiting = IO.readChar();
          
          System.out.println("\nPlayers still in the game win!");
          
          System.out.println("Player Winners: ");
          int counter = 0;
          for (int j = 0; j < players.length; j++)
          {
            if (players[j].getScore() != 0)
            {

              if (!players[j].getLost()) {
                counter++;
                players[j].setWon(true);
                System.out.println("\nPlayer " + players[j].getName() + " with a score of " + players[j].getScore() + " vs " + dealerScore);
                System.out.println("Player " + players[j].getName() + " bet $" + players[j].getBet());
                players[j].winMoney(players[j].getBet());
                System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());
                
                if ((players[j].getSplit()) && (!players[j].getSplitLost())) {
                  players[j].setSplitWon(true);
                  System.out.println("\nPlayer " + players[j].getName() + "'s second hand with a score of " + players[j].getSplitScore() + " vs " + dealerScore);
                  System.out.println("Player " + players[j].getName() + "'s second hand bet $" + players[j].getSplitBet());
                  players[j].winMoney(players[j].getSplitBet());
                  System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());
                }
              }
            }
          }
          

          if (counter == 0) { System.out.println("NO WINNERS! HAHA!");
          }
        }
        System.out.println("\nEnter any character to continue...");
        waiting = IO.readChar();
        

        for (int j = 0; j < players.length; j++) {
          if (players[j].getScore() != 0)
          {
            if ((!players[j].getLost()) && (!players[j].getWon()))
            {
              if (players[j].getScore() > dealerScore)
              {
                players[j].setWon(true);
                System.out.println("\nPlayer " + players[j].getName() + " WINS with a score of " + players[j].getScore() + " vs " + dealerScore);
                System.out.println("Player " + players[j].getName() + " bet $" + players[j].getBet());
                players[j].winMoney(players[j].getBet());
                System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());
              }
              else if (players[j].getScore() < dealerScore)
              {
                players[j].setWon(false);
                System.out.println("\nPlayer " + players[j].getName() + " LOSES with a score of " + players[j].getScore() + " vs " + dealerScore);
                System.out.println("Player " + players[j].getName() + " bet $" + players[j].getBet());
                players[j].loseMoney(players[j].getBet());
                System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());

              }
              else if (players[j].getScore() == dealerScore)
              {
                System.out.println("\nPlayer " + players[j].getName() + " PUSHES with the dealer since both have a score of " + dealerScore);
                System.out.println("Player " + players[j].getName() + "'s balance remains at $" + players[j].getBalance());
              }
            }
            



            if ((players[j].getSplit()) && (!players[j].getSplitLost()) && (!players[j].getSplitWon()))
            {
              if (players[j].getSplitScore() > dealerScore)
              {
                players[j].setSplitWon(true);
                System.out.println("\nPlayer " + players[j].getName() + "'s second hand WINS with a score of " + players[j].getSplitScore() + " vs " + dealerScore);
                System.out.println("Player " + players[j].getName() + "'s second hand bet $" + players[j].getSplitBet());
                players[j].winMoney(players[j].getSplitBet());
                System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());
              }
              else if (players[j].getSplitScore() < dealerScore)
              {
                players[j].setSplitWon(false);
                System.out.println("\nPlayer " + players[j].getName() + "'s second hand LOSES with a score of " + players[j].getSplitScore() + " vs " + dealerScore);
                System.out.println("Player " + players[j].getName() + "'s second hand bet $" + players[j].getSplitBet());
                players[j].loseMoney(players[j].getSplitBet());
                System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());

              }
              else if (players[j].getSplitScore() == dealerScore)
              {
                System.out.println("\nPlayer " + players[j].getName() + "'s second hand PUSHES with the dealer since both have a score of " + dealerScore);
                System.out.println("Player " + players[j].getName() + "'s balance remains at $" + players[j].getBalance());
              }
            }
            


            if (lostGame)
            {
              System.out.println("\nPlayer " + players[j].getName() + " LOSES with a score of " + players[j].getScore() + " from BUSTING.");
              System.out.println("Player " + players[j].getName() + " bet $" + players[j].getBet());
              players[j].loseMoney(players[j].getBet());
              System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());
            }
            

            if (splitLost)
            {
              System.out.println("\nPlayer " + players[j].getName() + "'s second hand LOSES with a score of " + players[j].getSplitScore() + " from BUSTING.");
              System.out.println("Player " + players[j].getName() + "'s second hand bet $" + players[j].getSplitBet());
              players[j].loseMoney(players[j].getSplitBet());
              System.out.println("Player " + players[j].getName() + "'s balance is now at $" + players[j].getBalance());
            }
          }
        }
        

        System.out.println("\nEnter any character to continue...");
        waiting = IO.readChar();
        
        if ((dealerScore == 21) && (hiddenCard.getValue() == 10))
        {
          System.out.println("\nPaying out insurance...");
          
          for (int k = 0; k < players.length; k++)
          {
            if (players[k].getIfInsured())
            {
              if ((players[k].getLost()) && (players[k].getScore() <= 21))
              {
                players[k].winMoney(players[k].getBet());
                System.out.println("\nPlayer " + players[k].getName() + " was reinbursed with their first hand bet.");
                System.out.println("Player " + players[k].getName() + "'s current balance is $" + players[k].getBalance());
              }
              

              if ((players[k].getSplitLost()) && (players[k].getSplitScore() <= 21))
              {
                players[k].winMoney(players[k].getSplitBet());
                System.out.println("\nPlayer " + players[k].getName() + " was reinbursed with their second hand's bet.");
                System.out.println("Player " + players[k].getName() + "'s current balance is $" + players[k].getBalance());
              }
            }
          }
        }
        





        for (int j = 0; j < players.length; j++) {
          if ((players[j].getBalance() == 0.0D) && (players[j].getScore() != 0))
          {
            System.out.println("\nPlayer " + players[j].getName() + " ran out of money and is out of the game!");
            numPlayers++;
            
            if (numPlayers == 0)
            {
              System.out.println("\nNo more players left in the game! :(");
              System.out.println("GAME OVER");
              
              System.out.println("\nFinal Player Stats:");
              for (int l = 0; l < players.length; l++) {
                System.out.println(l + 1 + ". Players: " + players[l].getName() + ", Final Bank Balance: $" + players[l].getBalance());
              }
              System.out.println("\nPlayed a total of " + round + " round(s).");
              System.out.println("\nThank you for playing Blackjack! :)");
              return;
            }
          }
        }
        
        System.out.println("\nPlayers in the next round: ");
        countThis = 0;
        for (int j = 0; j < players.length; j++)
        {
          if (players[j].getBalance() != 0.0D) {
            countThis++;
            PlayerHands[] updatedPlayers = new PlayerHands[numPlayers];
            System.out.println(countThis + ". " + players[j].getName());
          }
        }
        


        System.out.println("Another round[Y]?");
        answer = IO.readChar();
      }
      while ((answer == 'Y') || (answer == 'y'));
    }
    else
    {
      System.out.println("\nFinal Player Stats:");
      for (int j = 0; j < players.length; j++)
        System.out.println(j + 1 + ". Players: " + players[j].getName() + ", Final Bank Balance: $" + players[j].getBalance());
      System.out.println("\nPlayed a total of " + round + " round(s).");
      System.out.println("\nThank you for playing Blackjack! :)");
      return;
    }
    
    System.out.println("\nFinal Player Stats:");
    for (int j = 0; j < players.length; j++)
      System.out.println(j + 1 + ". Players: " + players[j].getName() + ", Final Bank Balance: $" + players[j].getBalance());
    System.out.println("\nPlayed a total of " + round + " round(s).");
    System.out.println("\nThank you for playing Blackjack! :)");
  }
  




  public static double hintOne(double cNegOne, double cZero, double cOne)
  {
    return cNegOne / (cNegOne + cZero + cOne) * 100.0D;
  }
  


  public static double hintTwo(double cNegOne, double cZero, double cOne)
  {
    return cZero / (cNegOne + cZero + cOne) * 100.0D;
  }
  



  public static double hintThree(double cNegOne, double cZero, double cOne)
  {
    return cOne / (cNegOne + cZero + cOne) * 100.0D;
  }
}
