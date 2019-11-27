package Utils;

import org.junit.Assert;
import org.junit.Test;

public class BitUtilsTest {

    @Test
    public void testSetAndUnsetMethod() throws Exception {
        int x = 8;

        Assert.assertEquals(0, BitUtils.getLSB(x));

        x = BitUtils.setLSB(x, 1);
        Assert.assertEquals(1, BitUtils.getLSB(x));
        Assert.assertEquals(9, x);

        x = BitUtils.setLSB(x, 0);
        Assert.assertEquals(0, BitUtils.getLSB(x));
        Assert.assertEquals(8, x);
    }
}