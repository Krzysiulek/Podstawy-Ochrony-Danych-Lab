package BlockCiphers;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

public interface BlockCipher {
    byte[] encrypt(byte[] message) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException;
    byte[] decrypt(byte[] message) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException;
}
