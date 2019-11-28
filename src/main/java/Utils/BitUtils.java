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

    public static int getNthBit(int num, int position) {
        return (num >> position) & 1;
    }

    public static int setNthBit(int num, int pos, int val) {
        if (val == 1) {
            num = num | (1 << pos);
        }
        else if (val == 0) {
            num = num & ~(1 << pos);
        }

        return num;
    }

}
