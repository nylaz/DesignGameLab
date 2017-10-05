package presentation;

import domain.Game;

import java.io.FileNotFoundException;

public class Program {

    public static void main(String args[]){
        Game game = null;
        try {
            game = new Game();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        game.run();
    }
}
