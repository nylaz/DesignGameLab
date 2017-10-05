package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Bank {

    private ArrayList<Card> bank = new ArrayList<>();

    public Bank(int amount, boolean asDeck){
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
            decks.add(new Deck());
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
        shuffleBank();
    }

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

    public ArrayList<Card> getBank() {
        return bank;
    }
}
