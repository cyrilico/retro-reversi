package feup.lpoo.reversi.view.entities;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by antonioalmeida on 02/05/2017.
 */

public class BoardGroup extends Group {

    public BoardGroup() {

        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                Actor cell = new CellActor((x +y) % 2, 64 * x - 256, 64 * y) ;
                addActor(cell);
            }
        }
    }
}
