package feup.lpoo.reversi.model;

import feup.lpoo.reversi.presenter.ai.AIPresenter;

/**
 * Created by antonioalmeida on 16/05/2017.
 */

public class AIModel extends PlayerModel {
    private AIPresenter presenter;

    public AIModel(char piece, AIPresenter presenter) {
        super(piece);
        this.presenter = presenter;
    }

    @Override
    public boolean isReady() {
        move = presenter.findMove(piece);
        return true;
    }
}
