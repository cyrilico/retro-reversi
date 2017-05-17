package feup.lpoo.reversi.model;

/**
 * Created by antonioalmeida on 16/05/2017.
 */

public class AIModel extends PlayerModel {
    public AIModel(char piece) {
        super(piece);
    }

    @Override
    public void setMoveIndex(int index) {
        moveIndex = 0;
    }

    @Override
    public boolean isReady() {
        return true;
    }
}
