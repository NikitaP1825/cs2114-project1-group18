
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Console interface for Word2.
 * Displays the game, previous guesses, and game history.
 */
public class GameDisplay {

    private Scanner input;
    private Word2Game game;

    // History of completed games.
    private ArrayList<GameRecord> gameHistory;

    /**
     * Stores a snapshot of one completed game.
     */
    private static class GameRecord {

        private ArrayList<String> guesses;
        private ArrayList<String> feedback1;
        private ArrayList<String> feedback2;

        private String target1;
        private String target2;

        private boolean won;

        public GameRecord(Word2Game game) {

            guesses = game.getGuesses();
            feedback1 = game.getFeedbackHistory1();
            feedback2 = game.getFeedbackHistory2();

            target1 = game.getTargetWord1();
            target2 = game.getTargetWord2();

            won = game.hasWon();
        }
    }

    /**
     * Creates the display and initializes game history.
     */
    public GameDisplay() {
        input = new Scanner(System.in);
        game = new Word2Game();

        gameHistory = new ArrayList<GameRecord>();
    }

    /**
     * Displays the game instructions.
     */
    public void displayGame() {

        System.out.println();
        System.out.println("========================");
        System.out.println("         WORD²");
        System.out.println("========================");

        System.out.println("Guess TWO five-letter words!");
        System.out.println("You have SEVEN valid guesses.");
        System.out.println();

        System.out.println("Each guess applies to both words.");
        System.out.println("✓ = Correct letter and position");
        System.out.println("~ = Correct letter, wrong position");
        System.out.println("X = Letter not present");

        System.out.println("========================");
    }

    /**
     * Reads a guess from the console.
     */
    public String getGuess() {
        System.out.print("\nEnter your guess: ");
        return input.nextLine();
    }

    /**
     * Displays feedback for both target words.
     */
    public void displayFeedback(
        String feedback1,
        String feedback2) {

        System.out.println("WORD 1: " + feedback1);
        System.out.println("WORD 2: " + feedback2);
    }

    /**
     * Displays an invalid-input message.
     */
    public void displayError(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Displays the final result.
     */
    public void displayResult(boolean won) {

        if (won) {
            System.out.println();
            System.out.println("CONGRATULATIONS!");
            System.out.println("You guessed both words!");
        }
        else {
            System.out.println();
            System.out.println("GAME OVER!");
            System.out.println("You ran out of guesses.");
        }
    }

    /**
     * Displays the complete board for the current game.
     */
    public void displayBoard() {

        System.out.println();
        System.out.println(
            "====================================");
        System.out.println(
            "             WORD² BOARD");
        System.out.println(
            "====================================");

        System.out.printf(
            "%-8s %-12s %s%n",
            "GUESS", "WORD 1", "WORD 2");

        System.out.println(
            "------------------------------------");

        ArrayList<String> guesses = game.getGuesses();

        ArrayList<String> history1 =
            game.getFeedbackHistory1();

        ArrayList<String> history2 =
            game.getFeedbackHistory2();

        for (int i = 0; i < guesses.size(); i++) {

            System.out.printf(
                "%-8s %-12s %s%n",
                guesses.get(i).toUpperCase(),
                history1.get(i),
                history2.get(i));
        }

        System.out.println(
            "------------------------------------");

        if (game.isWord1Guessed()) {
            System.out.println("WORD 1: SOLVED!");
        }

        if (game.isWord2Guessed()) {
            System.out.println("WORD 2: SOLVED!");
        }

        System.out.println(
            "Guesses remaining: "
            + game.getGuessesRemaining());
    }

    /**
     * Saves the completed game to history.
     */
    private void saveGameToHistory() {

        GameRecord record = new GameRecord(game);

        gameHistory.add(record);
    }

    /**
     * Displays a list of previously completed games.
     */
    public void displayGameHistory() {

        while (true) {

            System.out.println();
            System.out.println(
                "========== GAME HISTORY ==========");

            if (gameHistory.isEmpty()) {

                System.out.println(
                    "No completed games yet.");

                return;
            }

            System.out.printf(
                "%-8s %-10s %s%n",
                "GAME", "RESULT", "GUESSES");

            System.out.println(
                "----------------------------------");

            for (int i = 0; i < gameHistory.size(); i++) {

                GameRecord record = gameHistory.get(i);

                String result =
                    record.won ? "WON" : "LOST";

                System.out.printf(
                    "%-8d %-10s %d/7%n",
                    i + 1,
                    result,
                    record.guesses.size());
            }

            System.out.println(
                "----------------------------------");

            System.out.println(
                "Enter a game number to view details.");

            System.out.println(
                "Enter 0 to return to the menu.");

            System.out.print("\nSelection: ");

            String selection = input.nextLine();

            int gameNumber;

            try {
                gameNumber =
                    Integer.parseInt(selection.trim());
            }
            catch (NumberFormatException e) {

                displayError(
                    "Please enter a valid number.");

                continue;
            }

            if (gameNumber == 0) {
                return;
            }

            if (gameNumber < 1
                || gameNumber > gameHistory.size()) {

                displayError(
                    "That game does not exist.");

                continue;
            }

            displayGameDetails(gameNumber - 1);

            System.out.println();
            System.out.print(
                "Press Enter to return to history...");

            input.nextLine();
        }
    }

    /**
     * Displays all guesses and feedback for a past game.
     */
    private void displayGameDetails(int index) {

        GameRecord record = gameHistory.get(index);

        System.out.println();
        System.out.println(
            "========== GAME "
            + (index + 1)
            + " ==========");

        System.out.println(
            "Result: "
            + (record.won ? "WON" : "LOST"));

        System.out.println(
            "Guesses used: "
            + record.guesses.size()
            + "/7");

        System.out.println();

        System.out.printf(
            "%-8s %-12s %s%n",
            "GUESS", "WORD 1", "WORD 2");

        System.out.println(
            "----------------------------------");

        for (int i = 0; i < record.guesses.size(); i++) {

            System.out.printf(
                "%-8s %-12s %s%n",
                record.guesses.get(i).toUpperCase(),
                record.feedback1.get(i),
                record.feedback2.get(i));
        }

        System.out.println(
            "----------------------------------");

        System.out.println(
            "Word 1: "
            + record.target1.toUpperCase());

        System.out.println(
            "Word 2: "
            + record.target2.toUpperCase());
    }

    /**
     * Displays the menu after each completed game.
     * Returns true if the player wants another game.
     */
    private boolean showEndMenu() {

        while (true) {

            System.out.println();
            System.out.println(
                "========== MAIN MENU ==========");

            System.out.println("1. Play Again");
            System.out.println("2. Game History");
            System.out.println("3. Exit");

            System.out.print("\nChoose an option: ");

            String choice = input.nextLine().trim();

            if (choice.equals("1")) {

                game.startGame();

                return true;
            }

            if (choice.equals("2")) {

                displayGameHistory();
            }
            else if (choice.equals("3")) {

                return false;
            }
            else {

                displayError(
                    "Please choose 1, 2, or 3.");
            }
        }
    }

    /**
     * Runs the game.
     */
    public void play() {

        boolean playAgain = true;

        while (playAgain) {

            displayGame();
            displayBoard();

            // Keep accepting guesses until the game ends.
            while (!game.isGameOver()) {

                String guess = getGuess();

                game.submitGuess(guess);

                if (!game.wasLastGuessAccepted()) {

                    displayError(
                        game.getErrorMessage());

                    continue;
                }

                // Reprint the entire board.
                displayBoard();
            }

            // Display and save the completed game.
            displayResult(game.hasWon());

            System.out.println(
                "Word 1 was: "
                + game.getTargetWord1());

            System.out.println(
                "Word 2 was: "
                + game.getTargetWord2());

            saveGameToHistory();

            // Allow the player to review past games.
            playAgain = showEndMenu();
        }

        System.out.println();
        System.out.println(
            "Thanks for playing Word²!");
    }

    /**
     * Starts the application.
     */
    public static void main(String[] args) {

        GameDisplay display = new GameDisplay();

        display.play();
    }
}