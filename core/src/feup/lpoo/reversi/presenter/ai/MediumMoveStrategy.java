package feup.lpoo.reversi.presenter.ai;

import java.util.ArrayList;

import feup.lpoo.reversi.model.BoardModel;
import feup.lpoo.reversi.model.MoveModel;

public class MediumMoveStrategy implements CalculatedAIMoveStrategy {

    @Override
    public MoveModel findMove(BoardModel board, char piece) {
        ArrayList<MoveModel> moveList = board.getValidMoves(piece);
        if(moveList.isEmpty()) return null;

        MoveModel bestMove = moveList.get(0);
        int bestScore = -Integer.MIN_VALUE;

        for (int i=1; i < moveList.size(); ++i) {
            MoveModel currentMove = moveList.get(i);
            int currentMoveScore = BOARD_VALUE[currentMove.getY()][currentMove.getX()];
            if(currentMoveScore > bestScore){
                bestScore = currentMoveScore;
                bestMove = currentMove;
            }
        }

        return bestMove;
    }
}
