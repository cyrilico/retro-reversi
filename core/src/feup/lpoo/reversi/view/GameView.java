package feup.lpoo.reversi.view;

import com.badlogic.gdx.Game;
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
    private Table hud;
    private Table boardTable;

    private Label player1;
    private Label score1;
    private Label player2;
    private Label score2;

    private BoardView board;

    public GameView(Reversi game) {
        this.game = game;
        gameInstance = gameInstance.getInstance();
        stage = new Stage(game.getViewport(), game.getBatch());

        addLabels();
        addBoard();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        update(delta);

        // Clear the screen
        Gdx.gl.glClearColor(Reversi.BACKGROUND_COLOR.r, Reversi.BACKGROUND_COLOR.g, Reversi.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        stage.draw();
    }

    private void addLabels() {
        player1 = new Label("Angry", game.getSkin());
        player2 = new Label("Blush", game.getSkin());
        score1 = new Label(String.format("%02d", GameModel.getInstance().getPlayer1Points()), game.getSkin());
        score2 = new Label(String.format("%02d", GameModel.getInstance().getPlayer2Points()), game.getSkin());

        hud = new Table();
        hud.debugAll();
        stage.addActor(hud);

        hud.setFillParent(true);
        hud.top();
        hud.add(player1).expandX().padTop(50);
        hud.add(player2).expandX().padTop(50);
        hud.row();
        hud.add(score1).expandX().padTop(25);
        hud.add(score2).expandX().padTop(25);
    }

    private void addBoard() {
        boardTable = new Table();
        boardTable.setFillParent(true);
        stage.addActor(boardTable);
        board = new BoardView();

        boardTable.add(board).center().expandY();
    }

    public void update(float dt) {
        //TO DO: make Hud class add it to the stage, so we can simply call stage.act() on the render method instead of using this method
        board.act(dt);
        updateLabels();
    }

    private void updateLabels() {
        score1.setText(String.format("%02d", GameModel.getInstance().getPlayer1Points()));
        score2.setText(String.format("%02d", GameModel.getInstance().getPlayer2Points()));
    }
}
