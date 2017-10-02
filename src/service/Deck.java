package service;

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();

    public Deck(){

    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

}
