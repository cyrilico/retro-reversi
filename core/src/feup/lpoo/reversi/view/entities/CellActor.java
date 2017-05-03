package feup.lpoo.reversi.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by antonioalmeida on 02/05/2017.
 */

public class CellActor extends Actor {
    private static final Texture black = new Texture(Gdx.files.internal("black.png"));
    private static final Texture white = new Texture(Gdx.files.internal("white.png"));

    private final Texture color;

    int actorX;
    int actorY;

    public CellActor(int color, int x, int y) {
        if(color == 0)
            this.color = white;
        else
            this.color = black;

        actorX = x;
        actorY = y;
        setBounds(actorX, actorY, black.getWidth(), black.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(color,actorX,actorY);
    }
}
