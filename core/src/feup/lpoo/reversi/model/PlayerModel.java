package feup.lpoo.reversi.model;

/**
 * Created by antonioalmeida on 04/05/2017.
 */

public abstract class PlayerModel {
    protected int points;
    protected int moveIndex;
    protected char piece;
    protected boolean ready;

    public PlayerModel(char piece) {
        points = 2;
        this.piece = piece;
    }

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

    public void setReady() {
        ready = true;
    }

    public void resetReady() {
        ready = false;
    }

    public boolean isReady() {
        return ready;
    }

}
