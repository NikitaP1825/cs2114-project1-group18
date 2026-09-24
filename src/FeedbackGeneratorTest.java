
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedbackGeneratorTest {

    private FeedbackGenerator generator;

    @BeforeEach
    public void setUp() {
        generator = new FeedbackGenerator();
    }

    @Test
    public void testExactMatch() {
        assertEquals(
            "✓✓✓✓✓",
            generator.generateFeedback("apple", "apple"));
    }

    @Test
    public void testNoMatchingLetters() {
        assertEquals(
            "XXXXX",
            generator.generateFeedback("brick", "mouse"));
    }

    @Test
    public void testWrongPositions() {
        assertEquals(
            "~~~~~",
            generator.generateFeedback("stare", "rates"));
    }


    @Test
    public void testMixedFeedback() {
        assertEquals(
            "XX✓✓✓",
            generator.generateFeedback("crane", "plane"));
    }

    @Test
    public void testUppercase() {
        assertEquals(
            "✓✓✓✓✓",
            generator.generateFeedback("APPLE", "apple"));
    }

    @Test
    public void testNullGuess() {
        assertThrows(
            IllegalArgumentException.class,
            () -> generator.generateFeedback(null, "apple"));
    }

    @Test
    public void testInvalidLength() {
        assertThrows(
            IllegalArgumentException.class,
            () -> generator.generateFeedback("bird", "apple"));
    }

    @Test
    public void testInvalidCharacters() {
        assertThrows(
            IllegalArgumentException.class,
            () -> generator.generateFeedback("app!e", "apple"));
    }
}