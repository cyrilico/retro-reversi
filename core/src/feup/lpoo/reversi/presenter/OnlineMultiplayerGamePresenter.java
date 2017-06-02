package feup.lpoo.reversi.presenter;

import feup.lpoo.reversi.Reversi;
import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.model.UserModel;

/**
 * Created by antonioalmeida on 29/05/2017.
 */

public class OnlineMultiplayerGamePresenter extends GamePresenter {

    public OnlineMultiplayerGamePresenter(Reversi reversi) {
        super(reversi);
        game = reversi.getPlayServices().getMatchData();
        initPlayers();
    }

    @Override
    public void restartGame() {
        blackPlayer = new UserModel('B');
        blackPlayer.setActive(true);
        whitePlayer = new UserModel('W');
        whitePlayer.setActive(false);
        game = new GameModel(blackPlayer, whitePlayer);
        reversi.getPlayServices().rematch();
    }

    @Override
    public void playServicesCalls() {
        reversi.getPlayServices().takeLastTurn(game);
        reversi.getPlayServices().finishMatch();
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
