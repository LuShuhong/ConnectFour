package com.thg.accelerator23.connectn.ai.lululu;

import com.thehutgroup.accelerator.connectn.player.*;

import java.util.ArrayList;
import java.util.Random;


public class iwanttheconnectfourprize extends Player {
  public iwanttheconnectfourprize(Counter counter) {
    //TODO: fill in your name here
    super(counter, iwanttheconnectfourprize.class.getName());
  }

  public static final int maxDepth = 5;

  //upon testing, the ai doesn't know to start at tge beginning, thus hard code to if first to play
  public boolean isFirstMove(Counter[][] gameBoard) {
    for(int i = 0; i < 10; i++) {
      if(gameBoard[i][0]!= null) return false;
    }
    return true;
  }


  @Override
  public int makeMove(Board board) {
    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it
    Counter counter = this.getCounter();
    Counter[][] gameBoard = board.getCounterPlacements();

    //check isFirstmove comment
    if(isFirstMove(gameBoard)) return 5;

    //this needs to be adjusted with AI
    //if two columns with the same tokens, block or continue from it
//    for(int i = 1; i < 8; i ++) {
//      if(gameBoard[i][0] != null && gameBoard[i+1][0] != null) {
//        if(gameBoard[i][0].getStringRepresentation().equals(gameBoard[i+1][0].getStringRepresentation())){
//          if(gameBoard[i-1][0] ==null && gameBoard[i+2][0] == null) {
//            if(i<=4) return i+2;
//            else return i-1;
//          }
//        }
//      }
//    }

//    for(int i: availableColumn){
//      if
//    }

    int bestMove = 4;
    int bestValue = Integer.MIN_VALUE;
    for(int i = 0; i < 10 ; i ++) {
      try{
        Board newBoard = new Board(board,i,counter);
        int moveValue = minimax(newBoard, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if(moveValue > bestValue) {
          bestMove = i;
          bestValue = moveValue;
        }
      } catch (InvalidMoveException e) {
      }
    }

    //this is a fallback, comment out in testing, but can add in real game
    Random rand = new Random();
    ArrayList<Integer> availableColumn = new ArrayList<>();
    for(int i = 0; i < 10; i ++) {
      if(board.getCounterAtPosition(new Position(i,0)) == null ){
        availableColumn.add(i);
      }
    }
    int randomElement = availableColumn.get(rand.nextInt(availableColumn.size()));
    if(bestMove == -1) {
      return randomElement;
    }

    return bestMove;

  }

  public int minimax(Board board, int depth, boolean isMaximisingPlayer, int alpha, int beta) {
    if(depth == maxDepth) return evaluateBoard(board, this.getCounter());
    if(isMaximisingPlayer){
      int maxEval = Integer.MIN_VALUE;
      for(int i = 0; i < 10; i++){
        try{
            Board newBoard = new Board(board, i, this.getCounter());
            int eval = minimax(newBoard, depth +1, false, alpha, beta);
            maxEval = Math.max(maxEval,eval);
            if(beta <= alpha) break;
        } catch (InvalidMoveException e) {
        }
      }
      return maxEval;
    } else {
      int minEval = Integer.MAX_VALUE;
      for(int i = 0; i < 10; i ++) {
        try {
          Counter opponentCounter = this.getCounter().getOther();
          Board newBoard = new Board(board, i, opponentCounter);
          int eval = minimax(newBoard, depth+1, true, alpha, beta);
          minEval = Math.min(minEval, eval);
          beta = Math.min(beta,eval);
          if(beta <= alpha) break;
        } catch (InvalidMoveException e) {
          //probably do something here, if have time
        }
      }
      return minEval;
    }
  }


  public int evaluateBoard(Board board, Counter myCounter){
    int score = 0;
    Counter opponentCounter = myCounter.getOther();

    //highly inefficient, how to improve?
    for(int i = 0; i < 10; i++) {
      for(int j = 0; j < 8; j ++){
        Position currentPosition = new Position(i,j);
      //  if(board.isWithinBoard(currentPosition)) {
       //   Counter currentCounter = board.getCounterAtPosition(currentPosition);

        //is there way to modify this? it checks horizontal, vertical and diagonal each time
        //top row don't need to check 2nd and 3rd conditions
        //most right column don't need to check 1st, 3rd, 4th conditions
          score += evaluateLine(board, currentPosition, 1,0, myCounter,opponentCounter);
          score += evaluateLine(board, currentPosition, 0,1, myCounter,opponentCounter);
          score += evaluateLine(board, currentPosition, 1,1, myCounter,opponentCounter);
          score += evaluateLine(board, currentPosition, 1,-1, myCounter,opponentCounter);
   //     }
      }
    }
    return score;
  };

  //ask the faculty if there's a way to improve, seems like inefficient and may not be a good evaluation function
  public int evaluateLine(Board board, Position start, int dx, int dy, Counter myCounter, Counter opponentCounter){
    int myCount = 0;
    int opponentCount = 0;
    int score = 0;

    for (int i = 0; i < 4; i++){
      int x = start.getX() + i*dx;
      int y = start.getY() + i*dy;
      Position currentPosition = new Position(x, y);

      if(board.isWithinBoard(currentPosition)) {
        Counter currentCounter = board.getCounterAtPosition(currentPosition);
        if(currentCounter == myCounter){
          myCount ++;
        } else if (currentCounter == opponentCounter) {
          opponentCount ++;
        }
      }
    }

    if(myCount == 4) score += 10000;
    else if (myCount == 3 && opponentCount == 0) score += 100;
    else if (myCount == 2 && opponentCount == 0) score += 10;

    if(opponentCount == 4) score -= 10000;
    else if (opponentCount == 3 && myCount == 0) score -=77;
    else if (opponentCount == 2 && myCount == 0) score -=7;

    return score;
  }
}
