package BlockCiphers;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public interface BlockCipher {
    byte[] encrypt(byte[] message) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException;
    byte[] decrypt(byte[] message) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException;
    byte[] encryptHandleExceptions(byte[] message);
    byte[] decryptHandleExceptions(byte[] message);
}
