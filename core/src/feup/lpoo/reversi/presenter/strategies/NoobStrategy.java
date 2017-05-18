package feup.lpoo.reversi.presenter.strategies;

import java.util.ArrayList;

import feup.lpoo.reversi.model.BoardModel;
import feup.lpoo.reversi.model.MoveModel;
import feup.lpoo.reversi.presenter.AIStrategy;
import feup.lpoo.reversi.presenter.Evaluation;

/**
 * Created by antonioalmeida on 18/05/2017.
 */

public class NoobStrategy implements AIStrategy {

    public NoobStrategy() {

    }

    @Override
    public MoveModel findMove(BoardModel board, char piece) {
        ArrayList<MoveModel> validMoves = board.getValidMoves(piece);

        ArrayList<MoveModel> sortedMoves = Evaluation.genPriorityMoves(validMoves, piece);

        return sortedMoves.get(0);
    }
}
