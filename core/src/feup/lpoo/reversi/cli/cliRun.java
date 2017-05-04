package feup.lpoo.reversi.cli;

import java.util.ArrayList;

import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.model.MoveModel;

/**
 * Created by antonioalmeida on 22/04/2017.
 */

public class cliRun {
    public GameModel game;

    public cliRun() {
        game = game.getInstance();

        ArrayList<MoveModel> cenas = game.getValidMoves(game.getCurrentPlayer());

        System.out.println(cenas.size());
        System.out.println(game.getCurrentBoard());

        for(MoveModel elem : cenas) {
            System.out.println("X : " + elem.getX() + "Y : " + elem.getY());
        }

        ArrayList<Integer[]> temp = cenas.get(0).getChangedPositions();

        for(Integer[] elem : temp) {
            System.out.println("Changed X:" + elem[0] + " Y:" + elem[1]);
        }

        game.getCurrentPlayer().setMove(cenas.get(0));
        game.updateGame();
        System.out.println(game.getCurrentBoard());
    }

    public static void main(String[] args) {
        cliRun session = new cliRun();
    }

}
