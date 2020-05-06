
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GridButton implements ActionListener {

    public GridButton() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource(); //Button select
        //Error Check: duplicate click --> N/A.
        //Prompt the user to select an open tile.
        if (buttonClicked.getText().equals("O") || buttonClicked.getText().equals("X")) {
            TicTacToe.dialog = JOptionPane.showOptionDialog(TicTacToe.frame,
                    "N/A\nPlease select an open tile",
                    "Error!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    TicTacToe.alert,
                    TicTacToe.alert[0]);
        }
        //If players try and start a game without entering names prior, error!
        //Prompts the users to input their names.
        if (TicTacToe.p1.getName().equals("") || TicTacToe.p2.getName().equals("")) {
            TicTacToe.startError();
            TicTacToe.alternate = 0;
        }

        //If button selected is valid, the button will change.
        //Background and font color while inserting X's & O's.
        if (buttonClicked.getText().equals("")) {
            if (TicTacToe.alternate % 2 == 0) {
                buttonClicked.setText("X");
                buttonClicked.setForeground(Color.white);
                TicTacToe.alternate++;
            } else {
                buttonClicked.setText("O");
                buttonClicked.setForeground(Color.white);
                TicTacToe.alternate++;

            }
        }

        //Check for a W which returns true,
        //Option to play again or quit the game is presented.
        if (TicTacToe.checkForWin() == true) {
            if ((buttonClicked.getText().equals("X"))) {
                TicTacToe.p1W++;
                TicTacToe.winnerFound(TicTacToe.p1);
                TicTacToe.scoreboard(); //Update score
            } else if (buttonClicked.getText().equals("O")) {
                TicTacToe.p2W++;   //Add a scoreboard
                TicTacToe.winnerFound(TicTacToe.p2);
                TicTacToe.scoreboard();
            }
        }

        int temp = 0;
        for (int i = 0; i < 9; i++) {
            if (TicTacToe.buttons[i].getText().equals("")) {
                temp++;
            }
        }
        if (temp == 0) {
            TicTacToe.dialog = JOptionPane.showOptionDialog(TicTacToe.frame,
                    "Game Over!\nResult: Tie",
                    "Play Again?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    TicTacToe.option,
                    TicTacToe.option[0]);
            TicTacToe.score.dispose();
            if (TicTacToe.dialog == 0) {
                TicTacToe.playAgain();
            }
            if (TicTacToe.dialog == 1) {
                System.exit(0);
            }
            TicTacToe.scoreboard();
        }
    }

}
