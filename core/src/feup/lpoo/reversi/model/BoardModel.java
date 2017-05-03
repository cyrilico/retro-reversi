package feup.lpoo.reversi.model;

import com.badlogic.gdx.Game;

/**
 * Created by antonioalmeida on 02/05/2017.
 */

public class BoardModel {
    private char[][] matrix;

    private static final int[][] directions = {
            {-1,-1},
            {-1,0},
            {-1,1},
            {0,-1},
            {0,1},
            {1,-1},
            {1,0},
            {1,1}
    }; //8 possible directions to test

    private BoardModel boardInstance;

    private BoardModel() {
        init();
    }

    public BoardModel getInstance() {
        if(boardInstance == null)
            boardInstance = new BoardModel();

        return boardInstance;
    }

    private void init() {
        matrix = generateMatrix(GameModel.BOARD_SIZE, GameModel.BOARD_SIZE);
    }

    public void setPieceAt(int x, int y, char piece) {
        if(x < GameModel.BOARD_SIZE && y < GameModel.BOARD_SIZE)
            matrix[y][x] = piece;
    }

    public boolean validPosition(int x, int y) {
        if(x < GameModel.BOARD_SIZE && y < GameModel.BOARD_SIZE && x >= 0 && y >0)
            return true;

        return false;
    }

    public char getPieceAt(int x, int y) {
        if(validPosition(x,y))
            return matrix[y][x];

        return GameModel.EMPTY_PIECE;
    }

    public void rotatePiece(int x, int y) {
        if(getPieceAt(x,y) == GameModel.EMPTY_PIECE)
            return;

        if(getPieceAt(x,y) == GameModel.BLACK_PIECE)
            setPieceAt(x,y, GameModel.WHITE_PIECE);
        else
            setPieceAt(x,y, GameModel.BLACK_PIECE);
    }

    public boolean isValidMove(int x, int y, char piece) {

        if(getPieceAt(x,y) != GameModel.EMPTY_PIECE)
            return false;

        for(int i = 0; i < directions.length; i++)
            if(checkValidDirection(x, y, piece, directions[i]))
                return true;

        return false;
    }

    public boolean checkValidDirection(int x, int y, char piece, int[] direction) {
        char oppositePiece = (piece == GameModel.BLACK_PIECE) ? GameModel.WHITE_PIECE : GameModel.BLACK_PIECE;

        boolean hasOppPieceBetween = false;
        boolean validDirection =  false;

        char currentPiece = piece;

        int index = 1;
        while(currentPiece != GameModel.EMPTY_PIECE) {
            currentPiece = getPieceAt(x + index * direction[0], y + index * direction[1]);

            if(currentPiece == piece && hasOppPieceBetween) {
                validDirection = true; break;
            }

            if(currentPiece == oppositePiece)
                hasOppPieceBetween = true;
            else break; //If currentPiece == empty or == piece, but hasOppPiece == false

            index++;
        }

        return validDirection;
    }

    public char[][] getCurrentBoard() {
        char[][] temp = new char[GameModel.BOARD_SIZE][GameModel.BOARD_SIZE];

        int index = 0;

        for(char[] line : matrix)
            temp[index++] = (char[])line.clone();

        return temp;
    }

    private char[][] generateMatrix(int width, int height) {
        char [][] temp = new char[height][width];

        for (char[] line : temp) {
            for (int i = 0; i < line.length; i++) {
                line[i] = GameModel.EMPTY_PIECE;
            }
        }

        return temp;
    }
}

