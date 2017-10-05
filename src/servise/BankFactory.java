package servise;

import domain.Bank;
import domain.Card;

import java.util.ArrayList;

public class BankFactory {

    public ArrayList<Card> getBank(int amount, boolean asDeck) {
        return new Bank(amount, asDeck).getBank();
    }
}
