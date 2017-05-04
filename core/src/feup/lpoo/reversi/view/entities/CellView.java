package feup.lpoo.reversi.view.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.view.GameView;

/**
 * Created by antonioalmeida on 02/05/2017.
 */

public class CellView extends Actor {
    private static final Texture black = new Texture(Gdx.files.internal("black.png"));
    private static final Texture white = new Texture(Gdx.files.internal("white.png"));

    private final Texture color;

    //The coordinates relative to it's parent that the cell is drawn
    private int actorX;
    private int actorY;

    //The position it represents on the board
    private int boardX;
    private int boardY;

    public CellView(int color, int x, int y, int boardX, int boardY) {
        if(color == 0)
            this.color = white;
        else
            this.color = black;

        actorX = x;
        actorY = y;
        setBounds(actorX, actorY, black.getWidth(), black.getHeight());

        this.boardX = boardX;
        this.boardY = boardY;
        addListeners();
    }

    public void addListeners() {
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int index = GameModel.getInstance().isValidMove(boardX, boardY);
                if(index != -1) {
                    GameModel.getInstance().getCurrentPlayer().setMoveIndex(index);
                    GameModel.getInstance().updateGame();
                }
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(color,actorX,actorY);
    }
}
