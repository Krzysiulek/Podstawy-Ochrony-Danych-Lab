package Shamir;

import java.math.BigInteger;
import java.util.Arrays;

public class Shamir2Runner {
    public static void main(final String[] args) throws Exception {
        final int t = 2;
        final int n = 4;
        final int secretInt = 12345;

        final Shamir2 shamir = new Shamir2( t, n);

        final BigInteger secret = new BigInteger(String.valueOf(secretInt));
        final SecretShare[] shares = shamir.split(secret);
        final BigInteger prime = shamir.getP();

        System.out.println("\nCalculating secret from: ");

        final SecretShare[] TMPshares = new SecretShare[t];
        for (int i = 0; i < t; i++) {
            TMPshares[i] = shares[i];
        }

        System.out.println(Arrays.toString(TMPshares));
        final Shamir2 shamir2 = new Shamir2(t, n);
        final BigInteger result  = shamir2.combine(TMPshares, prime);

        if (!result.equals(secret))
            throw new Exception("Result not equals secret");
    }
}
