package cli;
import logic.*;

import java.util.Scanner;

public class DKeep {
	public Game game;

	public DKeep() {
		game = new Game();
	}

	public static void main(String[] args) {
		  	DKeep session = new DKeep();
		  	Scanner buffer = new Scanner(System.in);
	        System.out.print(session.game.getCurrentMatrix()); //Show initial map before user presses a key for the first time (from now on only is reprinted when he moves)

	        do {
	        	System.out.print("Insert the next movement (w,s,a,d - everything else is ignored): ");
	    		char nextHeroMovement = buffer.next().charAt(0);
	            session.game.updateGame(nextHeroMovement);
	            System.out.print(session.game.getCurrentMatrix());
                System.out.print("\n\n");
	        } while(session.game.isRunning());

	        buffer.close();

	        session.game.finalMessage(); /*Instead of that switch, which will be in the Game class*/
	}
}
