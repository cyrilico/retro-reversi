package feup.lpoo.reversi.model;

import java.util.ArrayList;

public class MoveModel {
    private int x;
    private int y;
    private char piece;

    private ArrayList<Integer[]> changedPositions;

    public MoveModel(int x, int y, char piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
        changedPositions = new ArrayList<Integer[]>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getPiece() {
        return piece;
    }

    public void addChangedPositions(ArrayList<Integer[]> toAdd) {
       changedPositions.addAll(toAdd);
    }

    public ArrayList<Integer[]> getChangedPositions() {
        return changedPositions;
    }

    @Override
    public boolean equals(Object otherMove){
        if(otherMove == null || !(otherMove instanceof MoveModel)) return false;
        MoveModel otherMoveCasted = ((MoveModel)otherMove);
        return x == otherMoveCasted.getX() && y == otherMoveCasted.getY() && piece == otherMoveCasted.getPiece();
    }
}
