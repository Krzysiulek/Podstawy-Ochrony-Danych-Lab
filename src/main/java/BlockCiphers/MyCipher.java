package BlockCiphers;

import lombok.Data;

import javax.crypto.Cipher;

@Data
public abstract class MyCipher {
    protected String message;
    protected String key;
    protected String cipherInstance;
    protected Cipher myCipher;
}
