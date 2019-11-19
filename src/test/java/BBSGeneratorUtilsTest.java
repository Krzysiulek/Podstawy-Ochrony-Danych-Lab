import BBSGenerator.MathUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BBSGeneratorUtilsTest {

    @Test
    public void congurentTest() {
        Assert.assertTrue(MathUtils.isCongruent(3, 24, 7));
        Assert.assertTrue(MathUtils.isCongruent(-15, -64, 7));
        Assert.assertTrue(MathUtils.isCongruent(-31, 11, 7));
        Assert.assertFalse(MathUtils.isCongruent(25, 12, 7));
    }

    @Test
    public void getPrimeNumbersTest() {
        List<Integer> primeNumbers = MathUtils.getListOfPrimeNumbers(10000000);
    }
}