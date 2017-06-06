package feup.lpoo.reversi.view;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import feup.lpoo.reversi.Reversi;
import feup.lpoo.reversi.presenter.GamePresenter;

public class OnlineGameStage extends GameStage {
    private TextButton submit;

    public OnlineGameStage(Reversi game, GamePresenter presenter) {
        super(game, presenter);
    }

    @Override
    public void initElements() {
        super.initElements();
        submit = new TextButton("Submit", game.getSkin());
    }

    @Override
    public void initTables() {
        super.initTables();
        addUndo();
    }

    private void addUndo() {
        buttonTable.row();
        buttonTable.bottom().add(submit).expandX().padBottom(40);
    }

    @Override
    public void gameOver() {
        super.gameOver();
        if(!gameOver)
            submit.setVisible(false);
    }

    @Override
    public void restartGame() {
        super.restartGame();
        submit.setVisible(true);
    }

    @Override
    public void addListeners() {
        super.addListeners();
        submit.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                presenter.screenAction();
                return true;
            }
        });
    }
}
