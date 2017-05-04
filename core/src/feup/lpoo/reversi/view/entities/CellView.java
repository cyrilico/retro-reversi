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

    private static final Texture angry = new Texture(Gdx.files.internal("angry.png"));
    private static final Texture blush = new Texture(Gdx.files.internal("blush.png"));

    private final Texture color;
    private Texture piece;

    //The coordinates relative to it's parent that the cell is drawn
    private int actorX;
    private int actorY;

    //The position it represents on the board
    private int boardX;
    private int boardY;

    //Variables to handle multiple cells piece updating
    private static int nMovesTotal = 0;
    private int nMovesCell = 0;

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
        updatePiece();
        addListeners();
    }

    public void addListeners() {
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //This part needs to be thoroughly rethinked

                int index = GameModel.getInstance().isValidMove(boardX, boardY);
                if(index != -1) {
                    GameModel.getInstance().getCurrentPlayer().setMoveIndex(index);
                    GameModel.getInstance().updateGame();
                    nMovesTotal++; //All cells get notified a change was made
                }
                return true;
            }
        });
    }

    public void updatePiece() {
        char currentPiece = GameModel.getInstance().getGameBoard().getPieceAt(boardX,boardY);

        if(currentPiece == 'B') //Replace with enums later (make getMethods for BoardModel or make enums public?
            piece = angry;
        else if(currentPiece == 'W')
            piece = blush;
        else
            piece = null;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(color,actorX,actorY);

        if(nMovesCell != nMovesTotal) {
            updatePiece();
            nMovesCell = nMovesTotal;
        }

        if(piece != null)
            batch.draw(piece,actorX,actorY);
    }
}
