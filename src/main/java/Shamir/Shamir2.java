package Shamir;

import lombok.Getter;

import java.math.BigInteger;
import java.util.Random;

public class Shamir2 {
    private static final int CERTAINTY = 50;
    private final int t;
    private final int n;
    private final Random random = new Random();

    @Getter
    private BigInteger p;

    public Shamir2(final int t, final int n) {
        this.t = t;
        this.n = n;
    }

    public SecretShare[] split(final BigInteger secret) {
        final int modLength = secret.bitLength() + 1;

        p = new BigInteger(modLength, CERTAINTY, random);
        final BigInteger[] coeff = new BigInteger[t - 1];

        System.out.println("Prime Number: " + p);

        for (int i = 0; i < t - 1; i++) {
            coeff[i] = initRandomAi(p);
            System.out.println("a" + (i + 1) + ": " + coeff[i]);
        }

        final SecretShare[] shares = new SecretShare[n];

        for (int i = 1; i <= n; i++) {
            BigInteger accum = secret;

            for (int j = 1; j < t; j++) {
                final BigInteger t1 = BigInteger.valueOf(i).modPow(BigInteger.valueOf(j), p);
                final BigInteger t2 = coeff[j - 1].multiply(t1).mod(p);

                accum = accum.add(t2).mod(p);
            }
            shares[i - 1] = new SecretShare(i - 1, accum);
            System.out.println(shares[i - 1]);
        }

        return shares;
    }

    public BigInteger combine(final SecretShare[] shares, final BigInteger primeNum) {
        BigInteger accum = BigInteger.ZERO;
        for (int i = 0; i < t; i++) {
            BigInteger num = BigInteger.ONE;
            BigInteger den = BigInteger.ONE;

            for (int j = 0; j < t; j++) {
                if (i != j) {
                    num = num.multiply(BigInteger.valueOf(-j - 1)).mod(primeNum);
                    den = den.multiply(BigInteger.valueOf(i - j)).mod(primeNum);
                }
            }

            final BigInteger value = shares[i].getValue();

            final BigInteger tmp = value.multiply(num).multiply(den.modInverse(primeNum)).mod(primeNum);
            accum = accum.add(primeNum).add(tmp).mod(primeNum);
        }

        System.out.println("The secret is: " + accum);

        return accum;
    }

    private BigInteger initRandomAi(final BigInteger p) {
        while (true) {
            final BigInteger r = new BigInteger(p.bitLength(), random);
            if (r.compareTo(BigInteger.ZERO) > 0 && r.compareTo(p) < 0) {
                return r;
            }
        }
    }
}
