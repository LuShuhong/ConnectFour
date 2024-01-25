package com.thg.accelerator23.connectn.ai.lululu;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.BoardStealer;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;

import java.util.ArrayList;
import java.util.Random;


public class JustToWinThePrize extends Player {
  public JustToWinThePrize(Counter counter) {
    //TODO: fill in your name here
    super(counter, JustToWinThePrize.class.getName());
  }

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
    for(int i = 1; i < 8; i ++) {
      if(gameBoard[i][0].getStringRepresentation().equals(gameBoard[i+1][0].getStringRepresentation())){
        if(gameBoard[i-1][0] ==null && gameBoard[i+2] == null) {
          if(i<=5) return i+2;
          else return i-1;
        }
      }
    }


    Random rand = new Random();
    int randomElement = availableColumn.get(rand.nextInt(availableColumn.size()));

    return randomElement;
  }
}
