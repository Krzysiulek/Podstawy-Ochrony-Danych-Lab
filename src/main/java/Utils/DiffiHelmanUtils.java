package Utils;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class DiffiHelmanUtils {
    final static int N_BIT_LENGTH = 20;

    public static BigInteger getPublicX(BigInteger secretX, BigInteger n, BigInteger g) {
        return g.modPow(secretX, n);
    }

    public static BigInteger getK(BigInteger publicX, BigInteger secretX, BigInteger n) {
        return publicX.modPow(secretX, n);
    }

    public static BigInteger getN() {
        return BigInteger.probablePrime(N_BIT_LENGTH, new Random());
    }

    public static BigInteger getN(long n) {
        return new BigInteger(String.valueOf(n));
    }

    public static BigInteger getG(BigInteger n) {
        return findPrimitive(n);
    }

    private static BigInteger findPrimitive(BigInteger n) {
        HashSet<BigInteger> s = new HashSet<BigInteger>();

        BigInteger phi = n.subtract(BigInteger.ONE);
        findPrimefactors(s, phi);

        for (BigInteger bi = BigInteger.valueOf(2);
             bi.compareTo(phi) <= 0;
             bi = bi.add(BigInteger.ONE)) {
            boolean flag = false;
            for (BigInteger a : s) {
                if (power(bi, phi.divide(a), n).equals(BigInteger.ONE)) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                return bi;
            }

        }

        return new BigInteger("2");
    }

    private static void findPrimefactors(HashSet<BigInteger> s, BigInteger n) {
        while (Objects.equals(n.mod(BigInteger.valueOf(2)), BigInteger.ZERO)) {
            s.add(BigInteger.valueOf(2));
            n = n.divide(BigInteger.valueOf(2));
        }

        for (BigInteger bi = BigInteger.valueOf(3);
             bi.compareTo(n.sqrt()) <= 0;
             bi = bi.add(BigInteger.valueOf(2))) {

            while (Objects.equals(n.mod(bi), BigInteger.ZERO)) {
                s.add(bi);
                n = n.divide(bi);
            }
        }
        if (n.compareTo(BigInteger.valueOf(2)) > 0) {
            s.add(n);
        }

    }

    private static BigInteger power(BigInteger x, BigInteger y, BigInteger p) {
        BigInteger res = new BigInteger("1");
        x = x.mod(p);

        while (y.compareTo(BigInteger.ZERO) < 0) {
            // If y is odd, multiply x with result
            if (y.mod(BigInteger.valueOf(2)) == (BigInteger.ONE)) {
                res = (res.multiply(x)).mod(p);
            }

            // y must be even now
            y = y.shiftRight(1); // y = y/2
            x = (x.multiply(x)).mod(p);
        }
        return res;
    }
}
