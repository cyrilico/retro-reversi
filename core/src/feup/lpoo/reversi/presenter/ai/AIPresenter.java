package feup.lpoo.reversi.presenter.ai;

import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.model.MoveModel;

public class AIPresenter {
    GameModel game;
    AIMoveStrategy strategy;


    public AIPresenter(AIMoveStrategy strategyChosen){
        this.strategy = strategyChosen;
    }

    public void setGame(GameModel game) {
        this.game = game;
    }

    public MoveModel findMove(char piece) {
       MoveModel move = strategy.findMove(game.getGameBoard(), piece);

       return move;
    }
}
