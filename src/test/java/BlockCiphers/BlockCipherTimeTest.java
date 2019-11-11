package BlockCiphers;

import Utils.TimeCounter;
import org.junit.Test;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class BlockCipherTimeTest {
    private

    @Test
    public void timeTestECB() throws NoSuchAlgorithmException, NoSuchPaddingException {
        ECB ecb = new ECB();
        countTime(() -> ecb.encrypt());
    }

    @Test
    public void timeTestCBC() {

    }

    @Test
    public void timeTestOFB() {

    }

    @Test
    public void timeTestCFB() {

    }

    @Test
    public void timeTestCTR() {

    }

    private long countTime(Runnable runnable) {
        TimeCounter counter = new TimeCounter();

        counter.start();
        runnable.run();
        counter.stop();

        return counter.getTimeMilis();
    }
}
