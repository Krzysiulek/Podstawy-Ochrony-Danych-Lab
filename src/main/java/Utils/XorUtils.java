package Utils;

public class XorUtils {

    public static byte[] xorBytesArray(byte[] m1, byte[] m2) throws IndexOutOfBoundsException {
        if (m1.length != m2.length)
            throw new IndexOutOfBoundsException("Arrays are not the same length");

        byte[] toReturn = new byte[m1.length];

        for (int i = 0; i < m1.length; i++) {
            // convert to ints and xor
            int one = (int)m1[i];
            int two = (int)m2[i];
            int xor = one ^ two;

            // convert back to byte
            byte b = (byte)(xor);
            toReturn[i] = b;
        }

        return toReturn;
    }
}
