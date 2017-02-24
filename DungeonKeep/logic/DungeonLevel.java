package logic;

public class DungeonLevel extends Level {
    public DungeonLevel() {
        map = new DungeonLevel();

        hero = new Hero(1,1);

        guards = new Guard[1];
        guards[0] = new Rookie(8,1);

        ogres = null;
    }
}
