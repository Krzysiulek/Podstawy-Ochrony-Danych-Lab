package Encryptors;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String args[]) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Scanner scanner = new Scanner(System.in);
        String input;
        VigenereCipherEncryptor encryptor;
        String message;
        String key;
        String outputMessage = "";

        boolean dec;
        boolean files;
        boolean personalKey;
        boolean copy;
        boolean outputFile;

        System.out.println("\nHello. Type:\n" +
                "\t* enc: to encrypt\n" +
                "\t* dec: to decrypt.\n" +
                "Available params:\n" +
                "\t* -k: to use personal key\n" +
                "\t* -f <file_path>: to use file mode\n" +
                "\t* -c: to copy output to clipboard\n" +
//                "\t* -o <file_path>: to save output to file\n" +
                "Example use:\n" +
                "enc, enc -f /Users/username/Desktop/, enc -k yourKey\n" +
                "You can use all of those params together");

        while(true) {
            try
            {
                System.out.println("Type what you want to use this :)\n");

                input = scanner.nextLine();

                dec = isDec(input);
                files = isFile(input);
                personalKey = isPersonalKey(input);
                copy = isCopy(input);
                outputFile = isOutputFile(input);

                message = getMessage(input);

                if (personalKey) {
                    key = getPersonalKey(input);
                    encryptor = new VigenereCipherEncryptor(message, key);
                }
                else {
                    encryptor = new VigenereCipherEncryptor(message);
                }


                if (dec)
                {
                    outputMessage = encryptor.decrypt();
                }
                else
                {
                    outputMessage = encryptor.encrypt();
                }


                System.out.println("####################");
                System.out.println(outputMessage);

                saveToFile(outputMessage);
                copyMessage(input, outputMessage);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Try again");
            }
        }
    }


    private static boolean isDec(String line) throws Exception {
        if (line.contains("dec"))
            return true;
        if (line.contains("enc"))
            return false;
        else
            throw new Exception("Encryption type noc found");
    }

    private static boolean isFile(String line) {
        return line.contains("-f");
    }

    private static boolean isPersonalKey(String line) {
        return line.contains("-k");
    }

    private static boolean isCopy(String line) {
        return line.contains("-c");
    }

    private static boolean isOutputFile(String line) {
        return line.contains("-o");
    }

    public static String getPersonalKey(String line) throws Exception {
        Matcher m = Pattern.compile("-k\\s\\w+").matcher(line);

        if (m.find()) {
            return m.group(0).replace("-k", "").replace(" ", "");
        }
        else
            throw new Exception("Personal Key not found");
    }

    private static String getPath(String line) throws Exception {
        Matcher m = Pattern.compile("-f\\s\\S+").matcher(line);

        if (m.find())
        {
            return m.group(0)
                    .replace("-f ", "");
        }

        throw new Exception("Path not found");
    }

    public static String getMessage(String line) throws Exception {
        if (!isFile(line))
        {
            System.out.println("Write your message here:");
            return new Scanner(System.in)
                    .nextLine();
        }
        else
        {
            return Files
                    .lines(Paths.get(getPath(line)))
                    .collect(Collectors.joining());
        }
    }

    public static void saveToFile(String outputMessage) {

    }

    private static void copyMessage(String input, String outputMessage) {
        if (isCopy(input)) {
            StringSelection stringSelection = new StringSelection(outputMessage);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            System.out.println("Message copied to clipboard");
        }
    }
}
