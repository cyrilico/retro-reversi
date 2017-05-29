package feup.lpoo.reversi.presenter;

import feup.lpoo.reversi.Reversi;
import feup.lpoo.reversi.model.AIModel;
import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.model.UserModel;
import feup.lpoo.reversi.presenter.ai.AIPresenter;

public class LocalMultiplayerGamePresenter extends GamePresenter {

    public LocalMultiplayerGamePresenter(Reversi reversi) {
        super(reversi);
        initPlayers();
        game = new GameModel(blackPlayer, whitePlayer);
    }

    @Override
    public void restartGame() {
        initPlayers();
        game = new GameModel(blackPlayer, whitePlayer);
    }

    @Override
    public void updateAchievements() {

    }

    @Override
    public void initPlayers() {
            blackPlayer = new UserModel('B');
            whitePlayer = new UserModel('W'); //Replace with macros
    }

    @Override
    public void undoMove() {
        game.undoMove(1);
    }
}
