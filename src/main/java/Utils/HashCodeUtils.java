package Utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCodeUtils {

    public static String getHashMD5(String text) {
        return DigestUtils
                .md5Hex(text)
                .toUpperCase();
    }

    public static String getHashSHA1(String text) {
        return DigestUtils
                .sha1Hex(text)
                .toUpperCase();
    }

    public static String getHashSHA224(String text) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-224");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] messageDigest = md.digest(text.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);

        // Add preceding 0s to make it 32 bit
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        return hashtext
                .toUpperCase();
    }

    public static String getHashSHA256(String text) {
        return DigestUtils
                .sha256Hex(text)
                .toUpperCase();
    }

    public static String getHashSHA384(String text) {
        return DigestUtils
                .sha384Hex(text)
                .toUpperCase();
    }

    public static String getHashSHA512(String text) {
        return DigestUtils
                .sha512Hex(text)
                .toUpperCase();
    }

    public static String getHashSHA3_224(String text) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest224();
        byte[] digest = digestSHA3.digest(text.getBytes());

        return Hex.toHexString(digest);
    }

    public static String getHashSHA3_256(String text) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
        byte[] digest = digestSHA3.digest(text.getBytes());

        return Hex.toHexString(digest);
    }

    public static String getHashSHA3_384(String text) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest384();
        byte[] digest = digestSHA3.digest(text.getBytes());

        return Hex.toHexString(digest);
    }

    public static String getHashSHA3_512(String text) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] digest = digestSHA3.digest(text.getBytes());

        return Hex.toHexString(digest);
    }

}
