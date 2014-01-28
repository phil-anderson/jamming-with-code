import java.util.Arrays;

public class PegSequence
{
    protected Peg[] pegs;

    public PegSequence(Peg... pegs)
    {
        this.pegs = Arrays.copyOf(pegs, pegs.length);
    }

    public int getLength()
    {
        return pegs.length;
    }

    public boolean hasSameLengthAs(PegSequence other)
    {
        return pegs.length == other.pegs.length;
    }

    public Peg getPegAt(int i)
    {
        return isValidPegNumber(i) ? pegs[i] : null;
    }

    public int firstIndexOf(Peg peg)
    {
        if(peg != null)
        {
            for(int i = 0; i < pegs.length; i++)
            {
                if(pegs[i] == peg) return i;
            }
        }
        return -1;
    }

    protected boolean isValidPegNumber(int position)
    {
        return position >= 0 && position < pegs.length;
    }

    MutablePegSequence mutableCopy()
    {
        return new MutablePegSequence(pegs);
    }
}
