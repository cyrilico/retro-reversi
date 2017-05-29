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

    class MoveScore implements Comparable<MoveScore>{
        private MoveModel move;
        private int score;

        public MoveScore(MoveModel move, int score){
            this.move = move;
            this.score = score;
        }

        public int getScore(){
            return score;
        }

        public MoveModel getMove(){
            return move;
        }

        @Override
        public int compareTo(MoveScore o) {
            if(o.score > this.score)
                return 1;
            else if (o.score < this.score)
                return -1;
            else
                return 0;
        }
    }

    private MoveScore negamax(BoardModel board, int depth, char piece){
        char oppPiece = (piece == 'B' ? 'W' : 'B');
        ArrayList<MoveModel> currentValidModes = board.getValidMoves(piece);

        if (depth == 0)
            return new MoveScore(null, evaluateBoard(board.getCurrentBoard(), piece));

        int currentScore;
        int bestScore = Integer.MIN_VALUE;
        MoveModel bestMove;

        if (currentValidModes.isEmpty())
            return new MoveScore(null, bestScore);
        bestMove = currentValidModes.get(0);

        for(MoveModel move : currentValidModes){
            BoardModel newBoard = null;
            try {
                newBoard = (BoardModel) board.clone();
            }
            catch(CloneNotSupportedException e) {
                System.out.println("RIP Board cloning");
                System.exit(-1);
            }

            //Recursive call
            newBoard.setPieceAt(move.getX(), move.getY(), move.getPiece());
            MoveScore current = negamax(newBoard, depth - 1, oppPiece);

            //Negamax principle: max(a,b) = -min(-a,-b)
            currentScore = -current.getScore();

            // Update bestScore
            if (currentScore>bestScore){
                    bestScore = currentScore;
                    bestMove = move;
            }
        }
        return new MoveScore(bestMove,bestScore);
    }
}