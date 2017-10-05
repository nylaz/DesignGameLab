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
        writer.append("***Game starting with " + bank.size() + " cards and " +
        players.size() + " players***");
        writer.flush();
    }

    public void logRound(){
        writer.append("");
        writer.flush();
    }
}
