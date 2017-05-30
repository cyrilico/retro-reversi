package feup.lpoo.reversi.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import feup.lpoo.reversi.Reversi;
import feup.lpoo.reversi.presenter.ai.EasyMoveStrategy;
import feup.lpoo.reversi.presenter.ai.HardMoveStrategy;
import feup.lpoo.reversi.presenter.ai.MediumMoveStrategy;

public class DifficultyMenuView extends ScreenAdapter {
    private Reversi game;

    private Stage stage;
    private Table buttonTable;
    private Table titleTable;
    private Table pieceTable;
    private Table backButtonTable;

    private TextButton randomAIButton;
    private TextButton immediateAIButton;
    private TextButton calculatedAIButton;
    private TextButton backButton;

    private CheckBox blackCheckBox;
    private CheckBox whiteCheckBox;

    private ButtonGroup<CheckBox> pieceChoiceGroup;

    //Labels
    private Label mainTitle;

    public DifficultyMenuView(Reversi game) {
        this.game = game;
        stage = new Stage(game.getViewport(), game.getBatch());

        addTitle();
        addPieceChoice();
        addChoiceButtons();
        addBackButton();
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

    private void addChoiceButtons() {
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

    private void addBackButton(){
        backButtonTable = new Table();
        backButtonTable.top();
        backButtonTable.setFillParent(true);

        backButton = new TextButton("\n BACK \n", game.getSkin());
        backButton.setTransform(true);
        backButton.setColor(Reversi.SECONDARY_COLOR);

        backButtonTable.add(backButton).right();

        stage.addActor(backButtonTable);
    }

    private void addPieceChoice(){
        pieceTable = new Table();
        pieceTable.top();
        pieceTable.setFillParent(true);

        pieceChoiceGroup = new ButtonGroup<CheckBox>();
        whiteCheckBox = new CheckBox("White", game.getSkin());
        whiteCheckBox.setTransform(true);
        whiteCheckBox.scaleBy(1);
        blackCheckBox = new CheckBox("Black", game.getSkin());
        blackCheckBox.setTransform(true);
        blackCheckBox.scaleBy(1);
        pieceChoiceGroup.add(whiteCheckBox);
        pieceChoiceGroup.add(blackCheckBox);
        pieceChoiceGroup.setMaxCheckCount(1);
        pieceChoiceGroup.setMinCheckCount(1);
        pieceChoiceGroup.setUncheckLast(true);
        pieceChoiceGroup.setChecked("Black");

        pieceTable.add(whiteCheckBox).center().padTop(325).row();
        pieceTable.add(blackCheckBox).center().padTop(50);

        stage.addActor(pieceTable);

    }

    private void addListeners() {
        randomAIButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameInfo info = new GameInfo(true, pieceChoiceGroup.getCheckedIndex() == 1, new EasyMoveStrategy());
                game.setScreen(new GameView(game, info));
                return true;
            }
        });

        immediateAIButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameInfo info = new GameInfo(true, pieceChoiceGroup.getCheckedIndex() == 1, new MediumMoveStrategy());
                game.setScreen(new GameView(game, info));
                return true;
            }
        });

        calculatedAIButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameInfo info = new GameInfo(true, pieceChoiceGroup.getCheckedIndex() == 1, new HardMoveStrategy());
                game.setScreen(new GameView(game, info));
                return true;
            }
        });

        backButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuView(game));
                return true;
            }
        });
    }
}
