package Shamir;

import lombok.Getter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shamir {
    private BigInteger n;
    private BigInteger s = BigInteger.valueOf(1234);
    private BigInteger t;
    private BigInteger p;
    private BigInteger tMinusOne;

    private BigInteger[] randomTNums;
    private Random random = new Random();

    @Getter
    private List<SharePoint> sharePoints = new ArrayList<>();

    public Shamir(int n, int t) {
        this.n = BigInteger.valueOf(n);
        this.t = BigInteger.valueOf(t);
        tMinusOne = this.t.subtract(BigInteger.ONE);
        randomTNums = new BigInteger[t - 1];
        initP();

        /** Inits a nums. */
        for (int i = 0; i < t - 1; i++) {
            BigInteger a = new BigInteger(5, random);
            randomTNums[i] = a;
        }

        /** Init share points. */
        for (int i = 1; i <= n; i++) {
            sharePoints.add(new SharePoint(BigInteger.valueOf(i), resolveWielomian(i)));
        }
    }

    private BigInteger countSharePoints(BigInteger a, BigInteger n) {
        BigInteger sum = BigInteger.valueOf(0);

        for (int i = 1; i <= tMinusOne.intValue(); i++) {
            sum = sum
                    .add(a.multiply(n.mod(p)));
        }

        return s.add(sum.mod(p));
    }

    private void initP() {
        int bitLenght = 2;
        p = BigInteger.probablePrime(bitLenght, random);

        while (! (p.compareTo(s) > 0 && p.compareTo(this.n) > 0)) {
            bitLenght++;
            p = BigInteger.probablePrime(bitLenght, random);
        }
    }

    private BigInteger resolveWielomian(int x) {
        BigInteger sum = BigInteger.ZERO;

        for (int i = randomTNums.length; i >= 1; i--) {
            sum = sum.add(randomTNums[i - 1].multiply(BigInteger.valueOf(x).pow(i)));
        }

        sum = sum.add(s);
        sum = sum.mod(p);

        return sum;
    }

    public BigInteger getSecret(SharePoint... sharePoints) {
        BigInteger[] yili = new BigInteger[sharePoints.length];

        /** Count li. */
        for (int i = 0; i < sharePoints.length; i++) {
            yili[i] = getLi(BigInteger.valueOf(i), sharePoints).multiply(sharePoints[i].pointValue).mod(p);
        }

        BigInteger sum = BigInteger.ZERO;
        for (BigInteger s : yili) {
            sum = sum.add(s);
        }

        return sum;
    }

    private BigInteger getLi(BigInteger x, SharePoint...sharePoints) {
        ArrayList<BigInteger> sum = new ArrayList<>();

        for (int i = 0; i < sharePoints.length; i++) {
            if (x.equals(BigInteger.valueOf(i)))
                continue;

            BigInteger licznik1;
            BigInteger mianownik1;


            BigInteger tmpX = BigInteger.ZERO;
            licznik1 = tmpX.subtract(sharePoints[i].id);
            mianownik1 = sharePoints[x.intValue()].id.subtract(sharePoints[i].id);

            sum.add(licznik1.divide(mianownik1));
        }

        BigInteger li = BigInteger.ZERO;

        for (BigInteger l : sum) {
            if (li.equals(BigInteger.ZERO))
                li = li.add(l);
            else
                li = li.multiply(l);
        }

        return li;
    }
}
