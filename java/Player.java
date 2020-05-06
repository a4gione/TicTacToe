 
/**
* The Player class for the COT4930 project. This class must be extended for the project.
*/
public class Player {
 
    private String name;
    private int score;
 
    /**
     * The constructor creates a default Player object.
     */
    public Player() {
        name = "";
    }
 
    /**
     * The constructor creates a Player object with the specified name.
     *
     * @param n represents the name of the Player.
     */
    public Player(String n) {
        name = n;
    }
 
    /**
     * Method to retrieve the name of the player.
     *
     * @return a String representing the name of the Player.
     */
    public String getName() { // return the name
        return name;
    }
 
    /**
     * Method to set the Players name.
     *
     * @param n represents the name of the Player.
     */
    public void setName(String n) { // set the PLayers name
        name = n;
    }
    // Method to set the players score.
    // Param s represents the score of the player.
 
    public void setScore(int s) {
        score = s;
    }
    // Method to retrieve the score of the player. Return string
    // represents the score.
 
    public int getScore() {
        return score;
    }
}


