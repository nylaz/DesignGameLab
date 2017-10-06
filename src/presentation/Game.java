package presentation;

import domain.Card;
import domain.Player;
import persistent.GameLogger;
import servise.BankFactory;
import servise.GameService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Game implements Runnable{

    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<Card> bank;
    private Player croupier = new Player("Croupier");
    private ArrayList<Player> players = new ArrayList<>();
    private GameLogger logger;
    private Player winner;

    public Game() throws FileNotFoundException{
        this.logger=GameLogger.getInstance();
    }

    @Override
    public void run() {
        initiateGame();
        dealToPlayers();
        dealToCroupier();
        endGame();
    }

    private void initiateGame(){
        System.out.println("Welcome to BlackJack");
        System.out.println("Would you like to play with:");
        System.out.println("1. One Deck of Cards");
        System.out.println("2. Two Deck of Cards");
        System.out.println("3. Choose card amount");
        int choice = 0;
        try {
            choice = Integer.parseInt(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (choice){
            case 1 :
                this.bank = new BankFactory().getBank(1, true);
                break;
            case 2 :
                this.bank = new BankFactory().getBank(2, true);
                break;
            case 3:
                System.out.println("How many cards would you like to play with?");
                int cAmount = 0;
                try {
                    cAmount = Integer.parseInt(input.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.bank = new BankFactory().getBank(cAmount, false);
                break;
        }
        System.out.println("How many players are playing?");
        int pAmount = 0;
        try {
            pAmount = Integer.parseInt(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < pAmount; i++){
            System.out.println("Player" + (i+1) + " name: ");
            String name = null;
            try {
                name = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            players.add(new Player(name));
        }
        logger.logSetup(bank, players);

        System.out.println("Game is Starting!");
    }

    private void dealToPlayers(){
        for (int i = 0; i < players.size(); i++){
            logger.newTurn(players.get(i));
            for (int j = 0; j < 2; j++){
                Random rnd = new Random();
                int card = rnd.nextInt(bank.size());
                players.get(i).getHand().add(bank.get(card));
                bank.remove(card);
            }
            boolean yes = true;
            while (yes){
                int sum = 0;
                for (int l = 0; l < players.get(i).getHand().size(); l++){
                    sum = sum + players.get(i).getHand().get(l).getValue();
                }
                System.out.println(players.get(i).getName() + ", you have :");
                for (int k = 0; k < players.get(i).getHand().size(); k++){
                    System.out.println(players.get(i).getHand().get(k).getName());
                }
                System.out.println("Your hands value is: " + sum);
                logger.logRound(players.get(i), sum);
                if (GameService.is21(sum)){
                    System.out.println("21!");
                    players.get(i).setSum(sum);
                    logger.hit21(players.get(i));
                    break;
                }
                if (GameService.isOver21(sum)) {
                    System.out.println("BUSTED!");
                    players.get(i).setSum(sum);
                    logger.gotBusted(players.get(i));
                    break;
                }
                System.out.println(players.get(i).getName() + ", would you like to hit? (y/n)");
                String yesNo = "";
                try {
                    yesNo = input.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch (yesNo) {
                    case "y":
                        Random rnd2 = new Random();
                        int card = rnd2.nextInt(bank.size());
                        players.get(i).getHand().add(bank.get(card));
                        bank.remove(card);
                        logger.logHit(players.get(i));
                        break;
                    case "n":
                        players.get(i).setSum(sum);
                        logger.logStand(players.get(i));
                        yes=false;
                        break;
                }
            }
        }
    }

    private void dealToCroupier(){
        while(true) {
            Random rnd = new Random();
            int sum = 0;
            int card = rnd.nextInt(bank.size());
            croupier.getHand().add((bank.get(card)));
            bank.remove(card);
            logger.logHit(croupier);
            for (int i = 0; i < croupier.getHand().size(); i++){
                sum = sum + croupier.getHand().get(i).getValue();
            }
            logger.logRound(croupier, sum);
            if (GameService.isOrOver17(sum)){
                System.out.println(croupier.getName() + " stopped at:");
                for (int j = 0; j < croupier.getHand().size(); j++){
                    System.out.println(croupier.getHand().get(j).getName());
                }
                System.out.println("With a value of : " + sum);
                croupier.setSum(sum);
                logger.logStand(croupier);
                break;
            }
            if (GameService.isOver21(sum)){
                System.out.println("BUSTED!");
                logger.gotBusted(croupier);
                break;
            }
        }
    }

    private void endGame(){
        ArrayList<Player> everyone = new ArrayList<>();
        everyone.add(croupier);
        everyone.addAll(players);
        everyone.removeIf(player -> player.getSum()>21);
        everyone.sort(Comparator.comparing(Player::getSum));
        try {
            winner = everyone.get(everyone.size()-1);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        System.out.println("Winner is " + winner.getName() + " with a winning hand of: ");
        winner.getHand().forEach(card -> System.out.println(card.getName()));
        System.out.println("With a total sum of: " + winner.getSum());
        logger.winnerIS(winner);
    }
}