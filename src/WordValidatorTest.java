
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordValidatorTest {

    private WordBank bank;
    private WordValidator validator;
    private ArrayList<String> previousGuesses;

    @BeforeEach
    public void setUp() {
        bank = new WordBank();
        validator = new WordValidator(bank);
        previousGuesses = new ArrayList<String>();
    }

    @Test
    public void testValidGuess() {
        assertTrue(
            validator.isValidGuess("apple", previousGuesses));
    }

    @Test
    public void testUppercaseGuess() {
        assertTrue(
            validator.isValidGuess("APPLE", previousGuesses));
    }

    @Test
    public void testInvalidLength() {
        assertFalse(
            validator.isValidGuess("bird", previousGuesses));

        assertFalse(
            validator.isValidGuess("toaster", previousGuesses));
    }

    @Test
    public void testNumbersAndSymbols() {
        assertFalse(
            validator.isValidGuess("app1e", previousGuesses));

        assertFalse(
            validator.isValidGuess("app!e", previousGuesses));
    }

    @Test
    public void testUnknownWord() {
        assertFalse(
            validator.isValidGuess("zzzzz", previousGuesses));
    }

    @Test
    public void testNullAndEmptyInput() {
        assertFalse(
            validator.isValidGuess(null, previousGuesses));

        assertFalse(
            validator.isValidGuess("", previousGuesses));
    }

    @Test
    public void testRepeatedGuess() {
        previousGuesses.add("apple");

        assertTrue(
            validator.isRepeatedGuess(
                "APPLE", previousGuesses));

        assertFalse(
            validator.isValidGuess(
                "apple", previousGuesses));
    }

    @Test
    public void testNotRepeated() {
        previousGuesses.add("apple");

        assertFalse(
            validator.isRepeatedGuess(
                "grape", previousGuesses));
    }

    @Test
    public void testNullPreviousGuesses() {
        assertFalse(
            validator.isValidGuess("apple", null));

        assertFalse(
            validator.isRepeatedGuess("apple", null));
    }
}