package feup.lpoo.reversi.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.HashMap;
import java.util.Map;

import feup.lpoo.reversi.presenter.CellPresenter;

/**
 * Created by antonioalmeida on 02/05/2017.
 */

public class CellView extends Actor {
    private static final Texture tile = new Texture(Gdx.files.internal("tile.png"));

    private static final Texture black = new Texture(Gdx.files.internal("black.png"));
    private static final Texture white = new Texture(Gdx.files.internal("white.png"));
    private static final Texture suggestion = new Texture(Gdx.files.internal("hint.png"));

    private static Map<Character, Texture> ICONS = new HashMap<Character, Texture>();

    private CellPresenter presenter;

    static {
        ICONS.put('B', black);
        ICONS.put('W', white);
        ICONS.put('X', suggestion);
        ICONS.put('-', null);
    }

    private Texture getIcon(char piece) {
        Texture result = ICONS.get(piece);
        return result;
    }

    private Texture icon;

    //The coordinates relative to the cell's parent
    private int actorX;
    private int actorY;

    public CellView(int x, int y, int boardX, int boardY) {
        actorX = x;
        actorY = y;
        setBounds(actorX, actorY, tile.getWidth(), tile.getHeight());

        presenter = new CellPresenter(boardX, boardY);
        update();
        addListeners();
    }

    public void addListeners() {
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                try {
                    presenter.handleInput();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }

    public void update() {
        char currentPiece = presenter.getCurrentPiece();
        icon = getIcon(currentPiece);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(tile,actorX,actorY);

        if(icon != null)
            batch.draw(icon, actorX, actorY);
    }
}
