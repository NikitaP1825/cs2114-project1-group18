import java.util.ArrayList;
//-------------------------------------------------------------------------
/**
* Is the guess a valid guess
* 
* @author chandrikakavuri
* @version Sep 22, 2026
*/

import java.util.ArrayList;

public class WordValidator {

    private WordBank wordBank;

    public WordValidator(WordBank wordBank) {
        this.wordBank = wordBank;
    }

    /**
     * Checks whether a guess is valid.
     */
    public boolean isValidGuess(
        String guess,
        ArrayList<String> previousGuesses) {

        if (guess == null || previousGuesses == null) {
            return false;
        }

        guess = guess.toLowerCase();

        if (guess.length() != 5) {
            return false;
        }

        // Only allow English letters.
        for (int i = 0; i < guess.length(); i++) {
            char letter = guess.charAt(i);

            if (letter < 'a' || letter > 'z') {
                return false;
            }
        }

        if (!wordBank.containsWord(guess)) {
            return false;
        }

        if (isRepeatedGuess(guess, previousGuesses)) {
            return false;
        }

        return true;
    }

    /**
     * Checks whether this word has already been guessed.
     */
    public boolean isRepeatedGuess(
        String guess,
        ArrayList<String> previousGuesses) {

        if (guess == null || previousGuesses == null) {
            return false;
        }

        for (String previous : previousGuesses) {
            if (previous != null
                && previous.equalsIgnoreCase(guess)) {
                return true;
            }
        }

        return false;
    }
}