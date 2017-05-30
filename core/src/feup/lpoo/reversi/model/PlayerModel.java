package feup.lpoo.reversi.model;

import java.io.Serializable;

public abstract class PlayerModel implements Serializable{
    protected int points;
    protected MoveModel move;
    protected char piece;
    protected boolean ready;

    public PlayerModel(char piece) {
        setPoints(2);
        this.piece = piece;
        ready = false;
    }

    public void setMove(MoveModel move) {
        this.move = move;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public MoveModel getMove() {
        return move;
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
