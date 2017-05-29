package feup.lpoo.reversi.model;

import java.util.ArrayList;

public class GameCareTaker {
    private ArrayList<GameMemento> mementoList = new ArrayList<GameMemento>();

    public void add(GameMemento state) {
        mementoList.add(state);
    }

    public GameMemento getLast() {
        return mementoList.get(mementoList.size()-1);
    }

    public void removeLast() {
        mementoList.remove(mementoList.size()-1);
    }

    public int getSize() {
        return mementoList.size();
    }
}
