package feup.lpoo.reversi.model;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import feup.lpoo.reversi.presenter.ai.AIPresenter;

public class AIModel extends PlayerModel{
    private AIPresenter presenter;
    private boolean calculating;
    private final ScheduledExecutorService scheduler;

    public AIModel(char piece, AIPresenter presenter) {
        super(piece);
        this.presenter = presenter;
        calculating = false;
        scheduler = Executors.newScheduledThreadPool(1);
    }

    @Override
    public boolean isReady() {
        if(ready) {
            ready = false;
            return true;
        }

        if(!calculating) {
            calculating = true;
            scheduler.schedule(calculateMove, 500, TimeUnit.MILLISECONDS);
        }

        return false;
    }

    private Runnable calculateMove = new Runnable() {
        @Override
        public void run() {
            move = presenter.findMove(piece);
            calculating = false;
            ready = true;
        }
    };

}
