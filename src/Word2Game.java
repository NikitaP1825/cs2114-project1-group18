
import java.util.ArrayList;

public class Word2Game {

    private String targetWord1;
    private String targetWord2;

    private ArrayList<String> guesses;
    private final int maxGuesses = 7;

    private boolean gameOver;
    private boolean word1Guessed;
    private boolean word2Guessed;

    private WordBank wordBank;
    private WordValidator validator;
    private FeedbackGenerator feedbackGenerator;

    // Creates a new game and initializes the other classes.
    public Word2Game() {
        wordBank = new WordBank();
        validator = new WordValidator(wordBank);
        feedbackGenerator = new FeedbackGenerator();

        guesses = new ArrayList<String>();

        startGame();
    }

    // Starts a new game or resets an existing game.
    public void startGame() {
        String[] targets = wordBank.getRandomWords();

        targetWord1 = targets[0];
        targetWord2 = targets[1];

        guesses.clear();

        word1Guessed = false;
        word2Guessed = false;
        gameOver = false;
    }

    // Processes a guess and updates the game.
    public void submitGuess(String guess) {

        // Do not accept guesses after the game ends.
        if (gameOver) {
            System.out.println("The game is already over!");
            return;
        }

        // Reject empty input.
        if (guess == null || guess.isEmpty()) {
            System.out.println("Please enter a five-letter word.");
            return;
        }

        // Treat uppercase and lowercase letters equally.
        guess = guess.toLowerCase();

        // Invalid guesses do not use an attempt.
        if (!validator.isValidGuess(guess, guesses)) {
            System.out.println(
                "Invalid or repeated guess. Please try again.");
            return;
        }

        // Store the valid guess.
        guesses.add(guess);

        // Generate separate feedback for both target words.
        String feedback1 =
            feedbackGenerator.generateFeedback(
                guess, targetWord1);

        String feedback2 =
            feedbackGenerator.generateFeedback(
                guess, targetWord2);

        System.out.println("Your guess: " + guess);
        System.out.println("Word 1: " + feedback1);
        System.out.println("Word 2: " + feedback2);

        // Check whether either word was solved.
        if (guess.equals(targetWord1)) {
            word1Guessed = true;
        }

        if (guess.equals(targetWord2)) {
            word2Guessed = true;
        }

        // End the game after a win or seven valid guesses.
        if (hasWon()) {
            gameOver = true;
            System.out.println(
                "Congratulations! You guessed both words!");
        }
        else if (guesses.size() >= maxGuesses) {
            gameOver = true;
            System.out.println("Game over!");
            System.out.println(
                "Word 1 was: " + targetWord1);
            System.out.println(
                "Word 2 was: " + targetWord2);
        }
        else {
            System.out.println(
                "Guesses remaining: "
                + getGuessesRemaining());
        }
    }

    // Returns true when the current game has ended.
    public boolean isGameOver() {
        return gameOver;
    }

    // Returns the number of valid guesses remaining.
    public int getGuessesRemaining() {
        return maxGuesses - guesses.size();
    }

    // Returns true only when both words have been solved.
    public boolean hasWon() {
        return word1Guessed && word2Guessed;
    }
}