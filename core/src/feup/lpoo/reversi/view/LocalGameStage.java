package feup.lpoo.reversi.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import feup.lpoo.reversi.Reversi;
import feup.lpoo.reversi.presenter.GamePresenter;
import feup.lpoo.reversi.view.entities.BoardView;

/**
 * Created by antonioalmeida on 29/05/2017.
 */

public class LocalGameStage extends GameStage {

    private TextButton undo;

    public LocalGameStage(Reversi game, GamePresenter presenter) {
        super(game, presenter);
    }

    @Override
    public void initElements() {
        super.initElements();
        undo = new TextButton("Undo", game.getSkin());
    }


    @Override
    public void initTables() {
        super.initTables();
        addUndo();
    }

    private void addUndo() {
        buttonTable.row();
        buttonTable.bottom().add(undo).expandX().padBottom(20);
    }

    @Override
    public void gameOver() {
        super.gameOver();
        if(!gameOver) {
            undo.setVisible(false);
            back.setVisible(false);
        }
    }

    @Override
    public void restartGame() {
        super.restartGame();
        undo.setVisible(true);
    }

    @Override
    public void addListeners() {
        super.addListeners();

        undo.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                presenter.undoMove();
                return true;
            }
        });


    }
}
