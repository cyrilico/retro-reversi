package feup.lpoo.reversi.presenter;

import java.util.ArrayList;
import java.util.PriorityQueue;

import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.model.MoveModel;

/**
 * Created by antonioalmeida on 18/05/2017.
 */

public class Evaluation {

    final static int[][] BOARD_VALUE = {
            {100, -1, 5, 2, 2, 5, -1, 100},
            {-1, -10,1, 1, 1, 1,-10, -1},
            {5 , 1,  1, 1, 1, 1,  1,  5},
            {2 , 1,  1, 0, 0, 1,  1,  2},
            {2 , 1,  1, 0, 0, 1,  1,  2},
            {5 , 1,  1, 1, 1, 1,  1,  5},
            {-1,-10, 1, 1, 1, 1,-10, -1},
            {100, -1, 5, 2, 2, 5, -1, 100}
    };

    public static int evaluateBoard(char[][] board, char piece, char oppPiece) {
        int score = 0;
        for (int r = 0; r < 8; ++r) {
            for (int c = 0; c < 8; ++c) {
                if (board[r][c] == piece)
                    score += BOARD_VALUE[r][c];
                else if (board[r][c] == oppPiece)
                    score -= BOARD_VALUE[r][c];
            }
        }
        return score;
    }

    public static ArrayList<MoveModel> genPriorityMoves(ArrayList<MoveModel> moveList, char piece) {
        PriorityQueue<MoveScore> moveQueue = new PriorityQueue<MoveScore>();

        for (int i=0; i < moveList.size(); ++i) {
            MoveModel move = moveList.get(i);
            MoveScore moveScore = new MoveScore(move, BOARD_VALUE[move.getY()][move.getX()]);
            moveQueue.add(moveScore);
        }

        moveList = new ArrayList<MoveModel>();
        while (!moveQueue.isEmpty()) {
            moveList.add(moveQueue.poll().getMove());
        }

        return moveList;
    }
}
