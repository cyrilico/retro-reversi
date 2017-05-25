package feup.lpoo.reversi.presenter.ai;

import feup.lpoo.reversi.model.MoveModel;

/**
 * Created by antonioalmeida on 18/05/2017.
 */

/**
 * Class for presenting the move and the score together
 */
public class MoveScore implements Comparable<MoveScore>{
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
