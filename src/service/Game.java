package service;

import business.DeckFactory;
import persistent.GameLogger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {

    private ArrayList<Card> bank = new ArrayList<>();
    private int cardAmount;
    private int playerAmount;
    private GameLogger logger;

    public void shuffleBank(){
        Random rnd = new Random();
        for (int i = 0; i < bank.size()*5; i++){
            int card1 = rnd.nextInt(bank.size());
            int card2 = rnd.nextInt(bank.size());
            if (card1 != card2){
                Collections.swap(bank, card1, card2);
            }
        }
    }

    public void createBank(int amount, boolean asDeck){
        ArrayList<Deck> decks = new ArrayList<>();
        int wholeDecks;
        int rest = 0;

        if (!asDeck){
            wholeDecks = Math.round(amount / 52) +1;
            rest = 52 - (amount % 52);
        }
        else {
            wholeDecks = amount;
        }

        for (int i = 0; i < wholeDecks; i++){
            decks.add(DeckFactory.createDeck());
        }

        for (int i = 0; i < decks.size(); i++){
            for (int j = 0; j < decks.get(i).getDeck().size(); j++){
                bank.add(decks.get(i).getDeck().get(j));
            }
        }

        if (rest != 0){
            Random rnd = new Random();

            for (int i = 0; i < rest; i++){
                bank.remove(rnd.nextInt(bank.size()));
            }
        }
    }

    public Game() throws FileNotFoundException{
        this.logger=GameLogger.getInstance();
    }

    public GameLogger getLogger() {
        return logger;
    }

    public int getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(int cardAmount) {
        this.cardAmount=cardAmount;
    }

    public int getPlayerAmount() {
        return playerAmount;
    }

    public void setPlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
    }

    public static class GameBuilder {

        private Game game;

        public GameBuilder setCardAmount(int amount){
            game.setCardAmount(amount);
            return this;
        }

        public GameBuilder setPlayerAmount(int amount){
            game.setPlayerAmount(amount);
            return this;
        }

        public Game build(){
            return game;
        }
    }
}
