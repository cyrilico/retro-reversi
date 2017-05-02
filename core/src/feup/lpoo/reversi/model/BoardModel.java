package feup.lpoo.reversi.model;

/**
 * Created by antonioalmeida on 02/05/2017.
 */

public class BoardModel {
    private char[][] matrix;

    private BoardModel boardInstance;

    private static final int BOARD_SIZE = 8;

    private BoardModel() {

    }

    public BoardModel getInstance() {
        if(boardInstance == null)
            boardInstance = new BoardModel();

        return boardInstance;
    }

    private void init() {
    }

    public char[][] getCurrentBoard() {
        char[][] temp = new char[BoardModel.BOARD_SIZE][BoardModel.BOARD_SIZE];

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

