
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Word2GameTest {

    @Test
    public void testNewGame() {
        Word2Game game = new Word2Game();

        assertEquals(7, game.getGuessesRemaining());
        assertFalse(game.isGameOver());
        assertFalse(game.hasWon());

        assertNotEquals(
            game.getTargetWord1(),
            game.getTargetWord2());
    }

    @Test
    public void testValidGuess() {
        Word2Game game = new Word2Game();

        game.submitGuess("apple");

        assertTrue(game.wasLastGuessAccepted());
        assertEquals(6, game.getGuessesRemaining());
        assertEquals(1, game.getGuesses().size());
    }

    @Test
    public void testInvalidGuess() {
        Word2Game game = new Word2Game();

        game.submitGuess("12345");

        assertFalse(game.wasLastGuessAccepted());
        assertEquals(7, game.getGuessesRemaining());
        assertEquals(0, game.getGuesses().size());
    }

    @Test
    public void testRepeatedGuess() {
        Word2Game game = new Word2Game();

        game.submitGuess("apple");
        game.submitGuess("APPLE");

        assertFalse(game.wasLastGuessAccepted());
        assertEquals(6, game.getGuessesRemaining());
        assertEquals(1, game.getGuesses().size());
    }

    @Test
    public void testGameReset() {
        Word2Game game = new Word2Game();

        game.submitGuess("apple");
        game.startGame();

        assertEquals(7, game.getGuessesRemaining());
        assertTrue(game.getGuesses().isEmpty());
        assertTrue(game.getFeedbackHistory1().isEmpty());
        assertTrue(game.getFeedbackHistory2().isEmpty());
        assertFalse(game.isGameOver());
        assertFalse(game.hasWon());
    }

    @Test
    public void testNullGuess() {
        Word2Game game = new Word2Game();

        game.submitGuess(null);

        assertFalse(game.wasLastGuessAccepted());
        assertEquals(7, game.getGuessesRemaining());
    }
}