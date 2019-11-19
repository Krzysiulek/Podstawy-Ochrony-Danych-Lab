package RSA;
import lombok.ToString;

import java.math.BigInteger;
import java.util.Random;

@ToString
public class RSA {
    private final int BIT_LENGTH = 2056;
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private Random random = new Random();

    public RSA() {
        p = BigInteger.probablePrime(BIT_LENGTH / 4, random);
        q = BigInteger.probablePrime(BIT_LENGTH / 4, random);
        n = p.multiply(q);

        /** phi = (p - 1) * (q - 1). */
        phi = (p.subtract(BigInteger.ONE))
                .multiply(q.subtract(BigInteger.ONE));

        initE();
        initD();
    }

    public RSA(BigInteger pN, BigInteger qN) {
        p = pN;
        q = qN;
        n = p.multiply(q);

        /** phi = (p - 1) * (q - 1). */
        /* Step 3: Calculate ø(n) = (p - 1).(q - 1) */
        phi = p.subtract(BigInteger.valueOf(1));
        phi = phi.multiply(q.subtract(BigInteger.valueOf(1)));
        /* Step 4: Find e such that gcd(e, ø(n)) = 1 ; 1 < e < ø(n) */
        do {
            e = new BigInteger(2 * BIT_LENGTH, new Random());
        } while ((e.compareTo(phi) != 1)
                || (e.gcd(phi).compareTo(BigInteger.valueOf(1)) != 0));
        /* Step 5: Calculate d such that e.d = 1 (mod ø(n)) */
        d = e.modInverse(phi);
    }

    public RSA(String p, String q) {
        this.p = new BigInteger(p);
        this.q = new BigInteger(q);
        n = this.p.multiply(this.q);

        /** phi = (p - 1) * (q - 1). */
        phi = (this.p.subtract(BigInteger.ONE))
                .multiply(this.q.subtract(BigInteger.ONE));

        initE();
        initD();
    }

    public static byte[] encrypt(byte[] message, PublicKey key) {
        BigInteger integerToEncrypt = new BigInteger(message).modPow(key.getE(), key.getN());

        System.out.println(key.getN());
        System.out.println(integerToEncrypt);
        System.out.println(key.getN().compareTo(integerToEncrypt));

        return integerToEncrypt.toByteArray();
    }

    public static byte[] decrypt(byte[] message, PrivateKey key) {
        byte[] bytesToReturn = (new BigInteger(message)).modPow(key.getD(), key.getN()).toByteArray();

        return bytesToReturn;
    }

    private void initE() {
        e = BigInteger.probablePrime(BIT_LENGTH / 2, random);

        while (!(e.isProbablePrime(1) && (e.gcd(phi).equals(BigInteger.ONE)))) {
            e = BigInteger.probablePrime(BIT_LENGTH / 4, random);
        }
    }

    private void initD() {
        d = new BigInteger(BIT_LENGTH, random);

//        while (!e.multiply(d).subtract(BigInteger.ONE).mod(phi).equals(BigInteger.ZERO))
//            d = new BigInteger(BIT_LENGTH, random);

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi);
    }

    public PrivateKey getPrivateKey() {
        return new PrivateKey(d, n);
    }

    public PublicKey getPublicKey() {
        return new PublicKey(e, n);
    }
}
