package GameDisplay;
/** 
 *Displays the game information and communicates with the player. 
 *Handles player input, feedback, errors, and game results. * 
 *@author Aditi Sambaraju * @version September 2026 
 **/
import java.util.Scanner;
public class GameDisplay {
    /** 
     *Scanner used to get input from the player. 
     **/
    private Scanner input;
    
    /** 
     *Creates a new GameDisplay and initializes the input scanner. 
     **/
    public GameDisplay(){
        input = new Scanner(System.in);
    }
    
    /** 
     *Displays the game instructions and feedback symbols. 
     **/
    public void DisplayGame(){
        System.out.print("Welcome to wordle squared!!");
        System.out.println("Try to guess both 5-letter words.");
        System.out.println("You have 7 valid guesses.");
        System.out.println("✓ = correct letter and position");
        System.out.println("~ = correct letter, wrong position");
        System.out.println("X = letter is not in the word");
    }
    
    /** 
    *Gets a guess from the player. 
    *@return the guess entered by the player 
    **/
    public String getGuess(){
        return input.nextLine();
    }
    
    /**
     * Displays the feedback for both target words. 
     *@param feedback1 feedback for the first target word 
     *@param feedback2 feedback for the second target word 
     **/
    public void displayFeedback(String feedback1, String feedback2){
        System.out.println("Word 1: " + feedback1);
        System.out.println("Word 2: " + feedback2);
    }
    
    /**
     * Displays an error message to the player. 
     *@param message the error message to display 
     **/
    public void displayError(String message){
        System.out.print("Error: " + message);
    }

    /** 
     *Displays the result of the game. 
     *@param won true if the player won, 
     *false if the player lost 
     **/
    public void displayResult(boolean won){

        if (won){
            System.out.println("YOU WON!!!");
        }
        else{
            System.out.println("YOU LOST!!!");
        }
    } 
}