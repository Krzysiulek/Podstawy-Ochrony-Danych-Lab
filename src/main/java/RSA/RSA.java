package RSA;
import com.google.common.primitives.Bytes;
import com.sun.deploy.util.ArrayUtil;
import lombok.ToString;

import java.math.BigInteger;
import java.util.*;

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

//        System.out.println(key.getN());
//        System.out.println(new BigInteger(message));
//        System.out.println(key.getN().compareTo(new BigInteger(message)));

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

    public static List<byte[]> encryptByteByByte(byte[] message, PublicKey publicKey) {
        List<byte[]> tmp = new ArrayList<>();

        for (int i = 0; i < message.length; i++) {
            tmp.add(RSA.encrypt(new byte[]{message[i]}, publicKey));
        }

        return tmp;
    }

    public static List<byte[]> encryptByBlocks(byte[] message, PublicKey key) {
        int blockSize = 1;

        List<Byte> bytesToEncrypt = new ArrayList<>();
        List<byte[]> bytesBlocksToEncrypt = new ArrayList<>();
        List<byte[]> encrypted = new ArrayList<>();

        /** Converts byte[] to List<Byte>. */
        for (byte b : message){
            bytesToEncrypt.add(b);
        }

        /** Finds max block size. */
        for (int i = 1; i < message.length; i++) {
            byte[] bytesToCheckValue = Bytes.toArray(bytesToEncrypt.subList(0, i));

            if (key.getN().compareTo(new BigInteger(bytesToCheckValue)) < 1) {
                break;
            }

            blockSize = i;
        }

        /** Split message to blocks. */
        for (int i = 0; i < bytesToEncrypt.size(); i += blockSize) {
            byte[] tmp = new byte[blockSize];

            int ctr = 0;
            boolean tooLong = false;
            for (int j = 0; j < blockSize ; j++) {
                if (i + j < bytesToEncrypt.size()) {
                    tmp[j] = bytesToEncrypt.get(i + j);
                }
                else {
                    tooLong = true;
                    ctr++;
                }
            }

            if (tooLong) {
                byte[] tooLongTmpBytes = new byte[blockSize - ctr];

                for (int k = 0; k < blockSize - ctr; k++) {
                    tooLongTmpBytes[k] = tmp[k];

                }
                bytesBlocksToEncrypt.add(tooLongTmpBytes);
            }
            else {
                bytesBlocksToEncrypt.add(tmp);
            }
        }

        for (byte[] bytes : bytesBlocksToEncrypt) {
            encrypted.add(RSA.encrypt(bytes, key));
        }

        return encrypted;
    }

    public static byte[] decryptByBlocks(List<byte[]> message, PrivateKey key) {
        List<Byte> decryptedArray = new ArrayList<>();

        /** Decrypts. */
        for (int i = 0; i < message.size(); i++) {
            byte[] tmpDecrypted = RSA.decrypt(message.get(i), key);

            for (int j = 0; j < tmpDecrypted.length; j++) {
                decryptedArray.add(tmpDecrypted[j]);
            }
        }

        return Bytes.toArray(decryptedArray);
    }

    public static byte[] decryptByteByByte(List<byte[]> message, PrivateKey privateKey) {
        byte[] tmp = new byte[message.size()];

        for (int i = 0; i < message.size(); i++) {
            tmp[i] = RSA.decrypt(message.get(i), privateKey)[0];
        }

        return tmp;
    }

    private void initD() {
        d = new BigInteger(BIT_LENGTH, random);

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
