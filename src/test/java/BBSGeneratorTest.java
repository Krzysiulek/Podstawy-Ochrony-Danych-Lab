import BBSGenerator.BBSGenerator;
import Utils.BBSGeneratorTime;
import Utils.TimeCounter;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BBSGeneratorTest {
    private static List<Long> list;

    @BeforeClass
    public static void init() {
        list = new BBSGenerator()
                .getBytesList(20000);

        System.out.println(list);
    }

    @Test
    @Ignore
    public void timeTest() {
        BBSGenerator generator = new BBSGenerator();
        TimeCounter timer = new TimeCounter();
        List<BBSGeneratorTime> generatorTimes = new ArrayList<>();
        String myString = "amount;timeMilis\n";

        final int max = 50000;
        final int numberOfIterations = 10000;

        for (int i = 0; i < max; i += max / numberOfIterations) {
            System.out.println(i*1.0/max * 100.0);
            timer.start();
            generator.getBytesList(i);
            timer.stop();

            generatorTimes.add(new BBSGeneratorTime(i, timer.getTimeMilis()));
            myString += i + ";" + timer.getTimeMilis() + "\n";
        }

        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        generatorTimes
                .forEach(o -> System.out.println(o.getAmountOfBytes() + ";" + o.getTimeMilis()));
    }

    @Test
    public void singleBytesTest() {
        long amountOfOne = list
                .stream()
                .filter(num -> num == 1)
                .count();

        Assert.assertTrue(amountOfOne > 9725 && amountOfOne < 10275);
    }

    @Test
    public void seriesTest_1() {
        int seriesAmount = getAmountOfSeries(1);
        System.out.println("Series of 1: " + seriesAmount);
        Assert.assertTrue(seriesAmount >= 2315 && seriesAmount <= 2685);
    }

    @Test
    public void seriesTest_2() {
        int seriesAmount = getAmountOfSeries(2);
        System.out.println("Series of 2: " + seriesAmount);
        Assert.assertTrue(seriesAmount >= 1114 && seriesAmount <= 1386);
    }

    @Test
    public void seriesTest_3() {
        int seriesAmount = getAmountOfSeries(3);
        System.out.println("Series of 3: " + seriesAmount);
        Assert.assertTrue(seriesAmount >= 527 && seriesAmount <= 723);
    }

    @Test
    public void seriesTest_4() {
        int seriesAmount = getAmountOfSeries(4);
        System.out.println("Series of 4: " + seriesAmount);
        Assert.assertTrue(seriesAmount >= 240 && seriesAmount <= 384);
    }

    @Test
    public void seriesTest_5() {
        int seriesAmount = getAmountOfSeries(5);
        System.out.println("Series of 5: " + seriesAmount);
        Assert.assertTrue(seriesAmount >= 103 && seriesAmount <= 209);
    }

    @Test
    public void seriesTest_6_andMore() {
        int seriesAmount = 0;

        for (int i = 6; i < 26; i++) {
            seriesAmount += getAmountOfSeries(i);
        }

        System.out.println("Series of 6 and more: " + seriesAmount);
        Assert.assertTrue(seriesAmount >= 103 && seriesAmount <= 209);
    }

    @Test
    public void longSeriesTest() {
        int counter = 0;

        for (Long aLong : list) {
            if (counter >= 26) {
                Assert.fail("Counter reached 26 '1'");
            }

            if (aLong == 1) {
                counter++;
            } else {
                counter = 0;
            }
        }
    }

    @Test
    public void pokerTest() {
        Map<String, Integer> counter = new HashMap<>();

        for (int i = 0; i < 20000; i += 4) {
            String numberDec = "";
            numberDec += list.get(i);
            numberDec += list.get(i + 1);
            numberDec += list.get(i + 2);
            numberDec += list.get(i + 3);

            if (counter.containsKey(numberDec)) {
                int keyRepetitions = counter.get(numberDec);
                counter.put(numberDec, ++keyRepetitions);
            } else {
                counter.put(numberDec, 1);
            }
        }

        double squareSumOfValues = counter.values()
                .stream()
                .map(integer -> integer * integer)
                .reduce(0, Integer::sum);

        double x = squareSumOfValues
                * (16.0 / 5000.0) - 5000;

        Assert.assertTrue(x > 2.16 && x < 46.17);
    }

    private int getAmountOfSeries(int seriesAmount) {
        int amountOf = 0;

        StringBuilder pattern = new StringBuilder("0");
        for (int i = 0; i < seriesAmount; i++) {
            pattern.append("1");
        }
        pattern.append("0");

        Pattern p = Pattern.compile(pattern.toString());
        String bitString = getStringOfList();

        for (int i = 0; i < (bitString.length() - pattern.length()); i++) {
            Matcher matcher = p.matcher(bitString.substring(i, (i + (pattern.length()))));
            if (matcher.matches()) {
                amountOf++;
            }
        }

        return amountOf;
    }

    private String getStringOfList() {
        StringBuilder builder = new StringBuilder();
        String listInString;

        list.forEach(builder::append);

        listInString = builder.toString();
        return listInString;
    }
}