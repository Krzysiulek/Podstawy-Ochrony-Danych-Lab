package Shamir;

import lombok.Getter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shamir {
    private BigInteger n = BigInteger.valueOf(6);
    private BigInteger s = BigInteger.valueOf(1234);
    private BigInteger t = BigInteger.valueOf(3);
    private BigInteger p;
    private BigInteger tMinusOne;

    private BigInteger[] randomTNums;
    private Random random = new Random();

    @Getter
    private List<SharePoint> sharePoints = new ArrayList<>();

    public Shamir() {
        int bitLenght = 3;
        tMinusOne = t.subtract(BigInteger.ONE);
        randomTNums = new BigInteger[tMinusOne.intValue()];

        p = BigInteger.probablePrime(bitLenght, random);

        while (! (p.compareTo(s) > 0 && p.compareTo(n) > 0)) {
            bitLenght++;
            p = BigInteger.probablePrime(bitLenght, random);
        }

        for (int i = 0; i < tMinusOne.intValue(); i++) {
            BigInteger a = new BigInteger(bitLenght, random);
            randomTNums[i] = a;
            sharePoints.add(new SharePoint(i, countSharePoints(a, BigInteger.valueOf(i + 1))));
        }
    }

    private BigInteger countSharePoints(BigInteger a, BigInteger n) {
        BigInteger sum = BigInteger.valueOf(0);

        for (int i = 0; i < tMinusOne.intValue(); i++) {
            sum = sum
                    .add(a.multiply(n.mod(p)));
        }

        return s.add(sum.mod(p));
    }

    private BigInteger getSecret(SharePoint...points) {
     if (points.length < t.intValue())
         throw new RuntimeException("Not enough value of share points");

        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
