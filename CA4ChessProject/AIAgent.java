import java.util.*;

public class AIAgent {
  Random rand;

  public AIAgent() {
    rand = new Random();
  }

  // The method randomMove takes as input a stack of potential moves that the AI
  // agent can make. The agent uses a rondom number generator to randomly select a
  // move from the inputted Stack and returns this to the calling agent.
  public Move randomMove(Stack possibilities) {
    int moveID = rand.nextInt(possibilities.size());
    System.out.println("AI agent selected RandomMove: : " + moveID);
    for (int i = 1; i < (possibilities.size() - (moveID)); i++) {
      possibilities.pop();
    }
    Move selectedMove = (Move) possibilities.pop();
    return selectedMove;
  }

  // nextBestMove agent
  public Move nextBestMove(Stack whitePossibleMoves, Stack blackPossibleMoves) {
    Stack whitePieces = (Stack) whitePossibleMoves.clone();// Getting stack of moves that white pieces can make
    Stack black = (Stack) blackPossibleMoves.clone();// Getting stack of moves that black piece can make
    Move whiteMove, attackingMove, normalMove;
    Square blackPosition;
    int Points = 0;// Points for pieces
    int chosenPiece = 0;
    attackingMove = null;

    while (!whitePossibleMoves.empty()) {
      whiteMove = (Move) whitePossibleMoves.pop();
      normalMove = whiteMove;

      // Assiging a Point to the centre of the playing board
      if ((normalMove.getStart().getYC() < normalMove.getLanding().getYC()) && (normalMove.getLanding().getXC() == 3)
          && (normalMove.getLanding().getYC() == 3)
          || (normalMove.getLanding().getXC() == 4) && (normalMove.getLanding().getYC() == 3)
          || (normalMove.getLanding().getXC() == 3) && (normalMove.getLanding().getYC() == 4)
          || (normalMove.getLanding().getXC() == 4) && (normalMove.getLanding().getYC() == 4)) {
        Points = 0;
        // Assiging nextBestMove
        if (Points > chosenPiece) {
          chosenPiece = Points;
          attackingMove = normalMove;
        }
      }
      // Returning attackingMove if piece has higher Point value than centre position
      /***************************************************************************************
       * Title: chess-ai-position-evaluation.js Author: Brandon Yanofsky Date: 4 Jul
       * 2017 Availability:
       * https://gist.github.com/byanofsky/334ba66c34e11264b718191f807a20d3 &&
       * https://byanofsky.com/2017/07/06/building-a-simple-chess-ai/
       *
       * Used the value for each piece idea from this code and the idea behind these
       * values
       ***************************************************************************************/
      while (!black.isEmpty()) {
        Points = 0;
        blackPosition = (Square) black.pop();
        if ((normalMove.getLanding().getXC() == blackPosition.getXC())
            && (normalMove.getLanding().getYC() == blackPosition.getYC())) {
          // Checking for black pawns and assigning points to it
          if (blackPosition.getName().equals("BlackPawn")) {
            Points = 100;
          }
          // Checking for black bishop and assigning points to it
          else if (blackPosition.getName().equals("BlackBishop")) {
            Points = 350;
          }
          // Checking for black bishop and assigning points to it
          else if (blackPosition.getName().equals("BlackKnight")) {
            Points = 350;
          }
          // Checking for black Rook and assigning points to it
          else if (blackPosition.getName().equals("BlackRook")) {
            Points = 525;
          }
          // Checking for black Queen and assigning points to it
          else if (blackPosition.getName().equals("BlackQueen")) {
            Points = 1000;
          }
          // Checking for black King and assigning points to it
          else if (blackPosition.getName().equals("BlackKing")) {
            Points = 10000;
          }
        }
        // Updating nextBestMove
        if (Points > chosenPiece) {
          chosenPiece = Points;
          attackingMove = normalMove;
        }
      }
      // Reloading black squares
      black = (Stack) blackPossibleMoves.clone();
    }
    // Will use nextBestMove if possible otherwise we will return randomMove agent
    // instead and make a random move
    if (chosenPiece > 0) {
      System.out.println("AI agent selected NextBestMove: " + chosenPiece);
      return attackingMove;
    }

    return randomMove(whitePieces);// randomMove agent is being returned
  }

  // Start of two levels deeps
  public Move twoLevelsDeep(Stack whitePossibleMoves, Stack blackPossibleMoves, int depth) {
    Stack blackPieces = (Stack) blackPossibleMoves.clone();// Getting stack of moves that black pieces can make
    Stack whitePieces = (Stack) whitePossibleMoves.clone();// Getting stack of moves that white pieces can make
    Move whiteMove, attackingMove, normalMove;
    Square blackPosition;
    int Points = 0;// Points for pieces
    int chosenPiece = 0;
    attackingMove = null;

    while (!whitePossibleMoves.empty()) {
      whiteMove = (Move) whitePossibleMoves.pop();
      normalMove = whiteMove;

      // Assiging a Point to the centre of the playing board
      if ((normalMove.getStart().getYC() < normalMove.getLanding().getYC()) && (normalMove.getLanding().getXC() == 3)
          && (normalMove.getLanding().getYC() == 3)
          || (normalMove.getLanding().getXC() == 4) && (normalMove.getLanding().getYC() == 3)
          || (normalMove.getLanding().getXC() == 3) && (normalMove.getLanding().getYC() == 4)
          || (normalMove.getLanding().getXC() == 4) && (normalMove.getLanding().getYC() == 4)) {
        Points = 0;
        // Assiging nextBestMove
        if (Points > chosenPiece) {
          chosenPiece = Points;
          attackingMove = normalMove;
        }
      }
      // Returning attackingMove if piece has higher Point value than centre position
      /***************************************************************************************
       * Title: chess-ai-position-evaluation.js Author: Brandon Yanofsky Date: 4 Jul
       * 2017 Availability:
       * https://gist.github.com/byanofsky/334ba66c34e11264b718191f807a20d3 &&
       * https://byanofsky.com/2017/07/06/building-a-simple-chess-ai/
       *
       * Used the value for each piece idea from this code and the idea behind these
       * values
       ***************************************************************************************/
      while (!blackPieces.isEmpty()) {
        Points = 0;
        blackPosition = (Square) blackPieces.pop();
        if ((normalMove.getLanding().getXC() == blackPosition.getXC())
            && (normalMove.getLanding().getYC() == blackPosition.getYC())) {
          // Checking for black pawns and assigning points to it
          if (blackPosition.getName().equals("BlackPawn")) {
            Points = 100;
          }
          // Checking for black bishop and assigning points to it
          else if (blackPosition.getName().equals("BlackBishop")) {
            Points = 350;
          }
          // Checking for black bishop and assigning points to it
          else if (blackPosition.getName().equals("BlackKnight")) {
            Points = 350;
          }
          // Checking for black Rook and assigning points to it
          else if (blackPosition.getName().equals("BlackRook")) {
            Points = 525;
          }
          // Checking for black Queen and assigning points to it
          else if (blackPosition.getName().equals("BlackQueen")) {
            Points = 1000;
          }
          // Checking for black King and assigning points to it
          else if (blackPosition.getName().equals("BlackKing")) {
            Points = 10000;
          }
        }
        // Updating nextBestMove
        if (Points > chosenPiece) {
          chosenPiece = Points;
          attackingMove = normalMove;
        }
      }
      // Reloading black squares
      blackPieces = (Stack) blackPossibleMoves.clone();
    }

    // Will use nextBestMove if possible otherwise we will return randomMove agent
    // instead and make a random move
    if (chosenPiece > 0) {
      System.out.println("AI agent selected NextBestMove: " + chosenPiece);
      return attackingMove;
    }

    return randomMove(whitePieces);// randomMove agent is being returned
  }
}
