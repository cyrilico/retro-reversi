package feup.lpoo.reversi.view;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import feup.lpoo.reversi.Reversi;
import feup.lpoo.reversi.presenter.GamePresenter;

/**
 * Created by antonioalmeida on 29/05/2017.
 */

public class OnlineGameStage extends GameStage {
    private TextButton submit;
    private Table submitTable;

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
        submitTable = new Table(); addUndo();
    }

    private void addUndo() {
        submitTable.setFillParent(true);
        submitTable.bottom().add(submit).expandX().padBottom(50);
    }

    @Override
    public void addToStage() {
        super.addToStage();
        addActor(submitTable);
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
                presenter.undoMove();
                return true;
            }
        });
    }
}
