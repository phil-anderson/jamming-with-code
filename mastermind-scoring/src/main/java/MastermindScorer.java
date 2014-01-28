public class MastermindScorer
{
    private MutablePegSequence actual;
    private MutablePegSequence guess;

    public MastermindScorer(PegSequence actual, PegSequence guess)
    {
        if(!actual.hasSameLengthAs(guess))
        {
            throw new IllegalArgumentException("Guess must have same length as actual");
        }

        this.actual = actual.mutableCopy();
        this.guess = guess.mutableCopy();
    }

    public Score calculateScore()
    {
        Score score = new Score();
        for(int i = 0; i < actual.getLength(); i++)
        {
            if(markSamePegAt(i)) score.addRightColourRightPlace();
        }

        for(int i = 0; i < actual.getLength(); i++)
        {
            if(markActualContainsGuessedPegAt(i)) score.addRightColourWrongPlace();
        }
        return score;
    }

    public boolean markSamePegAt(int position)
    {
        if(pegsMatch(actual.getPegAt(position), guess.getPegAt(position)))
        {
            actual.removePegAt(position);
            guess.removePegAt(position);
            return true;
        }
        return false;
    }

    public boolean markActualContainsGuessedPegAt(int position)
    {
        int positionOfGuess = actual.firstIndexOf(guess.getPegAt(position));
        if(positionOfGuess >= 0)
        {
            guess.removePegAt(position);
            actual.removePegAt(positionOfGuess);
            return true;
        }
        return false;
    }

    private boolean pegsMatch(Peg peg1, Peg peg2)
    {
        return peg1 == peg2 && peg1 != null;
    }
}
