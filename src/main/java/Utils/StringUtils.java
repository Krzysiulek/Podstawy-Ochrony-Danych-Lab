package Utils;

import java.util.Random;

public class StringUtils {

    public static String getRandomString(int n) {
        StringBuilder builder = new StringBuilder();
        String letters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM ";
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            builder.append(letters.charAt(random.nextInt(letters.length() - 1)));
        }

        return builder.toString();
    }
}
