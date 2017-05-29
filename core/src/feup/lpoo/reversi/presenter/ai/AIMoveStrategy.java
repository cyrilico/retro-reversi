package feup.lpoo.reversi.presenter.ai;

import feup.lpoo.reversi.model.BoardModel;
import feup.lpoo.reversi.model.MoveModel;

public interface AIMoveStrategy {
    MoveModel findMove(BoardModel board, char piece);
}

