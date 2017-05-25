package feup.lpoo.reversi.presenter.ai;

import java.util.ArrayList;

import feup.lpoo.reversi.model.BoardModel;
import feup.lpoo.reversi.model.MoveModel;

/**
 * Created by cyrilico on 23-05-2017.
 */

public class CalculatedMoveStrategy implements feup.lpoo.reversi.presenter.ai.AIMoveStrategy {
    private int maxDepth;

    public CalculatedMoveStrategy() { //TODO: Add depth as constructor argument? (Allows for an extra difficulty with (much) more move depth analysis)
        maxDepth = 3;
    }

    @Override
    public MoveModel findMove(BoardModel board, char piece) {
        return negamax(board, maxDepth, piece).getMove();
    }

    private MoveScore negamax(BoardModel board, int depth, char piece){
        char oppPiece = (piece == 'B' ? 'W' : 'B');
        ArrayList<MoveModel> currentValidModes = board.getValidMoves(piece);

        if (depth == 0)
            return new MoveScore(null, feup.lpoo.reversi.presenter.ai.BoardEvaluation.evaluateBoard(board.getCurrentBoard(), piece));

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