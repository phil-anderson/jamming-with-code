import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class StackSortTest
{
    @Test
    public void canSortIntegers() throws Exception
    {
        List<Integer> result = new StackSort().sort(1, 5, 2, 9, 3);
        assertThat(result, contains(1, 2, 3, 5, 9));
    }

    @Test
    public void noEffectIfAlreadySorted() throws Exception
    {
        List<Integer> result = new StackSort().sort(1, 2, 3, 5, 9);
        assertThat(result, contains(1, 2, 3, 5, 9));
    }

    @Test
    public void canSortFromReverseOrder() throws Exception
    {
        List<Integer> result = new StackSort().sort(9, 5, 3, 2, 1);
        assertThat(result, contains(1, 2, 3, 5, 9));
    }

    @Test
    public void canSortAllTheSame() throws Exception
    {
        List<Integer> result = new StackSort().sort(1, 1, 1, 1, 1);
        assertThat(result, contains(1, 1, 1, 1, 1));
    }
}
