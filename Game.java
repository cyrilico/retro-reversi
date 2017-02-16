import java.util.Scanner;

public class Game {
    public char[][][] maps = {
                                {
                                    {'X','X','X','X','X','X','X','X','X','X'},
                                    {'X','H','.','.','I','.','X','.','G','X'},
                                    {'X','X','X','.','X','X','X','.','.','X'},
                                    {'X','.','I','.','I','.','X','.','.','X'},
                                    {'X','X','X','.','X','X','X','.','.','X'},
                                    {'I','.','.','.','.','.','.','.','.','X'},
                                    {'I','.','.','.','.','.','.','.','.','X'},
                                    {'X','X','X','.','X','X','X','X','.','X'},
                                    {'X','.','I','.','I','.','X','k','.','X'},
                                    {'X','X','X','X','X','X','X','X','X','X'}
                                },
                                    {}    
    };

    public Game() {
 
    }

    public static void main(String[] args) {
        Game g1 = new Game();  
            for(char[] line : g1.maps[0]){
                for(char element : line)
                    System.out.print(element);
                System.out.println("");
            }
        }
}
