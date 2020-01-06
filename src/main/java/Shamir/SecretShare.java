package Shamir;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@ToString
public final class SecretShare {
    private final int id;
    private final BigInteger value;
}