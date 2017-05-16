package feup.lpoo.reversi.model;

/**
 * Created by antonioalmeida on 04/05/2017.
 */

public class UserModel extends PlayerModel {

    public UserModel(char piece) {
        this.piece = piece;
        points = 2;
    }

    public void setMoveIndex(int index) {
        moveIndex = index;
    }
}
