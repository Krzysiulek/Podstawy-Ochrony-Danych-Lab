package Utils;

import org.junit.Test;

import java.util.Arrays;

public class XorUtilsTest {

    @Test
    public void xorTest() {
        byte[] tmp = {127};
        byte[] tmp2 = {};

        byte[] tmp3 = XorUtils.xorBytesArray(tmp, tmp2);
        System.out.println(Arrays.toString(tmp3));

        System.out.println(9 ^ 5);
    }
}