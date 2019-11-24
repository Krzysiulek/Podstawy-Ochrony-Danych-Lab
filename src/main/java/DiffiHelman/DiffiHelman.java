package DiffiHelman;

import lombok.Getter;

import java.math.BigInteger;
import java.util.Random;

public class DiffiHelman {
    @Getter
    private String name;
    private final int BIT_LENGTH = 128;

    private BigInteger n;
    private BigInteger g;

    @Getter
    private BigInteger publicX;
    private BigInteger publicY;

    private BigInteger secretX;

    @Getter
    private BigInteger sessionKeyK;

    public DiffiHelman(String name, BigInteger n, BigInteger g) {
        this.name = name;
        this.n = n;
        this.g = g;
    }

    public void initX() {
        System.out.println(name + " is initialising secret x");
        this.secretX = new BigInteger(BIT_LENGTH, new Random());

        System.out.println(name + " is counting public X");
        this.publicX = g.modPow(secretX, n);
    }

    public void countK(BigInteger publicY) {
        this.publicY = publicY;

        this.sessionKeyK = publicY.modPow(secretX, n);
    }
}
