package com.thg.accelerator23.connectn.ai.lululu;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.InvalidMoveException;
import com.thehutgroup.accelerator.connectn.player.Player;

import java.util.ArrayList;
import java.util.Random;


public class iwanttheconnectfourprize extends Player {
  public iwanttheconnectfourprize(Counter counter) {
    //TODO: fill in your name here
    super(counter, iwanttheconnectfourprize.class.getName());
  }

  public static final int maxDepth = 4;

  @Override
  public int makeMove(Board board) {
    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it
    Counter counter = this.getCounter();
    Counter[][] gameBoard = board.getCounterPlacements();


    ArrayList<Integer> availableColumn = new ArrayList<>();
    for(int i = 0; i < 10; i ++) {
      if(gameBoard[i][7] == null ){
        availableColumn.add(i);
      }
    }

    //this needs to be adjusted with AI
    //if two columns with the same tokens, block or continue from it
    for(int i = 1; i < 8; i ++) {
      if(gameBoard[i][0] != null && gameBoard[i+1][0] != null) {
        if(gameBoard[i][0].getStringRepresentation().equals(gameBoard[i+1][0].getStringRepresentation())){
          if(gameBoard[i-1][0] ==null && gameBoard[i+2][0] == null) {
            if(i<=4) return i+2;
            else return i-1;
          }
        }
      }
    }

    Random rand = new Random();
    int randomElement = availableColumn.get(rand.nextInt(availableColumn.size()));

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
      return bestMove;
    }




    return randomElement;
  }

  public int minimax(Board board, int depth, boolean isMaximisingPlayer, int alpha, int beta) {
    if(depth == maxDepth) return evaluateBoard(board, this.getCounter());
    if(isMaximisingPlayer){
      int maxEval = Integer.MIN_VALUE;
      for(int i = 0; i < 10; i++){
        try{

        }
      }
    }
    return 0;
  }

  public int evaluateBoard(Board board, Counter myCounter){
    return 0;
  };
}
