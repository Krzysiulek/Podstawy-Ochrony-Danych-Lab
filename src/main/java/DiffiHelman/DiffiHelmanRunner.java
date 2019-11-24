package DiffiHelman;

import Utils.DiffiHelmanUtils;

import java.math.BigInteger;

public class DiffiHelmanRunner {
    public static void main(String args[]) {
        BigInteger n = DiffiHelmanUtils.getN();
        BigInteger g = DiffiHelmanUtils.getG(n);

        BigInteger X;
        BigInteger Y;

        DiffiHelman person1 = new DiffiHelman("A", n, g);
        DiffiHelman person2 = new DiffiHelman("B", n, g);

        person1.initX();
        person2.initX();

        X = person1.getPublicX();
        Y = person2.getPublicX();

        System.out.println("X is: " + X);
        System.out.println("Y is: " + Y);

        person1.countK(Y);
        person2.countK(X);

        System.out.println(person1.getName() + " session key is: "+ person1.getSessionKeyK());
        System.out.println(person2.getName() + " session key is: "+ person2.getSessionKeyK());
    }
}
