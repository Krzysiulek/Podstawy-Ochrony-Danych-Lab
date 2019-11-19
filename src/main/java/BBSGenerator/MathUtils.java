package BBSGenerator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MathUtils {

    public static boolean isCongruent(long a, long b, long n) {
        return (a - b) % n == 0;
    }

    public static long getCongurentPrimeNumbersInRange(List<Integer> primeNumbers) {
        while (true) {
            int indexOfPrimeNumber = Math.abs(new Random().nextInt() % primeNumbers.size());
            int potentialPrimeCongurent = primeNumbers.get(indexOfPrimeNumber);

            if (isPrime(potentialPrimeCongurent) && isCongruent(potentialPrimeCongurent, 3, 4)) {
                return potentialPrimeCongurent;
            }
        }
    }

    public static boolean isPrime(long num) {
        boolean flag = false;
        if (num == 1) {
            return false;
        }

        for (int i = 2; i <= num / 2; ++i) {
            if (num % i == 0) {
                flag = true;
                break;
            }
        }

        return !flag;
    }

    public static long NWD(long firstNum, long secNum) {
        if (secNum == 0) {
            return firstNum;
        } else {
            return NWD(secNum, firstNum % secNum);
        }
    }

    public static List<Integer> getListOfPrimeNumbers(int maxPrimeNumber) {
        boolean prime[] = new boolean[maxPrimeNumber + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= maxPrimeNumber; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= maxPrimeNumber; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= maxPrimeNumber; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
}
