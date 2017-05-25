package feup.lpoo.reversi.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import feup.lpoo.reversi.Reversi;
import feup.lpoo.reversi.presenter.ai.CalculatedMoveStrategy;
import feup.lpoo.reversi.presenter.ai.ImmediateMoveStrategy;
import feup.lpoo.reversi.presenter.ai.RandomMoveStrategy;

/**
 * Created by antonioalmeida on 22/05/2017.
 */

public class DifficultyMenuView extends ScreenAdapter {
    private Reversi game;

    private Stage stage;
    private Table buttonTable;
    private Table titleTable;

    private TextButton randomAIButton;
    private TextButton immediateAIButton;
    private TextButton calculatedAIButton;

    //Labels
    private Label mainTitle;

    public DifficultyMenuView(Reversi game) {
        this.game = game;
        stage = new Stage(game.getViewport(), game.getBatch());

        addTitle();
        addButtons();
        addListeners();

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
        mainTitle = new Label("    Choose \nDifficulty", game.getSkin());
        mainTitle.setFontScale(2);

        titleTable = new Table();
        titleTable.setFillParent(true);
        titleTable.top();
        titleTable.add(mainTitle).center().padTop(120);

        stage.addActor(titleTable);
    }

    private void addButtons() {
        buttonTable = new Table();
        buttonTable.bottom();
        buttonTable.setFillParent(true);

        randomAIButton = new TextButton("\n  Easy  \n", game.getSkin());
        immediateAIButton = new TextButton("\n  Medium  \n", game.getSkin());
        calculatedAIButton = new TextButton("\n  Hard  \n", game.getSkin());

        buttonTable.add(randomAIButton).center().padBottom(40);
        buttonTable.row();
        buttonTable.add(immediateAIButton).center().padBottom(40);
        buttonTable.row();
        buttonTable.add(calculatedAIButton).center().padBottom(40);

        stage.addActor(buttonTable);
    }

    private void addListeners() {
        randomAIButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameInfo info = new GameInfo(true, true, new RandomMoveStrategy());
                game.setScreen(new GameView(game, info));
                return true;
            }
        });

        immediateAIButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameInfo info = new GameInfo(true, false, new ImmediateMoveStrategy());
                game.setScreen(new GameView(game, info));
                return true;
            }
        });

        calculatedAIButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameInfo info = new GameInfo(true, true, new CalculatedMoveStrategy());
                game.setScreen(new GameView(game, info));
                return true;
            }
        });
    }
}
