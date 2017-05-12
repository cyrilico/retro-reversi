package feup.lpoo.reversi.model;

/**
 * Created by antonioalmeida on 04/05/2017.
 */

public interface PlayerModel {

    public MoveModel getMove();

    public void setMove(MoveModel move);

    public void setMoveIndex(int index);

    public void setPoints(int points);

    public char getPiece();

    public int getPoints();

}
