package feup.lpoo.reversi.model;

import java.util.ArrayList;

/**
 * Created by antonioalmeida on 02/05/2017.
 */

public class GameModel {

    protected static final char EMPTY_PIECE = '-';
    protected static final char BLACK_PIECE = 'B';
    protected static final char WHITE_PIECE = 'W';
    protected static final char SUGGESTION_PIECE = 'X';
    protected static final int BOARD_SIZE = 8;

    //The unique instance of this class
    private static GameModel gameInstance;

    //List of moves made during the game
    private static ArrayList<MoveModel> movesList;

    //Current valid moves
    private ArrayList<MoveModel> currentMoves;

    private BoardModel gameBoard;

    private PlayerModel player1;
    private PlayerModel player2;

    private boolean turn; // true if player1, pla

    private GameModel() {
        // Initialize board board
        gameBoard = new BoardModel();

        // init move list
        movesList = new ArrayList<MoveModel>();

        player1 = new UserModel(BLACK_PIECE);
        player2 = new UserModel(WHITE_PIECE);

        turn = true;
        currentMoves = getValidMoves(getCurrentPlayer());
        gameBoard.setSuggestions(currentMoves);
    }

    public static GameModel getInstance() {
        if (gameInstance == null)
            gameInstance = new GameModel();

        return gameInstance;
    }

    public ArrayList<MoveModel> getValidMoves(PlayerModel player) {
        ArrayList<MoveModel> result =  new ArrayList<MoveModel>();
        char piece = player.getPiece();

        for(int y = 0; y < BOARD_SIZE; y++) {
            for(int x = 0; x < BOARD_SIZE; x++) {
                MoveModel temp = gameBoard.getValidMove(x,y,piece);

                if(temp != null)
                    result.add(temp);
            }
        }

        return result;
    }

    public boolean hasValidMoves(PlayerModel player) {
        if(currentMoves.size() > 0)
            return true;

        return false;
    }

    //Returns -1 if not a valid move, returns the index in the currentMoves otherwise
    public int isValidMove(int x, int y) {
        for(int i = 0; i < currentMoves.size(); i++)
            if(currentMoves.get(i).getX() == x && currentMoves.get(i).getY() == y)
                return i;

        return -1;
    }

    public void makeMove(MoveModel move) {
        gameBoard.setPieceAt(move.getX(), move.getY(), move.getPiece());

        for(Integer[] pos : move.getChangedPositions())
            gameBoard.rotatePiece(pos[0], pos[1]);

        movesList.add(move);
    }

    public void updateGame() {
        MoveModel toMake = getCurrentPlayer().getMove();

        makeMove(toMake);

        updateTurn();
        updatePoints();
        currentMoves = getValidMoves(getCurrentPlayer());
        gameBoard.setSuggestions(currentMoves);
    }

    public void updatePoints() {
        int p1 = gameBoard.getCurrentPoints(player1.getPiece());
        int p2 = gameBoard.getCurrentPoints(player2.getPiece());

        player1.setPoints(p1);
        player2.setPoints(p2);
    }

    public PlayerModel getCurrentPlayer() {
        if(turn)
            return player1;

        return player2;
    }

    public PlayerModel getNonCurrentPlayer() {
        if(turn)
            return player2;

        return player1;
    }

    public void updateTurn() {
        if(hasValidMoves(getNonCurrentPlayer()))
            turn = !turn;
    }

    //Returns piece at given positions, considering both gameBoard's matrixes
    public char getPieceAt(int x, int y) {

        char temp = gameBoard.getPieceAt(x,y);

        if(temp == GameModel.EMPTY_PIECE)
            return gameBoard.getSuggestionAt(x,y);

        return temp;
    }

    public BoardModel getGameBoard() {
        return gameBoard;
    }

    public ArrayList<MoveModel> getCurrentMoves() {
        return currentMoves;
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

    public ArrayList<MoveModel> getMovesList() {
        return (ArrayList<MoveModel>) movesList.clone();
    }

    public int getPlayer1Points() {
        return player1.getPoints();
    }

    public int getPlayer2Points() {
        return player2.getPoints();
    }
}
