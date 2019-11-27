package Utils;

public class BitUtils {

    public static int setLSB(int number, int value) throws Exception {
        if (value == 1) {
            number |= 0b1;
        }
        else if (value == 0) {
            number &= ~0b1;
        }
        else {
            throw new Exception("Incorrect value");
        }

        return number;
    }

    public static int getLSB(int number) {
        return number % 2;
    }
}
