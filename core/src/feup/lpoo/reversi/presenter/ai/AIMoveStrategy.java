package feup.lpoo.reversi.presenter.ai;

import feup.lpoo.reversi.model.BoardModel;
import feup.lpoo.reversi.model.MoveModel;

/**
 * Created by cyrilico on 22-05-2017.
 */

public interface AIMoveStrategy {
    MoveModel findMove(BoardModel board, char piece);
}

