
import java.util.ArrayList;

/**
 * Controls the Word2 game.
 * Manages target words, guesses, feedback history,
 * win conditions, and game resets.
 *
 * @author Nikita
 */
public class Word2Game {

    private String targetWord1;
    private String targetWord2;

    private ArrayList<String> guesses;
    private ArrayList<String> feedbackHistory1;
    private ArrayList<String> feedbackHistory2;

    private final int maxGuesses = 7;

    private boolean gameOver;
    private boolean word1Guessed;
    private boolean word2Guessed;

    private WordBank wordBank;
    private WordValidator validator;
    private FeedbackGenerator feedbackGenerator;

    private String feedback1;
    private String feedback2;
    private String errorMessage;
    private boolean lastGuessAccepted;

    /**
     * Creates a new game.
     */
    public Word2Game() {
        wordBank = new WordBank();
        validator = new WordValidator(wordBank);
        feedbackGenerator = new FeedbackGenerator();

        guesses = new ArrayList<String>();
        feedbackHistory1 = new ArrayList<String>();
        feedbackHistory2 = new ArrayList<String>();

        startGame();
    }

    /**
     * Starts a new game or resets an existing game.
     */
    public void startGame() {
        String[] targets = wordBank.getRandomWords();

        targetWord1 = targets[0];
        targetWord2 = targets[1];

        guesses.clear();
        feedbackHistory1.clear();
        feedbackHistory2.clear();

        gameOver = false;
        word1Guessed = false;
        word2Guessed = false;

        feedback1 = "";
        feedback2 = "";
        errorMessage = "";
        lastGuessAccepted = false;
    }

    /**
     * Processes a guess.
     * Invalid guesses do not use an attempt.
     */
    public void submitGuess(String guess) {

        lastGuessAccepted = false;
        errorMessage = "";

        if (gameOver) {
            errorMessage = "The game is already over.";
            return;
        }

        if (guess == null || guess.isEmpty()) {
            errorMessage =
                "Please enter a five-letter word.";
            return;
        }

        guess = guess.toLowerCase();

        // Reject repeated guesses.
        if (validator.isRepeatedGuess(guess, guesses)) {
            errorMessage =
                "You already guessed that word.";
            return;
        }

        // Reject invalid words.
        if (!validator.isValidGuess(guess, guesses)) {
            errorMessage =
                "Invalid guess. Enter a valid five-letter word.";
            return;
        }

        // Save the valid guess.
        guesses.add(guess);
        lastGuessAccepted = true;

        // Generate feedback for both target words.
        feedback1 = feedbackGenerator.generateFeedback(
            guess, targetWord1);

        feedback2 = feedbackGenerator.generateFeedback(
            guess, targetWord2);

        // Save the feedback for the game board.
        feedbackHistory1.add(feedback1);
        feedbackHistory2.add(feedback2);

        // Update solved-word status.
        if (guess.equals(targetWord1)) {
            word1Guessed = true;
        }

        if (guess.equals(targetWord2)) {
            word2Guessed = true;
        }

        // End the game after a win or seven guesses.
        if (hasWon() || guesses.size() >= maxGuesses) {
            gameOver = true;
        }
    }

    /**
     * Returns true when the game is over.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Returns the number of valid guesses remaining.
     */
    public int getGuessesRemaining() {
        return maxGuesses - guesses.size();
    }

    /**
     * Returns true when both target words are solved.
     */
    public boolean hasWon() {
        return word1Guessed && word2Guessed;
    }

    /**
     * Returns whether the last guess was accepted.
     */
    public boolean wasLastGuessAccepted() {
        return lastGuessAccepted;
    }

    /**
     * Returns the most recent error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns the latest feedback for Word 1.
     */
    public String getFeedback1() {
        return feedback1;
    }

    /**
     * Returns the latest feedback for Word 2.
     */
    public String getFeedback2() {
        return feedback2;
    }

    /**
     * Returns the first target word.
     */
    public String getTargetWord1() {
        return targetWord1;
    }

    /**
     * Returns the second target word.
     */
    public String getTargetWord2() {
        return targetWord2;
    }

    /**
     * Returns all accepted guesses in order.
     */
    public ArrayList<String> getGuesses() {
        return new ArrayList<String>(guesses);
    }

    /**
     * Returns all feedback for the first word.
     */
    public ArrayList<String> getFeedbackHistory1() {
        return new ArrayList<String>(feedbackHistory1);
    }

    /**
     * Returns all feedback for the second word.
     */
    public ArrayList<String> getFeedbackHistory2() {
        return new ArrayList<String>(feedbackHistory2);
    }

    /**
     * Returns whether the first word has been solved.
     */
    public boolean isWord1Guessed() {
        return word1Guessed;
    }

    /**
     * Returns whether the second word has been solved.
     */
    public boolean isWord2Guessed() {
        return word2Guessed;
    }
}