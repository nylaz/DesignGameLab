package persistent;

import domain.Card;
import domain.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public final class GameLogger {

    private static volatile GameLogger instance = null;
    private File logFile= new File("gamelog.txt");
    private PrintWriter writer = new PrintWriter(new FileOutputStream(logFile));;

    private GameLogger() throws FileNotFoundException {

    }

    public static GameLogger getInstance() throws FileNotFoundException {
        if (instance == null){
            synchronized (GameLogger.class){
                if (instance == null){
                    instance = new GameLogger();
                }
            }
        }
        return instance;
    }

    public void logSetup(ArrayList<Card> bank, ArrayList<Player> players){
        writer.append("* " + "Game starting with " + bank.size() + " cards and " +
        players.size() + " players" + "\n");
        players.forEach(player -> writer.append("*" + player.getName() + "\n"));
        writer.append("* " + "GAME IS NOW STARTING" + "\n");
        writer.flush();
    }

    public void logRound(Player player, int sum){
        writer.append("* " + player.getName() + " now have:" + "\n");
        for (Card c : player.getHand()){
            writer.append(c.getName() + "\n");
        }
        writer.append("With a total sum of: " + sum + "\n");
        writer.flush();
    }

    public void logHit(Player player){
        writer.append("* " + player.getName() + " chose to hit and got dealt: " +
                player.getHand().get(player.getHand().size()-1).getName() +  "\n");
        writer.flush();
    }

    public void hit21(Player player){
        writer.append("* " + player.getName() + " hit 21" + "\n");
        writer.flush();
    }

    public void gotBusted(Player player){
        writer.append("* " + player.getName() + " got busted" + "\n");
        writer.flush();
    }

    public void logStand(Player player) {
        writer.append("* " + player.getName() + " chose to stand" + "\n");
        writer.flush();
    }

    public void winnerIS(Player winner) {
        writer.append("* " + winner.getName() + " won the game with: " + "\n");
        for (Card c : winner.getHand()){
            writer.append(c.getName() + "\n");
        }
        writer.append("With a total sum of: " + winner.getSum() + "\n");
        writer.flush();
    }

    public void newTurn(Player player) {
        writer.append("* " + player.getName() + " gets dealt a starting hand" + "\n");
        writer.flush();
    }
}
