package feup.lpoo.reversi.view;

import feup.lpoo.reversi.presenter.ai.AIMoveStrategy;

public class GameInfo {
    private boolean isSinglePlayer;
    private boolean isBlack;
    private AIMoveStrategy strategy;

    public GameInfo(boolean singlePlayer) {
        this.isSinglePlayer = singlePlayer;
    }

    public GameInfo(boolean singlePlayer, boolean isBlack, AIMoveStrategy strategy) {
        this(singlePlayer);
        this.isBlack = isBlack;
        this.strategy = strategy;
    }

    public boolean isSinglePlayer() {
        return isSinglePlayer;
    }

    public boolean userIsBlack() {
        return isBlack;
    }

    public AIMoveStrategy getStrategy() {
        return strategy;
    }
}
