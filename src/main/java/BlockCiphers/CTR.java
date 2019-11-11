package BlockCiphers;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CTR extends MyCipher {
    SecretKeySpec secretKey;

    public CTR() throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.cipherInstance = "AES/OFB/NoPadding";
        this.key = "klucz--128-bitow";
        this.secretKey = new SecretKeySpec(key.getBytes(), "AES");

        this.myCipher = Cipher.getInstance(cipherInstance);
    }

    @Override
    public byte[] encrypt(byte[] message) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        myCipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
        return myCipher.doFinal(message);
    }

    @Override
    public byte[] decrypt(byte[] message) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        myCipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
        return myCipher.doFinal(message);
    }
}
