package BlockCiphers;

import Utils.XorUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class OwnCBC extends MyCipher {
    private final byte[] iv = "FINALkeyToMyCiph".getBytes();
    private ECB ecb = new ECB();

    public OwnCBC() throws NoSuchAlgorithmException, NoSuchPaddingException {

    }

    @Override
    public byte[] encrypt(byte[] message) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        message = ecb.encrypt(message);
        List<byte[]> messageToEncrypt = getByteList(message);
        List<byte[]> encrypted = new ArrayList<>();

        encrypted.add(XorUtils.xorBytesArray(iv, messageToEncrypt.get(0)));

        for (int i = 1; i < messageToEncrypt.size(); i++) {
            encrypted.add(XorUtils.xorBytesArray(encrypted.get(i - 1), messageToEncrypt.get(i)));
        }

        return getByteArray(encrypted);
    }

    @Override
    public byte[] decrypt(byte[] message) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        List<byte[]> messageToDecrypt = getByteList(message);
        List<byte[]> decrypted = new ArrayList<>();

        decrypted.add(XorUtils.xorBytesArray(iv, messageToDecrypt.get(0)));

        for (int i = 0; i < messageToDecrypt.size() - 1; i++) {
            decrypted.add(XorUtils.xorBytesArray(messageToDecrypt.get(i), messageToDecrypt.get(i + 1)));
        }

        return ecb.decrypt(getByteArray(decrypted));
    }

    public List<byte[]> getByteList(byte[] message) {
        List<byte[]> byteList = new ArrayList<>();

        int ctr = 0;
        while (ctr < message.length) {
            byte[] byteArrayToAdd = new byte[16];
            for (int i = 0; i < 16; i++) {
                byteArrayToAdd[i] = message[ctr + i];
            }

            byteList.add(byteArrayToAdd);
            ctr += 16;
        }

        return byteList;
    }

    public byte[] getByteArray(List<byte[]> message) {
        byte[] byteArray = new byte[message.size() * 16];

        int ctr = 0;
        while (ctr < message.size() * 16) {
            for (int i = 0; i < 16; i++) {
                byteArray[ctr + i] = message.get(ctr / 16)[i];
            }

            ctr += 16;
        }

        return byteArray;
    }
}
