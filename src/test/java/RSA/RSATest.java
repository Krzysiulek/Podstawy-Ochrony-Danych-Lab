package RSA;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class RSATest {

    @Test
    public void isRSAWorking() {
        RSA rsa = new RSA();
        final String message = "To jest bardzo wazna wiadomosc, ale to bardzo wazna wiadomosc";
        PrivateKey privateKey = rsa.getPrivateKey();
        PublicKey publicKey = rsa.getPublicKey();

        byte[] encrypted = RSA.encrypt(message.getBytes(), publicKey);
        byte[] decrypted = RSA.decrypt(encrypted, privateKey);

        System.out.println(new String(decrypted));
        Assert.assertEquals(message, new String(decrypted));
    }

    @Test
    public void isRSAWorkingWithExcersiseExample() {
        String message = new String(new byte[] {8, 8});
        RSA rsa = new RSA("31", "19");
        final PrivateKey privateKey = rsa.getPrivateKey();
        final PublicKey publicKey = rsa.getPublicKey();

//        List<BigInteger> encrypted = RSA.encrypt(message, publicKey);
////        Assert.assertEquals(new BigInteger("312"), encrypted.get(0));
////        Assert.assertEquals(new BigInteger("312"), encrypted.get(1));
//
//        Assert.assertEquals(message, RSA.decrypt(encrypted, privateKey));
    }

    @Test
    public void excersises() {
        final long p = 8461;
        final long q = 9767;

        final String message = "To jest bardzo wazna wiadomosc, ale to bardzo wazna wiadomosc";
        RSA rsaToGenerate = new RSA(BigInteger.valueOf(p), BigInteger.valueOf(q));

        PrivateKey privateKey = rsaToGenerate.getPrivateKey();
        PublicKey publicKey = rsaToGenerate.getPublicKey();

        byte[] encrypted = RSA.encrypt(message.getBytes(), publicKey);
        System.out.println(new String(encrypted));

        byte[] decrypted = RSA.decrypt(encrypted, privateKey);
        System.out.println(new String(decrypted));

        System.out.println(Arrays.toString(message.getBytes()));
        System.out.println(Arrays.toString(decrypted));
        Assert.assertEquals(message, new String(decrypted));
    }

    @Test
    public void dupa() {
        final long p = 8461;
        final long q = 9767;

        final String message = "To jest bardzo wazna wiadomosc, ale to bardzo wazna wiadomosc";
        RSA rsaToGenerate = new RSA(BigInteger.valueOf(p), BigInteger.valueOf(q));

        PrivateKey privateKey = rsaToGenerate.getPrivateKey();
        PublicKey publicKey = rsaToGenerate.getPublicKey();

        List<byte[]> encryptByteByByte = RSA.encryptByteByByte(message.getBytes(), publicKey);

        byte[] decrypted = RSA.decryptByteByByte(encryptByteByByte, privateKey);
        System.out.println(new String(decrypted));
    }

    @Test
    public void encryptingAndDecryptingByBlocks() {
        final long p = 20549;
        final long q = 6619;

        final String message = "To jest bardzo wazna wiadomosc, ale to bardzo wazna wiadomosc";
        RSA rsaToGenerate = new RSA(BigInteger.valueOf(p), BigInteger.valueOf(q));

        PrivateKey privateKey = rsaToGenerate.getPrivateKey();
        PublicKey publicKey = rsaToGenerate.getPublicKey();

        List<byte[]> encrypted = RSA.encryptByBlocks(message.getBytes(), publicKey);

        byte[] decryted = RSA.decryptByBlocks(encrypted, privateKey);
        System.out.println(new String(decryted));
    }
}