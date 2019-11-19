package RSA;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class PublicKey {
    private BigInteger e;
    private BigInteger n;
}
