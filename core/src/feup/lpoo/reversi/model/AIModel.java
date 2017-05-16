package feup.lpoo.reversi.model;

import java.util.Random;

/**
 * Created by antonioalmeida on 16/05/2017.
 */

public class AIModel extends PlayerModel {
    private Random random;

    public AIModel(char piece) {
        points = 2;
        this.piece = piece;
        random = new Random();
    }

    @Override
    public void setMoveIndex(int index) {
        moveIndex = 0;
    }
}
