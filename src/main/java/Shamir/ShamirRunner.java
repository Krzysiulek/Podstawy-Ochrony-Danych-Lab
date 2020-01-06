package Shamir;

import com.codahale.shamir.Scheme;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Map;

public class ShamirRunner {

    public static void main(String[] args) {
        final Scheme scheme = new Scheme(new SecureRandom(), 5, 2);
        final byte[] secret = "To jest jakiś tekst".getBytes(StandardCharsets.UTF_8);
        final Map<Integer, byte[]> parts = scheme.split(secret);
        final byte[] recovered = scheme.join(parts);
        System.out.println(new String(recovered, StandardCharsets.UTF_8));
    }
}
