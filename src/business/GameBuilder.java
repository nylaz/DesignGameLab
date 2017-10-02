package business;

import service.Game;

public class GameBuilder {

    private static Game game = new Game();
    private static GameBuilder builder;

    public static GameBuilder setCardAmount(int amount){
        game.setPlayerAmount(amount);
        return builder;
    }

    public static GameBuilder setPlayerAmount(int amount){
        game.setPlayerAmount(amount);
        return builder;
    }

    public void build(){

    }
}
