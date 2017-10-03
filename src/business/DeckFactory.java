package business;

import service.Card;
import service.Deck;

import java.util.ArrayList;

public class DeckFactory {

    public DeckFactory(){

    }

    public static Deck createDeck(){
        return new Deck();
    }
}
