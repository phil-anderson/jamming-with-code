class MutablePegSequence extends PegSequence
{
    public MutablePegSequence(Peg... pegs)
    {
        super(pegs);
    }

    public void removePegAt(int position)
    {
        if(isValidPegNumber(position))
        {
            pegs[position] = null;
        }
    }
}
