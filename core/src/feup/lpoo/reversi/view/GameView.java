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
import feup.lpoo.reversi.presenter.GamePresenter;
import feup.lpoo.reversi.view.entities.BoardView;

/**
 * Created by antonioalmeida on 02/05/2017.
 */

public class GameView extends ScreenAdapter {
    private Reversi game;
    private GamePresenter presenter;

    private Stage stage;

    private Table hud;
    private Table paddleTable;
    private Table boardTable;
    private Table undoTable;

    private Image blackIcon;
    private Image whiteIcon;
    private Image paddle1;
    private Image paddle2;

    private Label score1;
    private Label score2;

    private BoardView board;

    private TextButton undo;

    public GameView(Reversi game, int type) {
        presenter = new GamePresenter(type);
        this.game = game;
        stage = new Stage(game.getViewport(), game.getBatch());

        addPaddles();
        addIcons();
        addLabels();
        addBoard();
        addUndo();
        addListeners();
    }

    @Override
    public void render(float delta) {
        update(delta);

        // Clear the screen
        Gdx.gl.glClearColor(Reversi.BACKGROUND_COLOR.r, Reversi.BACKGROUND_COLOR.g, Reversi.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        stage.draw();
    }

    private void addIcons() {
        blackIcon = new Image(game.getAssetManager().get("black.png", Texture.class));
        whiteIcon = new Image(game.getAssetManager().get("white.png", Texture.class));
    }

    private void addPaddles() {
        paddle1 = new Image(game.getAssetManager().get("paddle.png", Texture.class));
        paddle2 = new Image(game.getAssetManager().get("paddle.png", Texture.class));

        paddleTable = new Table();
        paddleTable.setFillParent(true);
        paddleTable.top();
        paddleTable.debugAll();
        stage.addActor(paddleTable);
        paddleTable.add(paddle1).expandX().padTop(75);
        paddleTable.add(paddle2).expandX().padTop(75);
    }

    private void addLabels() {
        score1 = new Label("02", game.getSkin());
        score2 = new Label("02", game.getSkin());

        hud = new Table();
        hud.debugAll();
        stage.addActor(hud);

        hud.setFillParent(true);
        hud.top();
        hud.add(blackIcon).expandX().padTop(75).align(Align.right).padRight(5);
        hud.add(score1).expandX().padTop(75).align(Align.left).padLeft(5);
        hud.add(whiteIcon).expandX().padTop(75).align(Align.right).padRight(5);
        hud.add(score2).expandX().padTop(75).align(Align.left).padLeft(5);
        hud.row();
    }

    private void addBoard() {
        boardTable = new Table();
        boardTable.setFillParent(true);
        stage.addActor(boardTable);
        board = new BoardView(presenter);
        boardTable.add(board).center().expandY();
    }

    private void addUndo() {
        undoTable = new Table();
        undo = new TextButton("Undo", game.getSkin());
        undoTable.setFillParent(true);
        undoTable.bottom().add(undo).expandX().padBottom(50);
        stage.addActor(undoTable);
    }

    public void update(float dt) {
        presenter.updateGame();
        board.act(dt);
        updateScore();
        updateTurn();
    }

    private void updateScore() {
        score1.setText(presenter.getPlayer1Points());
        score2.setText(presenter.getPlayer2Points());
    }

    private void updateTurn() {
        paddle1.setVisible(presenter.isBlackTurn());
        paddle2.setVisible(presenter.isWhiteTurn());
    }

    private void addListeners() {
        undo.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                presenter.undoMove();
                return true;
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

}
