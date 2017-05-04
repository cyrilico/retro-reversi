package feup.lpoo.reversi.model;

/**
 * Created by antonioalmeida on 04/05/2017.
 */

public class UserModel implements PlayerModel {
    char piece;
    MoveModel move;

    public UserModel(char piece) {
        this.piece = piece;
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
    public char getPiece() {
        return piece;
    }
}
