import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackSort
{
    private Stack<Integer> sorted = new Stack<>();
    private Stack<Integer> buffer = new Stack<>();

    public List<Integer> sort(int... numbers)
    {
        for(int i : numbers)
        {
            addNumber(i);
        }

        List<Integer> result = new ArrayList<>();
        while (!sorted.isEmpty())
        {
            result.add(sorted.pop());
        }
        return result;
    }

    private void addNumber(int numberToAdd)
    {
        if(sorted.isEmpty() || sorted.peek() >= numberToAdd)
        {
            sorted.push(numberToAdd);
        }
        else
        {
            while(!sorted.isEmpty() && numberToAdd > sorted.peek())
            {
                buffer.push(sorted.pop());
            }
            sorted.push(numberToAdd);
            while(!buffer.isEmpty())
            {
                sorted.push(buffer.pop());
            }
        }
    }
}