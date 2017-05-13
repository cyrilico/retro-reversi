package feup.lpoo.reversi.view.entities;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by antonioalmeida on 02/05/2017.
 */

public class BoardView extends Group {

    public BoardView() {

        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                Actor cell = new CellView(64 * x - 256, 64 * y - 256, x, 7 - y) ;
                addActor(cell);
            }
        }

    }

    @Override
    public void act(float dt) {
        for(Actor elem : this.getChildren()) {
            ((CellView) elem).update();
        }
    }
}
