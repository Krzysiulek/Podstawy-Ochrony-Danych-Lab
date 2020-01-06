package Shamir;

import java.math.BigInteger;

public class Shamir2Runner {
    public static void main(final String[] args) throws Exception {
        final Shamir2 shamir = new Shamir2( 2, 4);

        final BigInteger secret = new BigInteger("1234");
        final SecretShare[] shares = shamir.split(secret);
        final BigInteger prime = shamir.getPrime();

        final Shamir2 shamir2 = new Shamir2( 2, 4);
        final BigInteger result  = shamir2.combine(shares, prime);

        if (!result.equals(secret))
            throw new Exception("Result not equals secret");
    }
}
