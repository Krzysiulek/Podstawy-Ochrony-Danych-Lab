package BBSGenerator;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class BBSGenerator {
    private long blumNumber;
    private List<Long> xList;
    private List<Long> uList;

    public BBSGenerator() {
        List<Integer> primeNumbers = BBSGeneratorUtils.getListOfPrimeNumbers(1000000);

        Long p = BBSGeneratorUtils
                .getCongurentPrimeNumbersInRange(primeNumbers);

        Long q = BBSGeneratorUtils
                .getCongurentPrimeNumbersInRange(primeNumbers);

        blumNumber = p * q;
    }

    public List<Long> getBytesList(long length) {
        xList = new ArrayList<>();
        uList = new ArrayList<>();
        initX();

        for (int i = 1; i < length; i++) {
            long xi = getXi(i);
            addX(xi);
        }

        return uList;
    }

    private void addX(long x) {
        x = abs(x);
        xList.add(x);
        uList.add(getLastBitFrom(x));
    }

    private long getXi(int i) {
        return (long) (pow(xList.get(i - 1), 2) % blumNumber);
    }

    private void initX() {
        long x;
        while (true) {
            long seed = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);

            if (BBSGeneratorUtils.NWD(seed, blumNumber) == 1)
            {
                x = seed;
                break;
            }
        }

        long xZero = x * x % blumNumber;
        addX(xZero);
    }

    private long getLastBitFrom(long num) {
        return num % 2;
    }
}
