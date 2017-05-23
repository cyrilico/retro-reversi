package feup.lpoo.reversi.presenter;

import feup.lpoo.reversi.model.BoardModel;
import feup.lpoo.reversi.model.MoveModel;

/**
 * Created by cyrilico on 22-05-2017.
 */

public class ImmediateMoveStrategy implements AIMoveStrategy {

    @Override
    public MoveModel findMove(BoardModel board, char piece) {
        return BoardEvaluation.getImmediateBestMove(board.getValidMoves(piece));
    }
}
