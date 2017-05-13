package feup.lpoo.reversi.presenter;


import feup.lpoo.reversi.model.GameModel;

/**
 * Created by antonioalmeida on 12/05/2017.
 */

public class GamePresenter {

    public GamePresenter() { }

    public String getPlayer1Points() {
        String result = "Black: " + String.format("%02d", GameModel.getInstance().getPlayer1Points());
        return result;
    }

    public String getPlayer2Points() {
        String result = "White: " + String.format("%02d", GameModel.getInstance().getPlayer2Points());
        return result;
    }

}
