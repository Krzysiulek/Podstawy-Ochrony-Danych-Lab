package BlockCiphers;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ECB extends MyCipher implements BlockCipher {
    SecretKeySpec secretKey;

    public ECB() throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.cipherInstance = "AES/ECB/NoPadding";
        this.key = "klucz--128-bitow";
        this.secretKey = new SecretKeySpec(key.getBytes(), "AES");

        this.myCipher = Cipher.getInstance(cipherInstance);
    }

    @Override
    public byte[] encrypt(byte[] message) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        myCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return myCipher.doFinal(message);
    }

    @Override
    public byte[] decrypt(byte[] message) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        myCipher.init(Cipher.DECRYPT_MODE, secretKey);
        return myCipher.doFinal(message);
    }
}
