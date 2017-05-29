package feup.lpoo.reversi.view;

import com.badlogic.gdx.Gdx;
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

public class GameStage extends Stage {
    private Reversi game;
    private GamePresenter presenter;

    private Table hud;
    private Table paddleTable;
    private Table boardTable;
    private Table undoTable;
    private Table gameOverTable;
    private Table winnerTable;

    private Image blackIcon;
    private Image whiteIcon;
    private Image paddle1;
    private Image paddle2;

    private Label score1;
    private Label score2;

    private BoardView board;

    private TextButton undo;

    private TextButton restart;
    private TextButton back;

    private Label winner;

    private boolean gameOver; //To ensure that gameOver() only runs once per game

    public GameStage(Reversi game, GamePresenter presenter) {
        super(game.getViewport(), game.getBatch());
        gameOver = false;
        this.game = game;
        this.presenter = presenter;
        initElements();
        initTables();
        addToStage();
        addListeners();
    }

    private void initElements() {
        blackIcon = new Image(game.getAssetManager().get("black.png", Texture.class));
        whiteIcon = new Image(game.getAssetManager().get("white.png", Texture.class));
        paddle1 = new Image(game.getAssetManager().get("paddle.png", Texture.class));
        paddle2 = new Image(game.getAssetManager().get("paddle.png", Texture.class));
        score1 = new Label("02", game.getSkin());
        score2 = new Label("02", game.getSkin());
        board = new BoardView(presenter);
        undo = new TextButton("Undo", game.getSkin());
        restart = new TextButton(" Restart ", game.getSkin());
        back = new TextButton(" Main Menu", game.getSkin());
        winner = new Label("", game.getSkin());
    }

    private void initTables() {
        paddleTable = new Table(); addPaddles();
        hud = new Table(); addHud();
        boardTable = new Table(); addBoard();
        undoTable = new Table(); addUndo();
        gameOverTable = new Table();
        winnerTable = new Table(); addGameOverHud();
    }

    private void addPaddles() {
        paddleTable.setFillParent(true);
        paddleTable.top();
        paddleTable.add(paddle1).expandX().padTop(70);
        paddleTable.add(paddle2).expandX().padTop(70);
    }

    private void addHud() {
        hud.setFillParent(true);
        hud.top();
        hud.add(blackIcon).expandX().padTop(75).align(Align.right).padRight(5);
        hud.add(score1).expandX().padTop(75).align(Align.left).padLeft(5);
        hud.add(whiteIcon).expandX().padTop(75).align(Align.right).padRight(5);
        hud.add(score2).expandX().padTop(75).align(Align.left).padLeft(5);
        hud.row();
    }

    private void addBoard() {
        boardTable.setFillParent(true);
        boardTable.add(board).center().expandY();
    }

    private void addUndo() {
        undoTable.setFillParent(true);
        undoTable.bottom().add(undo).expandX().padBottom(50);
    }

    private void addGameOverHud() {
        gameOverTable.setVisible(false);
        gameOverTable.setFillParent(true);
        gameOverTable.bottom();
        gameOverTable.add(restart).expandX().padBottom(50);
        gameOverTable.add(back).expandX().padBottom(50);

        winnerTable.setFillParent(true);
        winnerTable.setVisible(false);
        winnerTable.bottom().add(winner).center().padBottom(130);
    }

    private void addToStage() {
        addActor(paddleTable);
        addActor(hud);
        addActor(boardTable);
        addActor(undoTable);
        addActor(gameOverTable);
        addActor(winnerTable);
    }

    private void gameOver() {
        if(!gameOver) {
            presenter.updateAchievements();
            undo.setVisible(false);
            gameOverTable.setVisible(true);
            winnerTable.setVisible(true);
            showResult();
        }
    }

    private void restartGame() {
        gameOver = false;
        presenter.restartGame();
        undo.setVisible(true);
        gameOverTable.setVisible(false);
        winnerTable.setVisible(false);
    }

    private void showResult() {
        String result = presenter.getResult();
        winner.setText(result);
    }

    @Override
    public void act(float dt) {
        presenter.updateGame();
        updateScore();
        updateTurn();
        board.act(dt);
        if(presenter.gameOver()) {
            gameOver();
            gameOver = true;
        }
    }

    private void updateScore() {
        score1.setText(presenter.getBlackPoints());
        score2.setText(presenter.getWhitePoints());
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

        restart.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                restartGame();
                return true;
            }
        });

        back.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuView(game));
                return true;
            }
        });
        Gdx.input.setInputProcessor(this);
    }
}
