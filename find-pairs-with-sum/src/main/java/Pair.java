public class Pair<T>
{
    private T firstValue;
    private T secondValue;

    public Pair(T firstValue, T secondValue)
    {
        assert(firstValue != null);
        assert(secondValue != null);
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public T getFirstValue()
    {
        return firstValue;
    }

    public T getSecondValue()
    {
        return secondValue;
    }

    public boolean equivalentTo(Pair<T> other)
    {
        return (firstValue.equals(other.firstValue) && secondValue.equals(other.secondValue)) ||
               (firstValue.equals(other.secondValue) && secondValue.equals(other.firstValue));
    }
}
