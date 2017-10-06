package servise;

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
}
