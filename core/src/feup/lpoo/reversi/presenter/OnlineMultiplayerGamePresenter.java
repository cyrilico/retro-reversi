package feup.lpoo.reversi.presenter;

import feup.lpoo.reversi.Reversi;
import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.model.UserModel;

/**
 * Created by antonioalmeida on 29/05/2017.
 */

public class OnlineMultiplayerGamePresenter extends GamePresenter {
    String data;

    public OnlineMultiplayerGamePresenter(Reversi reversi) {
        super(reversi);
        GameModel temp = reversi.getPlayServices().getMatchData();

        game = temp;
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
        blackPlayer = game.getCurrentPlayer();
        whitePlayer = game.getNonCurrentPlayer();
    }

    @Override
    public void undoMove() {
        reversi.getPlayServices().takeTurn(game);
    }
}
