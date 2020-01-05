package HashCodes;

import Utils.HashCodeUtils;
import Utils.StringUtils;
import Utils.TimeCounter;

import java.util.function.Supplier;

public class HashCode {

    public static void main(String args[]) {
        for (int i = 10000; i < 1000000; i += (1000000 - 10000) / 9) {
            final String text = StringUtils.getRandomString(i);
            tmp(text);
            System.out.println();
        }


    }

    private static void printInfo(Supplier runnable, String text, String method) {
        final double repeats = 500;
        String result = (String) runnable.get();
        System.out.println(method + " : " + result);

        TimeCounter counter = new TimeCounter();

        counter.start();
        for (int i = 0; i < repeats; i++) {
            runnable.get();
        }
        counter.stop();
        System.out.println("Execution time: " + (counter.getTimeMilis() * 1.0 ) / repeats + " ms");
        String tmp = String.valueOf((counter.getTimeMilis() * 1.0 ) / repeats);
        tmp = tmp.replace(".", ",");
        System.out.println(tmp);

        System.out.println();
    }

    private static void tmp(String text) {
        System.out.println("Input:" + text);
        System.out.println("Input length:" + text.length());
        System.out.println(text.length());

        printInfo(() -> HashCodeUtils.getHashMD5(text), text, "MD-5");
        printInfo(() -> HashCodeUtils.getHashSHA1(text), text, "SHA-1");

        printInfo(() -> HashCodeUtils.getHashSHA224(text), text, "SHA2-224");
        printInfo(() -> HashCodeUtils.getHashSHA256(text), text, "SHA2-256");
        printInfo(() -> HashCodeUtils.getHashSHA384(text), text, "SHA2-384");
        printInfo(() -> HashCodeUtils.getHashSHA512(text), text, "SHA2-512");

        printInfo(() -> HashCodeUtils.getHashSHA3_224(text), text, "SHA3-224");
        printInfo(() -> HashCodeUtils.getHashSHA3_256(text), text, "SHA3-256");
        printInfo(() -> HashCodeUtils.getHashSHA3_384(text), text, "SHA3-384");
        printInfo(() -> HashCodeUtils.getHashSHA3_512(text), text, "SHA3-512");
    }
}
