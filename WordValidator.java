import java.util.ArrayList;

public class WordValidator {

    private WordBank wordBank;

    // ----------------------------------------------------------
    /**
     * Create a new WordValidator object.
     * @param wordBank
     */
    public WordValidator(WordBank wordBank) {
        this.wordBank = wordBank;
    }

    /**
     * Checks whether a word is valid.
     *
     * @param previousGuesses array list of previously guessed words
     * @param guess entered guess
     * @return whether the word is valid
     */
    public boolean isValidGuess(String guess, ArrayList<String> previousGuesses) {

        if (guess == null) {
            return false;
        }

        guess = guess.toLowerCase();

        if (guess.length() != 5) {
            return false;
        }

        for (int i = 0; i < guess.length(); i++) {
            if (!Character.isLetter(guess.charAt(i))) {
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
     * Checks whether a word is valid.
     *
     * @param previousGuesses array list of previously guessed words
     * @param guess entered guess
     * @return whether the current guess has already been guessed in the current game
     */
    public boolean isRepeatedGuess(String guess, ArrayList<String> previousGuesses) {

        if (guess == null) {
            return false;
        }

        guess = guess.toLowerCase();

        return previousGuesses.contains(guess);
    }
}
