// -------------------------------------------------------------------------
/**
 *  Feedback Generator
 * 
 *  @author madisonnitti
 *  @version Sep 24, 2026
 */
public class FeedbackGenerator
{

}

    /**
     * Creates new Feedback Generator.
     */
    public FeedbackGenerator()
    {
        // no fields need to be initialized
    }


    /**
     * Compares a guess to a target word, and will return feedback for each
     * letter.
     * 
     * @param guess
     *            the player's valid guess
     * @param target
     *            the target word
     * @return the feedback for the guess
     */

    public String generateFeedBack(String guess, String target)
    {
        String feedback = "";

        for (int i = 0; i < guess.length(); i++)
        {
            char guessLetter = guess.charAt(i);

            if (guessLetter == target.charAt(i))
            {
                feedback = feedback + "✔";

            }
            else if (target.indexOf(guessLetter) != -1)
            {
                feedback = feedback + "~";
            }
            else
            {
                feedback = feedback + "X";
            }
        }
        return feedback;
    }
}
