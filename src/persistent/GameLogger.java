package persistent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

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

    public void print(){
        writer.append("Hello");
        writer.flush();
        System.out.print("Hello World");
    }
}
