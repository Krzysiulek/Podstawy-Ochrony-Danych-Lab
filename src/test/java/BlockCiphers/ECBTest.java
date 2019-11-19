package BlockCiphers;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ECBTest {

    @Test
    public void encryptionTest() throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        String textToEncrypt = "abcdefghijklmnopabcdefghijklmnop";
        byte[] encrypted;
        byte[] decrypted;

        ECB ecb = new ECB();
        encrypted = ecb.encrypt(textToEncrypt.getBytes());
        decrypted = ecb.decrypt(encrypted);

        System.out.println("Encrypted:");
        System.out.println(Arrays.toString(encrypted));

        System.out.println("Decrypted:");
        System.out.println(Arrays.toString(decrypted));
        System.out.println(new String(decrypted));

        Assert.assertEquals(Arrays.toString(textToEncrypt.getBytes()), Arrays.toString(decrypted));
    }

}