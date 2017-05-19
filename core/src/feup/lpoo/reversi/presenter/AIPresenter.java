package feup.lpoo.reversi.presenter;

import com.badlogic.gdx.Game;

import feup.lpoo.reversi.model.BoardModel;
import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.model.MoveModel;
import feup.lpoo.reversi.presenter.strategies.NoobStrategy;

/**
 * Created by antonioalmeida on 18/05/2017.
 */

public class AIPresenter {
    GameModel game;
    AIStrategy strategy;


    public AIPresenter() {
        strategy = new NoobStrategy();
    }

    public void setGame(GameModel game) {
        this.game = game;
    }

    public MoveModel findMove(char piece) {
        System.out.println("Cenas");
        System.out.println(game.getGameBoard().getCurrentPoints(piece));
        MoveModel move = strategy.findMove(game.getGameBoard(), piece);

       return move;
    }
}
