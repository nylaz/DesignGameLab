package presentation;

import business.GameBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {

    public static JFrame frame = new JFrame("Game");
    private JPanel panel1;
    private JButton homeButton;
    private JButton newGameButton;
    private JPanel contentPane;
    private JPanel homePane;
    private JTextArea welcomeToBlackJackTextArea;
    private JPanel preGamePane;
    private JRadioButton oneDeckRadioButton;
    private JRadioButton twoDecksRadioButton;
    private JRadioButton customCardsRadioButton;
    private JTextField textField1;
    private JLabel cardAmountLabel;
    private JLabel playersAmountPanel;
    private JRadioButton onePlayerRadioButton;
    private JRadioButton twoPlayersRadioButton;
    private JRadioButton threePlayersRadioButton;

    public Start() {

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.removeAll();
                contentPane.repaint();
                contentPane.revalidate();

                contentPane.add(homePane);
                contentPane.repaint();
                contentPane.revalidate();
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.removeAll();
                contentPane.repaint();
                contentPane.revalidate();

                contentPane.add(preGamePane);
                contentPane.repaint();
                contentPane.revalidate();
            }
        });
    }

    public static void main(String args[]){
        frame.setContentPane(new Start().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(640,480);
        frame.setVisible(true);
    }
}
