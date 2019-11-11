import BBSGenerator.BBSGeneratorUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BBSGeneratorUtilsTest {

    @Test
    public void congurentTest() {
        Assert.assertTrue(BBSGeneratorUtils.isCongruent(3, 24, 7));
        Assert.assertTrue(BBSGeneratorUtils.isCongruent(-15, -64, 7));
        Assert.assertTrue(BBSGeneratorUtils.isCongruent(-31, 11, 7));
        Assert.assertFalse(BBSGeneratorUtils.isCongruent(25, 12, 7));
    }

    @Test
    public void getPrimeNumbersTest() {
        List<Integer> primeNumbers = BBSGeneratorUtils.getListOfPrimeNumbers(10000000);
    }
}