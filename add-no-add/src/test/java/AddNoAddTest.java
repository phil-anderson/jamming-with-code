import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNoAddTest
{
    @Test
    public void basicAddWorks()
    {
        assertThat(AddNoAdd.addWithoutPlus(2, 2), is(4));
        assertThat(AddNoAdd.addWithoutPlus(97, 33), is(130));
    }

    @Test
    public void zeroPlusZeroIsZero()
    {
        assertThat(AddNoAdd.addWithoutPlus(0, 0), is(0));
    }

    @Test
    public void allOnesAddsCorrectly()
    {
        assertThat(AddNoAdd.addWithoutPlus(0b11111111, 0b11111111), is(0b111111110));
    }
}
