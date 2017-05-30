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
    private Table undoTable;
    private Table backButtonTable;
    private TextButton backButton;
    private Dialog exitDialog;

    public LocalGameStage(Reversi game, GamePresenter presenter) {
        super(game, presenter);
    }

    @Override
    public void initElements() {
        super.initElements();
        undo = new TextButton("Undo", game.getSkin());

        initExitDialog();

        backButton = new TextButton("\n BACK \n", game.getSkin());
        backButton.setColor(Reversi.SECONDARY_COLOR);
    }

    private void initExitDialog(){
        exitDialog = new Dialog("Exit game confirmation", game.getSkin()) {
            @Override
            protected void result(Object object) {
                if ((Boolean) object) {
                    game.setScreen(new MainMenuView(game));
                }else
                    hide(null); //Do NOT remove null argument
            }
        };
        //exitDialog.text("Are you sure you want to quit? You will lose current game progress");
        exitDialog.button("Yes", true).button("No", false);
    }

    @Override
    public void initTables() {
        super.initTables();
        undoTable = new Table(); addUndo();

        backButtonTable = new Table();
        backButtonTable.top();
        backButtonTable.setFillParent(true);

        addButtons();
    }

    private void addButtons(){
        backButtonTable.add(backButton).right();
    }

    private void addUndo() {
        undoTable.setFillParent(true);
        undoTable.bottom().add(undo).expandX().padBottom(50);
    }

    @Override
    public void addToStage() {
        super.addToStage();
        addActor(undoTable);
        addActor(backButtonTable);
    }

    @Override
    public void gameOver() {
        super.gameOver();
        if(!gameOver) {
            undo.setVisible(false);
        }
    }

    @Override
    public void restartGame() {
        super.restartGame();
        undo.setVisible(true);
    }

    private void showExitDialog(){
        exitDialog.show(this);
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

        backButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                showExitDialog();
                return true;
            }
        });
    }
}
