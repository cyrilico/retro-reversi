package feup.lpoo.reversi.presenter;

import feup.lpoo.reversi.Reversi;
import feup.lpoo.reversi.model.AIModel;
import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.model.UserModel;
import feup.lpoo.reversi.presenter.ai.AIMoveStrategy;
import feup.lpoo.reversi.presenter.ai.AIPresenter;

/**
 * Created by antonioalmeida on 25/05/2017.
 */

public class SinglePlayerGamePresenter extends GamePresenter {
    private AIPresenter AI;
    private AIMoveStrategy strategy;
    private boolean userIsBlack;

    public SinglePlayerGamePresenter(Reversi reversi, AIMoveStrategy strategy, boolean isBlack) {
        super(reversi);
        userIsBlack = isBlack;
        this.strategy = strategy;
        AI = new AIPresenter(strategy);
        initPlayers();
        game = new GameModel(blackPlayer, whitePlayer);
        AI.setGame(game);
    };

    @Override
    public void initPlayers() {
        if(userIsBlack) {
            blackPlayer = new UserModel('B');
            whitePlayer = new AIModel('W', AI); //Replace with macros
        }
        else {
            blackPlayer = new AIModel('B', AI);
            whitePlayer = new UserModel('W');
        }
    }

    @Override
    public void restartGame() {
        AI = new AIPresenter(strategy);
        initPlayers();
        game = new GameModel(blackPlayer, whitePlayer);
        AI.setGame(game);
    }

    private boolean userWon() {
        int black = game.getBlackPoints();
        int white = game.getWhitePoints();

        if(userIsBlack && black > white)
            return true;
        else if(!userIsBlack && white > black)
            return true;

        return false;
    }

    @Override
    public void updateAchievements() {
        boolean victory = userWon();
        reversi.getPlayServices().matchCompleted(victory);
    }


    @Override
    public void undoMove() {
        game.undoMove(2);
    }

}
