package feup.lpoo.reversi.presenter.ai;

import java.util.ArrayList;

import feup.lpoo.reversi.model.BoardModel;
import feup.lpoo.reversi.model.MoveModel;

import java.util.Random;

/**
 * Created by antonioalmeida on 18/05/2017.
 */

public class RandomMoveStrategy implements feup.lpoo.reversi.presenter.ai.AIMoveStrategy {
    private Random generator;

    public RandomMoveStrategy() {
        generator = new Random();
    }

    @Override
    public MoveModel findMove(BoardModel board, char piece) {
        ArrayList<MoveModel> validMoves = board.getValidMoves(piece);
        int randomMoveIndex = generator.nextInt(validMoves.size());

        return validMoves.get(randomMoveIndex);
    }
}
