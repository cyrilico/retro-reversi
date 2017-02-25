package logic;

public class KeepLevel extends Level {

    /* The villains for the level */
    Ogre ogre;

    public KeepLevel() {

        super();

        map = new KeepMap();

        /*Create level's characters*/
        //The hero
        hero = new Hero(1,7);
        //The ogres
        ogre = new Ogre(4,1);
    }

    public void checkIfHeroCaptured(){
      int[] heroCoordinates = hero.getCoordinates();
       if(ogre.hasCaughtHero(heroCoordinates[0],heroCoordinates[1]))
        levelStatus = LevelState.LOST;
    }

    public void updatePositions(int[] input) {
        //Update the hero's position
        int dx = input[0];
        int dy = input[1];

        int heroX = hero.getCoordinates()[0];
        int heroY = hero.getCoordinates()[1];

        char currentChar = map.elementAt(heroX+dx, heroY+dy);

        switch(currentChar) { //Checking what is present in the cell the hero wants to move to
            case 'S':
              levelStatus = LevelState.WON;
              return; /* Avoids checking for enemies on negative indexes */
            case 'k':
              heroHasKey = true;
              hero.setRepresentation('K');
            case '.':
              break;
            case 'I':
              if(heroHasKey)
                map.openDoors(); /* No break statement because while he's opening the door, he's still. No break forces dx=dy=0, like we want */
            default: /* currentChar == 'X' so we can't move through */
                dy = 0;
                dx = 0;
        }

        hero.setCoordinates(heroX+dx, heroY+dy);

        //Update the villains' position
        //Update the ogre's position
        int ogreX = ogre.getCoordinates()[0];
        int ogreY = ogre.getCoordinates()[1];
        int[] nextMovement;
        generateOgreMovement:
        do{
          nextMovement = ogre.getNextMovement();
          currentChar = map.elementAt(ogreX+nextMovement[0],ogreY+nextMovement[1]);
          switch(currentChar) {
            case 'k':
              ogre.setRepresentation('$');
              break generateOgreMovement;
            case '.':
              ogre.setRepresentation('0');
              break generateOgreMovement;
            default:
              break;
          }
        }while(true); //Risky...

        ogre.setCoordinates(ogreX+nextMovement[0],ogreY+nextMovement[1]);
        //Re-get coordinates so club isn't looking for a new position based on old ones
        ogreX = ogre.getCoordinates()[0];
        ogreY = ogre.getCoordinates()[1];

        //Update the ogre's club's position
        generateClubMovement:
        do{
          nextMovement = ogre.getNextMovement();
          currentChar = map.elementAt(ogreX+nextMovement[0],ogreY+nextMovement[1]);
          switch(currentChar) {
            case 'k':
              ogre.putClubOnKey();
              break generateClubMovement;
            case '.':
              ogre.removeClubFromKey();
              break generateClubMovement;
            default:
              break;
          }
        }while(true); //Risky...

        ogre.setClubOffset(nextMovement[0],nextMovement[1]);

        checkIfHeroCaptured();
    }

    public char[][] getLevelMatrix() {
        char[][] matrix = map.getCurrentPlan();

        //Draw hero
        int[] heroCoordinates = hero.getCoordinates();
        int heroX = heroCoordinates[0];
        int heroY = heroCoordinates[1];
        matrix[heroY][heroX] = hero.getRepresentation();

        //Draw ogre
        int[] ogreCoordinates = ogre.getCoordinates();
        int[] ogreClubOffsetCoordinates = ogre.getClubOffset();
        int ogreX = ogreCoordinates[0];
        int ogreClubX = ogreX + ogreClubOffsetCoordinates[0];
        int ogreY = ogreCoordinates[1];
        int ogreClubY = ogreY + ogreClubOffsetCoordinates[1];
        matrix[ogreY][ogreX] = ogre.getRepresentation();
        matrix[ogreClubY][ogreClubX] = (ogre.clubIsOnKey() ? '$' : '*');

        return matrix;
    }

    public Level getNextLevel() {
        return null;
    }
}
