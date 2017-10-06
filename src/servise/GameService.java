package servise;

import domain.Card;
import domain.Player;

public class GameService {

    public static boolean is21(int sum){
        return sum == 21;
    }

    public static boolean isOver21(int sum){
        return sum > 21;
    }

    public static boolean isOrOver17(int sum){
        return sum >= 17;
    }

    public static void calculateSum(Player player, int sum) {
        for (Card c : player.getHand()){
            if (c.getValue() == 1 && sum > 21){
                //etc etc
            }
        }
    }
}
