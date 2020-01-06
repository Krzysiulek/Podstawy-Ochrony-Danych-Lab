package Shamir;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@AllArgsConstructor
@Data
public class SharePoint {
    BigInteger id;
    BigInteger pointValue;
}
