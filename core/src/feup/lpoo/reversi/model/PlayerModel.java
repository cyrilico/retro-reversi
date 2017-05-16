package feup.lpoo.reversi.model;

/**
 * Created by antonioalmeida on 04/05/2017.
 */

public abstract class PlayerModel {
    protected int points;
    protected int moveIndex;
    protected char piece;

    public abstract void setMoveIndex(int index);

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMoveIndex() {
        return moveIndex;
    }

    public char getPiece() {
        return piece;
    }

    public int getPoints() {
        return points;
    }

}
