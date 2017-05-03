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
    private static ArrayList<String> movesList;

    private BoardModel gameBoard;

    //True if Player 1, False if Player 2
    private boolean turn;

    private GameModel() {
        init();
    }

    public static GameModel getInstance() {
        if (gameInstance == null)
            gameInstance = new GameModel();

        return gameInstance;
    }

    /** Initialize the game */
    private void init() {
        // Initialize board board
        gameBoard = gameBoard.getInstance();

        // init move list
        movesList = new ArrayList<String>();

        // Player 1 plays first default
        turn = true;
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
