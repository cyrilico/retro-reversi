package feup.lpoo.reversi.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import feup.lpoo.reversi.Reversi;
import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.model.MoveModel;
import feup.lpoo.reversi.view.entities.BoardView;
import feup.lpoo.reversi.view.entities.CellView;

/**
 * Created by antonioalmeida on 02/05/2017.
 */

public class GameView extends ScreenAdapter {
    private Reversi game;
    private GameModel gameInstance;

    private Stage stage;
    private Table table;

    private Label mainTitle;
    private Group board;

    public GameView(Reversi game) {
        this.game = game;
        gameInstance = gameInstance.getInstance();
        stage = new Stage(game.getViewport(), game.getBatch());

        addTitle();
        addBoard();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(Reversi.BACKGROUND_COLOR.r, Reversi.BACKGROUND_COLOR.g, Reversi.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        stage.draw();
    }

    private void addTitle() {
        mainTitle = new Label("Game View", game.getSkin());
        mainTitle.setFontScale(2);

        table = new Table();
        stage.addActor(table);

        table.setFillParent(true);
        table.top();
        table.add(mainTitle).expandX().padTop(50);
        table.row();
    }

    private void addBoard() {
        board = new BoardView();
        table.add(board).center().padTop(576);
    }
}
