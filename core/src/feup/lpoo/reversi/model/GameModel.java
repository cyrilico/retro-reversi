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

    /**
     * Instance of TurnState enumeration that stores current turn state
     */
    private TurnState turn;

    /**
     * Instance of GameState enumeration that stores current turn state
     */
    private GameState state;

    /**
    * List of moves made during the game
    */
    private static ArrayList<MoveModel> movesList;

    /**
    * Current valid moves (updated on every turn)
    */
    private ArrayList<MoveModel> currentMoves;

    private BoardModel gameBoard;

    //Saves previous game state
    private GameCareTaker caretaker;

    private PlayerModel blackPlayer;
    private PlayerModel whitePlayer;

    public GameModel(PlayerModel black, PlayerModel white) {
        gameBoard = new BoardModel();
        movesList = new ArrayList<MoveModel>();

        blackPlayer = black;
        whitePlayer = white;

        caretaker = new GameCareTaker();

        turn = TurnState.BLACK;
        state = GameState.RUNNING;

        currentMoves = getValidMoves(getCurrentPlayer());
        gameBoard.setSuggestions(currentMoves);
    }

    public ArrayList<MoveModel> getValidMoves(PlayerModel player) {
        ArrayList<MoveModel> result = gameBoard.getValidMoves(player.getPiece());

        return result;
    }

    //Returns null if not a valid move, returns the index in the currentMoves otherwise
    public MoveModel getValidMove(int x, int y) {
        for(int i = 0; i < currentMoves.size(); i++)
            if(currentMoves.get(i).getX() == x && currentMoves.get(i).getY() == y)
                return currentMoves.get(i);

        return null;
    }

    public void makeMove(MoveModel move) {
        gameBoard.setPieceAt(move.getX(), move.getY(), move.getPiece());

        for(Integer[] pos : move.getChangedPositions())
            gameBoard.rotatePiece(pos[0], pos[1]);

        movesList.add(move);
    }

    public boolean updateGame() throws CloneNotSupportedException {
        if(isOver())
            return false;

        MoveModel toMake = getCurrentPlayer().getMove();
        makeMove(toMake);

        updateTurn();
        updatePoints();
        updateGameState();
        currentMoves = getValidMoves(getCurrentPlayer());
        gameBoard.setSuggestions(currentMoves);
        caretaker.add(saveState());
        return true;
    }

    public void updatePoints() {
        int p1 = gameBoard.getCurrentPoints(blackPlayer.getPiece());
        int p2 = gameBoard.getCurrentPoints(whitePlayer.getPiece());

        blackPlayer.setPoints(p1);
        whitePlayer.setPoints(p2);
    }

    public void updateGameState() {
        int black = blackPlayer.getPoints();
        int white = whitePlayer.getPoints();

        if((black + white) == 64)
            state = (black > white) ? GameState.BLACK_WON : GameState.WHITE_WON;
        else if(black == 0)
            state = GameState.WHITE_WON;
        else if(white == 0)
            state = GameState.BLACK_WON;
        else //Useful for cases when game is over and user makes an undo
            state = GameState.RUNNING;
    }

    public void updateTurn() {
        ArrayList<MoveModel> possibleMoves = getValidMoves(getNonCurrentPlayer());

        if(possibleMoves.size() > 0)
            turn = (turn == TurnState.BLACK ? TurnState.WHITE : TurnState.BLACK);
    }

    //Returns piece at given position, considering both gameBoard's matrices
    public char getPieceAt(int x, int y) {
        char temp = gameBoard.getPieceAt(x,y);

        if(temp == GameModel.EMPTY_PIECE)
            return gameBoard.getSuggestionAt(x,y);

        return temp;
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

    public BoardModel getGameBoard() {
        return gameBoard;
    }

    public PlayerModel getCurrentPlayer() {
        if(turn == TurnState.BLACK)
            return blackPlayer;

        return whitePlayer;
    }

    public PlayerModel getNonCurrentPlayer() {
        if(turn == TurnState.BLACK)
            return whitePlayer;

        return blackPlayer;
    }

    public ArrayList<MoveModel> getMovesList() {
        return (ArrayList<MoveModel>) movesList.clone();
    }

    public int getBlackPoints() {
        return blackPlayer.getPoints();
    }

    public int getWhitePoints() {
        return whitePlayer.getPoints();
    }

    public boolean isOver() {
        return state != GameState.RUNNING;
    }

    public GameMemento saveState() throws CloneNotSupportedException {
        BoardModel temp = (BoardModel) gameBoard.clone();
        return new GameMemento(temp, turn);
    }

    public void setPreviousState(GameMemento state) throws CloneNotSupportedException {
        gameBoard = (BoardModel) state.getBoard().clone();
        turn = state.getTurn();
        currentMoves = getValidMoves(getCurrentPlayer());
        updatePoints();
        updateGameState();
    }

    public boolean undoMove() throws CloneNotSupportedException {
        if(caretaker.getSize() == 0)
            return false;

        caretaker.removeLast();

        if(caretaker.getSize() == 0)
            return false;

        setPreviousState(caretaker.getLast());
        return true;
    }
}
