public class OddAndEvenBitSwapper
{
    private static final int ODDS_MASK = 0b01010101010101010101010101010101;
    private static final int EVENS_MASK = ~ODDS_MASK;

    public int swapBits(int num)
    {
        int odds = num & ODDS_MASK;
        int evens = num & EVENS_MASK;
        return (odds << 1) | (evens >>> 1);
    }
}
