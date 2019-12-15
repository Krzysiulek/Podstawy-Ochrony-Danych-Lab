package HashCodes;

import Utils.HashCodeUtils;
import Utils.TimeCounter;

import java.util.function.Supplier;

public class HashCode {

    public static void main(String args[]) {
        final String text = "xd";

        runner(() -> HashCodeUtils.getHashMD5(text), text, "MD-5");
        runner(() -> HashCodeUtils.getHashSHA1(text), text, "SHA-1");

        runner(() -> HashCodeUtils.getHashSHA224(text), text, "SHA2-224");
        runner(() -> HashCodeUtils.getHashSHA256(text), text, "SHA2-256");
        runner(() -> HashCodeUtils.getHashSHA384(text), text, "SHA2-384");
        runner(() -> HashCodeUtils.getHashSHA512(text), text, "SHA2-512");

        runner(() -> HashCodeUtils.getHashSHA3_224(text), text, "SHA3-224");
        runner(() -> HashCodeUtils.getHashSHA3_256(text), text, "SHA3-256");
        runner(() -> HashCodeUtils.getHashSHA3_384(text), text, "SHA3-384");
        runner(() -> HashCodeUtils.getHashSHA3_512(text), text, "SHA3-384");
    }

    private static void runner(Supplier runnable, String text, String method) {
        final double repeats = 10000000;
        String result = (String) runnable.get();
        System.out.println("Input:" + text);
        System.out.println(method + " : " + result);

        TimeCounter counter = new TimeCounter();

        counter.start();
        for (int i = 0; i < repeats; i++) {
            runnable.get();
        }
        counter.stop();
        System.out.println("Execution time: " + (counter.getTimeMilis() * 1.0 ) / repeats + " ms");

        System.out.println();
    }
}
