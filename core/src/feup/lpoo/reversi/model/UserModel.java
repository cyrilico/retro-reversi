package feup.lpoo.reversi.model;

/**
 * Created by antonioalmeida on 04/05/2017.
 */

public class UserModel implements PlayerModel {
    private char piece;
    private MoveModel move;
    private int points;

    public UserModel(char piece) {
        this.piece = piece;
        points = 2;
    }

    @Override
    public MoveModel getMove() {
        return move;
    }

    @Override
    public void setMove(MoveModel move) {
        this.move = move;
    }

    @Override
    public void setMoveIndex(int index) {
        move = GameModel.getInstance().getCurrentMoves().get(index);
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public char getPiece() {
        return piece;
    }

    @Override
    public int getPoints() {
        return points;
    }
}
