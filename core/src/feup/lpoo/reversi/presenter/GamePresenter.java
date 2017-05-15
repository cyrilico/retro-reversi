package feup.lpoo.reversi.presenter;


import feup.lpoo.reversi.model.GameModel;

/**
 * Created by antonioalmeida on 12/05/2017.
 */

public class GamePresenter {

    public GamePresenter() { }

    public String getPlayer1Points() {
        String result = "Black: " + String.format("%02d", GameModel.getInstance().getBlackPoints());
        return result;
    }

    public String getPlayer2Points() {
        String result = "White: " + String.format("%02d", GameModel.getInstance().getWhitePoints());
        return result;
    }

    public void undoMove() {
        try {
            if(!GameModel.getInstance().undoMove())
                GameModel.resetGame();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
