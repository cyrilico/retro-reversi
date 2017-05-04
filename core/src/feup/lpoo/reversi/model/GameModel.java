package feup.lpoo.reversi.model;

import java.util.ArrayList;

/**
 * Created by antonioalmeida on 02/05/2017.
 */

public class GameModel {

    protected static final char EMPTY_PIECE = '-';
    protected static final char BLACK_PIECE = 'B';
    protected static final char WHITE_PIECE = 'W';
    protected static final int BOARD_SIZE = 8;

    //The unique instance of this class
    private static GameModel gameInstance;

    //List of moves during the game
    private static ArrayList<MoveModel> movesList;

    private BoardModel gameBoard;

    //True if Player 1, False if Player 2
    private boolean turn;

    private GameModel() {
        // Initialize board board
        gameBoard = new BoardModel();

        // init move list
        movesList = new ArrayList<MoveModel>();

        // Player 1 plays first default
        turn = true;
    }

    public static GameModel getInstance() {
        if (gameInstance == null)
            gameInstance = new GameModel();

        return gameInstance;
    }

    public ArrayList<MoveModel> getValidMoves(char piece) {
        ArrayList<MoveModel> result =  new ArrayList<MoveModel>();

        for(int y = 0; y < BOARD_SIZE; y++) {
            for(int x = 0; x < BOARD_SIZE; x++) {
                MoveModel temp = gameBoard.getValidMove(x,y,piece);

                if(temp != null)
                    result.add(temp);
            }
        }

        return result;
    }

    public void makeMove(MoveModel move) {
        gameBoard.setPieceAt(move.getX(), move.getY(), move.getPiece());

        for(Integer[] pos : move.getChangedPositions())
            gameBoard.rotatePiece(pos[0], pos[1]);

        movesList.add(move);
    }

    public BoardModel getGameBoard() {
        return gameBoard;
    }

    public String getCurrentBoard() {
        String result = "";

        char[][] matrix = gameBoard.getCurrentBoard();

        for(char[] line : matrix) {
            for(char element : line)
                result += element;
            result += '\n';
        }

        return result;
    }

    public ArrayList<String> getMovesList() {
        return (ArrayList<String>) movesList.clone();
    }


}
