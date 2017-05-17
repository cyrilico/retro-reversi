package feup.lpoo.reversi.presenter;

import feup.lpoo.reversi.model.AIModel;
import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.model.PlayerModel;
import feup.lpoo.reversi.model.UserModel;

/**
 * Created by antonioalmeida on 12/05/2017.
 */

public class GamePresenter {

    private GameModel game;
    private PlayerModel blackPlayer;
    private PlayerModel whitePlayer;
    private int type;

    public GamePresenter(int type) {
        this.type = type;

        resetPlayers();
        game = new GameModel(blackPlayer, whitePlayer);
    }

    public String getPlayer1Points() {
        String result = String.format("%02d", game.getBlackPoints());
        return result;
    }

    public String getPlayer2Points() {
        String result = String.format("%02d", game.getWhitePoints());
        return result;
    }

    public void handleInput(int x, int y) throws CloneNotSupportedException {
        int move = getValidMove(x, y);

        if(move != -1) {
            game.getCurrentPlayer().setMoveIndex(move);
            game.getCurrentPlayer().setReady();
        }
    }

    public void updateGame() {
        if(game.getCurrentPlayer().isReady()) {
            game.getCurrentPlayer().resetReady();
            try {
                game.updateGame();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getValidMove(int x, int y) throws CloneNotSupportedException {
        return game.isValidMove(x, y);
    }

    public char getCurrentPiece(int x, int y) {
        char piece = 0;
        piece = game.getPieceAt(x,y);

        return piece;
    }

    public void resetPlayers() {
        if(type == 0) {
            blackPlayer = new UserModel('B'); //Replace with macros
            whitePlayer = new UserModel('W'); //Replace with macros
        }
        else {
            blackPlayer = new UserModel('B'); //Replace with macros
            whitePlayer = new AIModel('W'); //Replace with macros
        }
    }

    public boolean isBlackTurn() {
        return game.getCurrentPlayer() == blackPlayer;
    }

    public boolean isWhiteTurn() {
        return game.getCurrentPlayer() == whitePlayer;
    }

    public void undoMove() {
        try {
            if(!game.undoMove()) {
                resetPlayers();
                game = new GameModel(blackPlayer, whitePlayer);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
