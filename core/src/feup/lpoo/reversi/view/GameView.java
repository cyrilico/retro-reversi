package feup.lpoo.reversi.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import feup.lpoo.reversi.Reversi;
import feup.lpoo.reversi.presenter.AIMoveStrategy;
import feup.lpoo.reversi.presenter.GamePresenter;
import feup.lpoo.reversi.view.entities.BoardView;

/**
 * Created by antonioalmeida on 02/05/2017.
 */

public class GameView extends ScreenAdapter {
    private Reversi game;
    private GamePresenter presenter;

    private GameStage stage;

    public GameView(Reversi game, int type, AIMoveStrategy strategyChosen) {
        presenter = new GamePresenter(type, strategyChosen);
        this.game = game;
        stage = new GameStage(game, presenter);
    }

    @Override
    public void render(float delta) {
        update(delta);

        // Clear the screen
        Gdx.gl.glClearColor(Reversi.BACKGROUND_COLOR.r, Reversi.BACKGROUND_COLOR.g, Reversi.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        stage.draw();
    }

    public void update(float dt) {
        presenter.updateGame();
        stage.act(dt);
    }
}
