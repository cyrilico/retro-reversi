package feup.lpoo.reversi.presenter.ai;

import java.util.ArrayList;
import java.util.PriorityQueue;

import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.model.MoveModel;

/**
 * Created by antonioalmeida on 18/05/2017.
 */

public class BoardEvaluation {

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


    public static int evaluateBoard(char[][] board, char piece) {
        int score = 0;
        for (int r = 0; r < 8; ++r) {
            for (int c = 0; c < 8; ++c) {
                if (board[r][c] == piece)
                    score += BOARD_VALUE[r][c];
                else if (board[r][c] != '-') //TODO: Replace macro
                    score -= BOARD_VALUE[r][c];
            }
        }
        return score;
    }


    //TODO: Move as much as possible to ImmediateMoveStrategy's findMove
    public static MoveModel getImmediateBestMove(ArrayList<MoveModel> moveList) {
        if(moveList.isEmpty()) return null;

        MoveModel bestMove = moveList.get(0);
        double bestScore = -Double.NEGATIVE_INFINITY;

        for (int i=1; i < moveList.size(); ++i) {
            MoveModel currentMove = moveList.get(i);
            double currentMoveScore = BOARD_VALUE[currentMove.getY()][currentMove.getX()];
            if(currentMoveScore > bestScore){
                bestScore = currentMoveScore;
                bestMove = currentMove;
            }
        }

        return bestMove;
    }
}
