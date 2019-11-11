package BlockCiphers;

import Utils.TimeCounter;
import org.junit.Test;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.function.Supplier;

public class BlockCipherTimeTest {
    final int MB =  1048576;
    private final byte[] message_1MB = getRandomBytes(MB);
    private final byte[] message_10MB = getRandomBytes(10 * MB);
    private final byte[] message_50MB = getRandomBytes(50 * MB);
    private final byte[] message_100MB = getRandomBytes(100 * MB);
    private final byte[] message_200MB = getRandomBytes(200 * MB);

    @Test
    public void timeTestECB() throws NoSuchAlgorithmException, NoSuchPaddingException {
        testCipherTime(new ECB());
    }


    @Test
    public void timeTestCBC() throws NoSuchAlgorithmException, NoSuchPaddingException {
        testCipherTime(new CBC());
    }

    @Test
    public void timeTestOwnCBC() throws NoSuchPaddingException, NoSuchAlgorithmException {
        testCipherTime(new OwnCBC());
    }

    @Test
    public void timeTestOFB() throws NoSuchAlgorithmException, NoSuchPaddingException {
        testCipherTime(new OFB());
    }

    @Test
    public void timeTestCFB() throws NoSuchAlgorithmException, NoSuchPaddingException {
        testCipherTime(new CFB());
    }

    @Test
    public void timeTestCTR() throws NoSuchAlgorithmException, NoSuchPaddingException {
        testCipherTime(new CTR());
    }

    private long countTime(Supplier consumer, String message) {
        TimeCounter counter = new TimeCounter();

        try {
            counter.start();
            consumer.get();
            counter.stop();
        }
        catch (Exception e) {
            return 0;
        }

        System.out.println(message + ": " + counter.getTimeMilis() + "ms");
        return counter.getTimeMilis();
    }

    private byte[] getRandomBytes(int size) {
        byte[] randomByteArray = new byte[size];
        new Random().nextBytes(randomByteArray);

        return randomByteArray;
    }

    private void testCipherTime(MyCipher myCipher) {
        final String cipherName = myCipher.getClass().getName();

        System.out.println("Encrypt");
        countTime(() -> myCipher.encryptHandleExceptions(message_1MB), cipherName + " 1MB");
        countTime(() -> myCipher.encryptHandleExceptions(message_10MB), cipherName + " 10MB");
        countTime(() -> myCipher.encryptHandleExceptions(message_50MB), cipherName + " 50MB");
        countTime(() -> myCipher.encryptHandleExceptions(message_100MB), cipherName + " 100MB");
        countTime(() -> myCipher.encryptHandleExceptions(message_200MB), cipherName + " 200MB");

        System.out.println("\nDecrypt");
        countTime(() -> myCipher.decryptHandleExceptions(message_1MB), cipherName + " 1MB");
        countTime(() -> myCipher.decryptHandleExceptions(message_10MB), cipherName + " 10MB");
        countTime(() -> myCipher.decryptHandleExceptions(message_50MB), cipherName + " 50MB");
        countTime(() -> myCipher.decryptHandleExceptions(message_100MB), cipherName + " 100MB");
        countTime(() -> myCipher.decryptHandleExceptions(message_200MB), cipherName + " 200MB");
        System.out.println("##################\n\n");
    }
}
