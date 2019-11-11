package Encryptors;

import lombok.Data;

import static java.lang.Math.abs;

@Data
public class VigenereCipherEncryptor {
    private String message;
    private String key;
    final String alphabet = "1234567890-=qwertyuiop[]asdfghjkl;'`zxcvbnm,./§£!@#$%^&*()_+QWERTYUIOP{}ASDFGHJKL:\"|~ZXCVBNM<>? ";

    public VigenereCipherEncryptor(String message) {
        this.key = "haslo";
        this.message = message;
        maximizeKey();
    }

    public VigenereCipherEncryptor(String message, String key) throws Exception {
        if (key.isEmpty()){
            throw new Exception("Key is empty!");
        }

        this.message = message;
        this.key = key;
        maximizeKey();
    }

    public String encrypt() throws Exception {
        String encryptedMessage = "";

        for (int i = 0; i < message.length(); i++) {
            if (alphabet.contains(String.valueOf(message.charAt(i)))) {
                int messageLetterNumber = getNumberFromLetter(message.charAt(i));
                int keyLetterNumber = getNumberFromLetter(key.charAt(i));

                encryptedMessage += String.valueOf(getLetterFromNumber(messageLetterNumber + keyLetterNumber));
            }
            else {
                encryptedMessage += String.valueOf(message.charAt(i));
            }
        }

        return encryptedMessage;
    }

    public String decrypt() throws Exception {
        String decryptedMessage = "";

        for (int i = 0; i < message.length(); i++) {
            if (alphabet.contains(String.valueOf(message.charAt(i)))) {
                int messageLetterNumber = getNumberFromLetter(message.charAt(i));
                int keyLetterNumber = getNumberFromLetter(key.charAt(i));

                if (messageLetterNumber >= keyLetterNumber) {
                    decryptedMessage += String.valueOf(getLetterFromNumber(messageLetterNumber - keyLetterNumber));
                }
                else {
                    int numberToDecrypt = alphabet.length() - abs(messageLetterNumber - keyLetterNumber) % alphabet.length();
                    decryptedMessage += String.valueOf(getLetterFromNumber(numberToDecrypt));
                }
            }
            else {
                decryptedMessage += String.valueOf(message.charAt(i));
            }
        }

        return decryptedMessage;
    }

    private void maximizeKey() {
        final String singleKey = this.key;

        while (key.length() < message.length()) {
            key += singleKey;
        }
    }

    public int getNumberFromLetter(char let) throws Exception {
        for (int i = 0; i < alphabet.length(); i++) {
            String letter = String.valueOf(let);

            if (letter.charAt(0) == (alphabet.charAt(i))) {
                return i;
            }
        }

        throw new Exception("Cannot get number from letter");
    }

    public char getLetterFromNumber(int number) {
        number = number % alphabet.length();
        return alphabet.charAt(number);
    }
}
