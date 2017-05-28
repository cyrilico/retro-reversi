import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

import feup.lpoo.reversi.model.BoardModel;
import feup.lpoo.reversi.model.MoveModel;


/**
 * Created by cyrilico on 23-05-2017.
 */
public class ReversiTest {
    @Test
    public void assertCorrectBoardInitialization(){
        BoardModel board = new BoardModel();
        char[][] startingBoard = board.getCurrentBoard();
        char[][] expectedBoard = {
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','W','B','-','-','-'},
                {'-','-','-','B','W','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
        };

        for(int i = 0; i < 8; i++)
            assertArrayEquals(startingBoard[i], expectedBoard[i]);
    }

    @Test
    public void assertCorrectLegalMovesCalculation(){
        BoardModel board = new BoardModel();
        ArrayList<MoveModel> legalMoves;

        char[][] situation1 = {
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','W','B','-','-','-'},
                {'-','-','-','B','W','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
        };

        board.setBoard(situation1);

        legalMoves = board.getValidMoves('W');
        assertTrue(legalMoves.size() == 4);

        char[][] situation2 = {
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
                {'-','W','-','-','-','-','B','-'},
                {'-','-','W','B','B','B','-','-'},
                {'-','-','-','W','B','-','-','-'},
                {'-','-','-','B','W','W','-','-'},
                {'-','-','B','-','-','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
        };

        board.setBoard(situation2);
        legalMoves = board.getValidMoves('B');
        assertTrue(legalMoves.size() == 6);

        char[][] situation3 = {
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','W','-','-','-','-'},
                {'-','-','B','B','W','B','-','-'},
                {'-','-','-','B','W','W','-','-'},
                {'-','-','-','-','B','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
                {'-','-','-','-','-','-','-','-'},
        };

        board.setBoard(situation3);
        legalMoves = board.getValidMoves('W');
        assertTrue(legalMoves.size() == 11);

        //Actually test if the correct legal moves are being chosen
        ArrayList<MoveModel> actualMoves = new ArrayList<MoveModel>();
        actualMoves.add(new MoveModel(2, 2, 'W'));
        actualMoves.add(new MoveModel(5, 2, 'W'));
        actualMoves.add(new MoveModel(6, 2, 'W'));
        actualMoves.add(new MoveModel(1, 3, 'W'));
        actualMoves.add(new MoveModel(6, 3, 'W'));
        actualMoves.add(new MoveModel(1, 4, 'W'));
        actualMoves.add(new MoveModel(2, 4, 'W'));
        actualMoves.add(new MoveModel(2, 5, 'W'));
        actualMoves.add(new MoveModel(3, 5, 'W'));
        actualMoves.add(new MoveModel(3, 6, 'W'));
        actualMoves.add(new MoveModel(4, 6, 'W'));
        for(MoveModel move : actualMoves)
            assertTrue(legalMoves.indexOf(move) >= 0);
    }
}