public class AddNoAdd
{
    // TODO: Doesn't handle overflow
    public static int addWithoutPlus(int a, int b)
    {
        int temp = a ^ b;
        int carry = (a & b) << 1;
        return carry != 0 ? addWithoutPlus(temp, carry) : temp;
    }
}
