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

    public GamePresenter() {
        blackPlayer = new UserModel('B'); //Replace with macros
        whitePlayer = new AIModel('W'); //Replace with macros
        game = new GameModel(blackPlayer, whitePlayer);
    }

    public String getPlayer1Points() {
        String result = "Black: " + String.format("%02d", game.getBlackPoints());
        return result;
    }

    public String getPlayer2Points() {
        String result = "White: " + String.format("%02d", game.getWhitePoints());
        return result;
    }

    public void handleInput(int x, int y) throws CloneNotSupportedException {
        int move = getValidMove(x, y);

        if(move != -1) {
            game.getCurrentPlayer().setMoveIndex(move);
            game.updateGame();
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

    public void undoMove() {
        try {
            if(!game.undoMove()) {
                blackPlayer = new UserModel('B'); //Replace with macros
                whitePlayer = new AIModel('W'); //Replace with macros
                game = new GameModel(blackPlayer, whitePlayer);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
