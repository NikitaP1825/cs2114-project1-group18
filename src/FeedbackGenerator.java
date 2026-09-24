// -------------------------------------------------------------------------
/**
 *  Feedback Generator
 * 
 *  @author madisonnitti
 *  @version Sep 24, 2026
 */

public class FeedbackGenerator {

    public FeedbackGenerator() {
        // No fields need to be initialized.
    }

    /**
     * Generates feedback for a five-letter guess.
     *
     * ✓ = Correct letter and position
     * ~ = Correct letter, wrong position
     * X = Letter not present
     */
    public String generateFeedback(
        String guess,
        String target) {

        if (guess == null || target == null
            || !guess.matches("[a-zA-Z]{5}")
            || !target.matches("[a-zA-Z]{5}")) {

            throw new IllegalArgumentException(
                "Both words must contain five letters.");
        }

        guess = guess.toLowerCase();
        target = target.toLowerCase();

        String[] feedback = {"X", "X", "X", "X", "X"};

        boolean[] used = new boolean[5];

        // First pass: exact matches.
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == target.charAt(i)) {
                feedback[i] = "✓";
                used[i] = true;
            }
        }

        // Second pass: correct letters in wrong positions.
        for (int i = 0; i < 5; i++) {

            if (feedback[i].equals("✓")) {
                continue;
            }

            for (int j = 0; j < 5; j++) {

                if (!used[j]
                    && guess.charAt(i) == target.charAt(j)) {

                    feedback[i] = "~";
                    used[j] = true;
                    break;
                }
            }
        }

        String result = "";

        for (String symbol : feedback) {
            result += symbol;
        }

        return result;
    }
}