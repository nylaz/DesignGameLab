package service;

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();

    public Deck(){
        String[] suits = {"H","S","D","C"};
        for (int suit = 0; suit < suits.length; suit ++){
            for (int value = 1; value < 14; value++){
                if (value < 11){
                    deck.add(new Card(suits[suit] + value, value));
                }
                else if (value == 11){
                    deck.add(new Card(suits[suit] + "Kn", 10));
                }
                else if (value == 11){
                    deck.add(new Card(suits[suit] + "Q", 10));
                }
                else if (value == 11){
                    deck.add(new Card(suits[suit] + "K", 10));
                }
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

}
