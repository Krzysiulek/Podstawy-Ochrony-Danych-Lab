package BlockCiphers;

import Utils.XorUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class CBCTest {

    @Test
    public void ownCBCTest() throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        String message = "mnbvcxzasdfghjklbdghfjkirsdfkxcb";
        byte[] byteMessage = message.getBytes();

        System.out.println("Message to encrypt");
        System.out.println(Arrays.toString(byteMessage));
        System.out.println();

        CBC cbc = new CBC();
        byte[] enc = cbc.encrypt(byteMessage);
        System.out.println("Encrypted:");
        System.out.println(Arrays.toString(enc));

        cbc = new CBC();
        byte[] dec = cbc.decrypt(enc);
        System.out.println("Decrypted");
        System.out.println(Arrays.toString(dec));
    }

    @Test
    public void getterTest() throws NoSuchPaddingException, NoSuchAlgorithmException {
        CBC cbc = new CBC();
        String message = "mnbvcxzasdfghjklbdghfjkirsdfkxcb";
        byte[] byteArray = message.getBytes();
        System.out.println(Arrays.toString(byteArray));

        byte[] tmp = cbc.getByteArray(cbc.getByteList(byteArray));
        System.out.println(Arrays.toString(tmp));
    }
}