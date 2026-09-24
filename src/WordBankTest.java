
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class WordBankTest {

    @Test
    public void testContainsWord() {
        WordBank bank = new WordBank();

        assertTrue(bank.containsWord("apple"));
        assertTrue(bank.containsWord("grape"));
    }

    @Test
    public void testUppercaseWord() {
        WordBank bank = new WordBank();

        assertTrue(bank.containsWord("APPLE"));
    }

    @Test
    public void testUnknownWord() {
        WordBank bank = new WordBank();

        assertFalse(bank.containsWord("zzzzz"));
    }

    @Test
    public void testNullWord() {
        WordBank bank = new WordBank();

        assertFalse(bank.containsWord(null));
    }

    @Test
    public void testRandomWords() {
        WordBank bank = new WordBank();

        String[] words = bank.getRandomWords();

        assertEquals(2, words.length);
        assertNotEquals(words[0], words[1]);

        assertTrue(bank.containsWord(words[0]));
        assertTrue(bank.containsWord(words[1]));
    }

    @Test
    public void testRandomSelectionRepeatedly() {
        WordBank bank = new WordBank();

        for (int i = 0; i < 100; i++) {
            String[] words = bank.getRandomWords();

            assertNotEquals(words[0], words[1]);
        }
    }
}