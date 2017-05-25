package feup.lpoo.reversi.presenter.ai;

import feup.lpoo.reversi.model.BoardModel;
import feup.lpoo.reversi.model.MoveModel;

/**
 * Created by cyrilico on 22-05-2017.
 */

public class ImmediateMoveStrategy implements feup.lpoo.reversi.presenter.ai.AIMoveStrategy {

    @Override
    public MoveModel findMove(BoardModel board, char piece) {
        return feup.lpoo.reversi.presenter.ai.BoardEvaluation.getImmediateBestMove(board.getValidMoves(piece));
    }
}
