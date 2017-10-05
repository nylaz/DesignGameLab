package domain;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand = new ArrayList<>();
    private int sum;

    public Player(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getSum(){
        return sum;
    }

    public void setSum(int sum){
        this.sum=sum;
    }
}
