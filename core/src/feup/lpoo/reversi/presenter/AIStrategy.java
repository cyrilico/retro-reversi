package feup.lpoo.reversi.presenter;

import feup.lpoo.reversi.model.BoardModel;
import feup.lpoo.reversi.model.MoveModel;

/**
 * Created by antonioalmeida on 18/05/2017.
 */

public interface AIStrategy {
    MoveModel findMove(BoardModel board, char piece);
}
