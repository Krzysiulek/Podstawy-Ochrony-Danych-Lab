package RSA;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class PrivateKey {
    private BigInteger d;
    private BigInteger n;
}
