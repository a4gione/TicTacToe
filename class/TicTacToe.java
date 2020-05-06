import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
 
 
public class TicTacToe extends Player {
 
    static int alternate = 0;
    static JButton buttons[] = new JButton[9]; //9 buttons
    static JMenu newgame, help, playagain;
    static int dialog;
    static JMenuBar menu;
    static JFrame score, frame;
    static Player p1 = new Player();
    static Player p2 = new Player();
    static int count = 0;
    static int p1W, p2W = 0;
    static String[] option = {"Play Again", "Cancel"}; //Option to play again
    static String[] alert = {"Continue", "Cancel"}; //Options to continue playing, or cancel
 
    public static void TicTacToe() {
        frame = new JFrame("Tic Tac Toe");
        menu = new JMenuBar();
        UIManager.put("MenuBar.background", Color.WHITE);
        help = new JMenu("Help");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.add(help);
        help.addMenuListener(new TicTacToeMenuListener(TicTacToeMenuType.HELP));        
        playagain = new JMenu("Play Again");
        menu.add(playagain);
        playagain.addMenuListener(new TicTacToeMenuListener(TicTacToeMenuType.PLAY_AGAIN));
 
        newgame = new JMenu("New Game");
        menu.add(newgame);
        newgame.addMenuListener(new TicTacToeMenuListener(TicTacToeMenuType.NEW_GAME));
 
        frame.setJMenuBar(menu);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.blue);
            buttons[i].addActionListener(new GridButton());
            panel.add(buttons[i]);
        }
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(300, 300);
    }
 
    public static void winnerFound(Player p) {
        if (p == p1) {
            p1.setScore(p1W);
            dialog = JOptionPane.showOptionDialog(frame,
                    "Game Over!\n " + p1.getName() + " Wins!",
                    "Replay",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    option,
                    option[0]);
            score.dispose();
            //If user selects continue, a new game will begin.
            if (dialog == 0) {
                playAgain();
            }
            //If user selects quit, the game will exit.
            else if (dialog == 1) {
                System.exit(0);
            }
        } else if (p == p2) {
            p2.setScore(p2W);
            dialog = JOptionPane.showOptionDialog(frame,
                   "Game Over!\n " + p2.getName() + " Wins!",
                    "Replay",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    option,
                    option[0]);
            score.dispose();
            System.out.println(dialog + " = n");
            //If user selects continue, a new game will begin.
            if (dialog == 0) {
                playAgain();
            }
            //If user selects quit, the game will exit
            else if (dialog == 1) {
                System.exit(0);
            }
        }
    }
    /*
    Error function is used to present the user with error when there are no characters
    inserted for p1 and p2 names. Users need to submit valid names in order play again. 
    If the user doesn't enter a name to continue, the game will exit.
     */
    public static void startError() {
        Object[] options = {"Continue", "Quit"};
        //Alert users to enter their names.
        int n = JOptionPane.showOptionDialog(
                frame,
                "Please Insert Player Names:",
                "Insert Name",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                options, //the titles of buttons
                options[0]);
        //If the user selects continue, they will continue to a new game
        if (n == 0) {
            newGame();
        }
        //If the user selects quit, the program will exit.
        if (n == 1) {
            System.exit(0);
        }
    }
   
    public static void scoreboard() {
        //Creating a border around both players scores.
        score = new JFrame("Scoreboard"); // New JFrame for scoreboard
        score.setLayout(new FlowLayout());
        JLabel p1Score = new JLabel("   " + p1.getName() + "'s Score:   ");
        JLabel p2Score = new JLabel("   " + p2.getName() + "'s Score:   ");
        JLabel p1scoreDisplay = new JLabel(Integer.toString(p1.getScore()));
        JLabel p2scoreDisplay = new JLabel(Integer.toString(p2.getScore()));
        score.add(p1Score);
        score.add(p1scoreDisplay);
        score.add(new JSeparator(JSeparator.VERTICAL),BorderLayout.LINE_START);
        score.add(p2Score);
        score.add(p2scoreDisplay);
        score.pack();
        score.setVisible(true);
        score.setSize(300, 100);
        score.setLocation(350, 0);
        SwingUtilities.updateComponentTreeUI(score);
    }
 
    /*
     Checks to see if there is a winner.
     Grid is subjected below:
    |0|1|2|
    |3|4|5|
    |6|7|8|
     */
    public static boolean checkForWin() {
 
        //Horizontal checking for W. 3 tiles match = W, background color is set to yellow.
        if (buttons[0].getText().equals(buttons[1].getText()) && buttons[1].getText().equals(buttons[2].getText()
        ) && !buttons[0].getText().equals("")) {
            buttons[0].setBackground(Color.yellow);
            buttons[1].setBackground(Color.yellow);
            buttons[2].setBackground(Color.yellow);
            return true;
        } else if (buttons[3].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[5].getText()
        ) && !buttons[3].getText().equals("")) {
            buttons[3].setBackground(Color.yellow);
            buttons[4].setBackground(Color.yellow);
            buttons[5].setBackground(Color.yellow);
            return true;
        } else if (buttons[6].getText().equals(buttons[7].getText()) && buttons[7].getText().equals(buttons[8].getText()
        ) && !buttons[7].getText().equals("")) {
            buttons[6].setBackground(Color.yellow);
            buttons[7].setBackground(Color.yellow);
            buttons[8].setBackground(Color.yellow);
            return true;
        } // Vertical W. 3 tiles match = W, background color is set to yellow.
        else if (buttons[0].getText().equals(buttons[3].getText()) && buttons[3].getText().equals(buttons[6].getText()
        ) && !buttons[0].getText().equals("")) {
            buttons[0].setBackground(Color.yellow);
            buttons[3].setBackground(Color.yellow);
            buttons[6].setBackground(Color.yellow);
            return true;
        } else if (buttons[1].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[7].getText()
        ) && !buttons[7].getText().equals("")) {
            buttons[1].setBackground(Color.yellow);
            buttons[4].setBackground(Color.yellow);
            buttons[7].setBackground(Color.yellow);
            return true;
        } else if (buttons[2].getText().equals(buttons[5].getText()) && buttons[5].getText().equals(buttons[8].getText()
        ) && !buttons[8].getText().equals("")) {
            buttons[2].setBackground(Color.yellow);
            buttons[5].setBackground(Color.yellow);
            buttons[8].setBackground(Color.yellow);
            return true;
        } //Horizontal W. 3 tiles match = W, background color is set to yellow.
        else if (buttons[2].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[6].getText()
        ) && !buttons[2].getText().equals("")) {
            buttons[2].setBackground(Color.yellow);
            buttons[4].setBackground(Color.yellow);
            buttons[6].setBackground(Color.yellow);
            return true;
        } else if (buttons[0].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[8].getText()
        ) && !buttons[8].getText().equals("")) {
            buttons[0].setBackground(Color.yellow);
            buttons[4].setBackground(Color.yellow);
            buttons[8].setBackground(Color.yellow);
            return true;
        } else {
            return false;
        }
    }
 
    public static void playAgain() {
        if (p1.getName().equals("") && p1.getName().equals("")) {
            startError();
        }
        alternate = 0;
        frame.dispose();
        TicTacToe();
    }
 
    public static void newGame() {
        alternate = 0;
        p1W = 0;
        p2W = 0;
        p1.setScore(p1W);
        p2.setScore(p2W);
        
        JFrame newGame = new JFrame("New Game");
        JLabel label = new JLabel();
        label.setText("Player 1 Name: ");
        JButton submit = new JButton("Submit");
        JTextArea textArea = new JTextArea(1, 10);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String p = textArea.getText();
                p1.setName(p);
                newGame.dispose();
                insertPlayerWithName("Player 2");
            }
        });
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                newGame.toFront();
                newGame.repaint();
            }
        });
        newGame.add(label);
        newGame.add(textArea);
        newGame.add(submit);
        newGame.pack();
        newGame.setVisible(true);
        newGame.toFront();
        newGame.setSize(100, 130);
        newGame.setLayout(new FlowLayout());
        // newGame.setLocationRelativeTo(null);
        newGame.getRootPane().setDefaultButton(submit);
    }
    
    private static void insertPlayerWithName(String name) {
        JFrame newGame = new JFrame("New Game");
        JLabel label = new JLabel();
        label.setText(name + " Name: ");
        JButton select = new JButton("Submit");
        JTextArea textArea = new JTextArea(1, 10);
        select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String p = textArea.getText();
                p2.setName(p);
                System.out.println("p2 = " + p2.getName());
                newGame.dispose();
                frame.dispose();
                TicTacToe();
                scoreboard();
            }
        });
        newGame.add(label);
        newGame.add(textArea);
        newGame.add(select);
        newGame.getRootPane().setDefaultButton(select);
        newGame.pack();
        newGame.setVisible(true);
        newGame.setSize(100, 130);
        newGame.setLayout(new FlowLayout());
    }
 
    public static void main(String[] args) {
        TicTacToe();
    }
}
