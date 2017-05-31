package feup.lpoo.reversi.presenter.ai;

import java.util.ArrayList;

import feup.lpoo.reversi.model.BoardModel;
import feup.lpoo.reversi.model.MoveModel;

public class HardMoveStrategy implements CalculatedAIMoveStrategy {
    private int maxDepth;

    public HardMoveStrategy() { //TODO: Add depth as constructor argument? (Allows for an extra difficulty with (much) more move depth analysis)
        maxDepth = 3;
    }

    @Override
    public MoveModel findMove(BoardModel board, char piece) {
        return negamax(board, maxDepth, piece).getMove();
    }

    public int evaluateBoard(char[][] board, char piece) {
        int score = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] == piece)
                    score += BOARD_VALUE[i][j];
                else if (board[i][j] != '-') //TODO: Replace macro
                    score -= BOARD_VALUE[i][j];
            }
        }
        return score;
    }

    class ScoredMove{
        private MoveModel move;
        private int score;

        public ScoredMove(MoveModel move, int score){
            this.move = move;
            this.score = score;
        }

        public int getScore(){
            return score;
        }

        public MoveModel getMove(){
            return move;
        }
    }

    private ScoredMove negamax(BoardModel board, int depth, char piece){
        char oppPiece = (piece == 'B' ? 'W' : 'B');
        ArrayList<MoveModel> currentValidModes = board.getValidMoves(piece);

        if (depth == 0)
            return new ScoredMove(null, evaluateBoard(board.getCurrentBoard(), piece));

        int currentScore;
        int bestScore = Integer.MIN_VALUE;
        MoveModel bestMove = null;

        if (currentValidModes.isEmpty())
            return new ScoredMove(null, bestScore);

        for(MoveModel move : currentValidModes){
            BoardModel newBoard = null;
            try {
                newBoard = (BoardModel) board.clone();
            }
            catch(CloneNotSupportedException e) {
                System.out.println("RIP Board cloning");
                return null;
            }

            //Recursive call
            newBoard.setPieceAt(move.getX(), move.getY(), move.getPiece());
            ScoredMove current = negamax(newBoard, depth - 1, oppPiece);

            //Negamax principle: max(a,b) = -min(-a,-b)
            currentScore = -current.getScore();

            // Update bestScore
            if (currentScore>bestScore){
                    bestScore = currentScore;
                    bestMove = move;
            }
        }
        return new ScoredMove(bestMove,bestScore);
    }
}