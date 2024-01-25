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
    Counter[][] gameBoard = BoardStealer.getBoard(board);

    ArrayList<Integer> availableColumn = new ArrayList<>();
    for(int i=0; i<10;i++){
      if(gameBoard[i][7] == null ){
        availableColumn.add(i);
      }
    }

    Random rand = new Random();
    int randomElement = availableColumn.get(rand.nextInt(availableColumn.size()));

    return randomElement;
  }
}
