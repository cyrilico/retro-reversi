package feup.lpoo.reversi.view.entities;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import feup.lpoo.reversi.presenter.GamePresenter;

public class BoardView extends Group {

    public BoardView(GamePresenter presenter) {

        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                Actor cell = new CellView(presenter, 64 * x - 256, 64 * y - 256, x, 7 - y) ;
                addActor(cell);
            }
        }

    }

}
