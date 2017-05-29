package feup.lpoo.reversi.model;


import java.io.Serializable;
import java.util.ArrayList;

public class BoardModel implements Cloneable, Serializable {
    private char[][] board;
    private char[][] suggestions;

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

    public BoardModel() {
        board = generateMatrix(GameModel.BOARD_SIZE, GameModel.BOARD_SIZE);
        suggestions = generateSuggestions(GameModel.BOARD_SIZE, GameModel.BOARD_SIZE);
    }

    public void setPieceAt(int x, int y, char piece) {
        if(x < GameModel.BOARD_SIZE && y < GameModel.BOARD_SIZE)
            board[y][x] = piece;
    }

    public boolean validPosition(int x, int y) {
        if(x < GameModel.BOARD_SIZE && y < GameModel.BOARD_SIZE && x >= 0 && y >= 0)
            return true;

        return false;
    }

    public char getPieceAt(int x, int y) {
        if(validPosition(x,y))
            return board[y][x];

        return GameModel.EMPTY_PIECE;
    }

    public char getSuggestionAt(int x, int y) {
        if (validPosition(x, y))
            return suggestions[y][x];

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

    public ArrayList<MoveModel> getValidMoves(char piece) {
        ArrayList<MoveModel> result = new ArrayList<MoveModel>();

        for (int y = 0; y < GameModel.BOARD_SIZE; y++) {
            for (int x = 0; x < GameModel.BOARD_SIZE; x++) {
                MoveModel temp = getValidMove(x, y, piece);

                if (temp != null)
                    result.add(temp);
            }
        }

        return result;
    }

    public MoveModel getValidMove(int x, int y, char piece) {

        if(getPieceAt(x,y) != GameModel.EMPTY_PIECE)
            return null;

        MoveModel result = new MoveModel(x,y,piece);

        boolean isValidMove = false;
        for(int i = 0; i < directions.length; i++)
            if(checkValidDirection(result, directions[i]))
                isValidMove = true;

        if(isValidMove)
            return result;

        return null;
    }

    public boolean checkValidDirection(MoveModel move, int[] direction) {
        char piece = move.getPiece();
        int x = move.getX();
        int y = move.getY();

        char oppositePiece = (piece == GameModel.BLACK_PIECE) ? GameModel.WHITE_PIECE : GameModel.BLACK_PIECE;

        boolean hasOppPieceBetween = false;
        boolean validDirection =  false;

        char currentPiece = piece;

        ArrayList<Integer[]> changedPositions = new ArrayList<Integer[]>();

        int index = 1;
        while(currentPiece != GameModel.EMPTY_PIECE) {
            int currentX = x + index * direction[0];
            int currentY = y + index * direction[1];

            currentPiece = getPieceAt(currentX, currentY);

            if(currentPiece == piece && hasOppPieceBetween) {
                validDirection = true;
                move.addChangedPositions(changedPositions);
                break;
            }

            Integer[] currentPos = {currentX, currentY};
            changedPositions.add(currentPos);

            if(currentPiece == oppositePiece)
                hasOppPieceBetween = true;
            else break; //If currentPiece == empty or == piece, but hasOppPiece == false

            index++;
        }

        return validDirection;
    }

    public int getCurrentPoints(char piece) {
        int result = 0;

        for(char[] line : board) {
            for(char elem : line)
                if(elem == piece)
                    result++;
        }

        return result;
    }

    public void setSuggestions(ArrayList<MoveModel> moves) {
        suggestions = generateSuggestions(GameModel.BOARD_SIZE, GameModel.BOARD_SIZE);
        for(MoveModel elem : moves)
            suggestions[elem.getY()][elem.getX()] = GameModel.SUGGESTION_PIECE;
    }

    public char[][] getCurrentBoard() {
        char[][] temp = new char[GameModel.BOARD_SIZE][GameModel.BOARD_SIZE];

        int index = 0;

        for(char[] line : board)
            temp[index++] = (char[])line.clone();

        return temp;
    }

    public char[][] getCurrentSuggestions() {
        char[][] temp = new char[GameModel.BOARD_SIZE][GameModel.BOARD_SIZE];

        int index = 0;

        for(char[] line : suggestions)
            temp[index++] = (char[])line.clone();

        return temp;
    }

    private char[][] generateMatrix(int width, int height) {
        char[][] temp = new char[height][width];

        for (char[] line : temp) {
            for (int i = 0; i < line.length; i++) {
                line[i] = GameModel.EMPTY_PIECE;
            }
        }

        temp[3][3] = GameModel.WHITE_PIECE;
        temp[3][4] = GameModel.BLACK_PIECE;
        temp[4][3] = GameModel.BLACK_PIECE;
        temp[4][4] = GameModel.WHITE_PIECE;

        return temp;
    }

    private char[][] generateSuggestions(int width, int height) {
        char[][] temp = new char[height][width];

        for (char[] line : temp) {
            for (int i = 0; i < line.length; i++) {
                line[i] = GameModel.EMPTY_PIECE;
            }
        }

        return temp;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BoardModel temp = (BoardModel) super.clone();

        temp.setBoard(getCurrentBoard());
        temp.setSuggestions(getCurrentSuggestions());

        return temp;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public void setSuggestions(char[][] suggestions) {
        this.suggestions = suggestions;
    }
}

