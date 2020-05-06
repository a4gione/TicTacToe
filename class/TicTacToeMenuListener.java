
import java.awt.Desktop;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author AnthonyF
 */
public class TicTacToeMenuListener implements MenuListener {

    final TicTacToeMenuType type;

    public TicTacToeMenuListener(final TicTacToeMenuType type) {
        this.type = type;
    }

    @Override
    public void menuSelected(MenuEvent e) {

        switch (type) {
            case HELP:
                loadHelpDoc();
                break;
            case NEW_GAME:
                TicTacToe.newGame();
                break;
            case PLAY_AGAIN:
                TicTacToe.playAgain();
                break;
            default:
        }

    }

    @Override
    public void menuDeselected(MenuEvent e) {
        // do nothing on menu-de-selection
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        // do nothing on menu-cancellation
    }

    private void loadHelpDoc() {
        try {
            Desktop.getDesktop().open(new java.io.File("src/main/java/Help.docx")); //Doc path for "Help"
        } catch (Exception ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
