import Encryptors.Main;
import Encryptors.VigenereCipherEncryptor;
import org.junit.Assert;
import org.junit.Test;

public class VigenereCipherEncryptorTest {

    @Test
    public void testInitialing() throws Exception {
        VigenereCipherEncryptor encryptor = new VigenereCipherEncryptor("To jest bardzo tajne", "mars");

        Assert.assertEquals("MARSMARSMARSMARSMARS", encryptor.getKey());
        Assert.assertEquals("TO JEST BARDZO TAJNE", encryptor.getMessage());
    }

    @Test
    public void testGettingNumber() throws Exception {
        VigenereCipherEncryptor encryptor = new VigenereCipherEncryptor("To jest bardzo tajne", "mars");

        Assert.assertEquals(0, encryptor.getNumberFromLetter('a'));
        Assert.assertEquals(26, encryptor.getNumberFromLetter('_'));
        Assert.assertEquals(1, encryptor.getNumberFromLetter('B'));
    }

    @Test
    public void testGettingLetter() throws Exception {
        VigenereCipherEncryptor encryptor = new VigenereCipherEncryptor("To jest bardzo tajne", "mars");

        Assert.assertEquals('A', encryptor.getLetterFromNumber(0));
        Assert.assertEquals('A', encryptor.getLetterFromNumber(27));
        Assert.assertEquals('_', encryptor.getLetterFromNumber(26));
        Assert.assertEquals('B', encryptor.getLetterFromNumber(1));
        Assert.assertEquals('B', encryptor.getLetterFromNumber(28));
    }

    @Test
    public void encryptTest() throws Exception {
        VigenereCipherEncryptor encryptor = new VigenereCipherEncryptor("JutroPodPomnikiem", "mars");
        Assert.assertEquals("VUJI_PEVAOCEUKZWY", encryptor.encrypt());
    }

    @Test
    public void encryptTes2() throws Exception {
        VigenereCipherEncryptor encryptor = new VigenereCipherEncryptor("TO JEST BARDZO TAJNY TEKST", "tajne");
        Assert.assertEquals("LO WIKT OEJDHA LAS_B TNXWL", encryptor.encrypt());
    }

    @Test
    public void encryptTes3() throws Exception {
        VigenereCipherEncryptor encryptor = new VigenereCipherEncryptor("Jutro Pod ..,!@#$%^&*()Pomnikiem", "mars");
        Assert.assertEquals("VUJI_ FFP ..,!@#$%^&*()G_MD_WIVD", encryptor.encrypt());
    }

    @Test
    public void decryptTest() throws Exception {
        VigenereCipherEncryptor encryptor = new VigenereCipherEncryptor("VUJI_PEVAOCEUKZWY", "mars");
        Assert.assertEquals("JUTROPODPOMNIKIEM", encryptor.decrypt());
    }

    @Test
    public void decryptTest2() throws Exception {
        VigenereCipherEncryptor encryptor = new VigenereCipherEncryptor("VUJI_ FFP ..,!@#$%^&*()G_MD_WIVD", "mars");
        Assert.assertEquals("JUTRO POD ..,!@#$%^&*()POMNIKIEM", encryptor.decrypt());
    }

    @Test
    public void getPersonalKeyTest() throws Exception {
        Assert.assertEquals("xdxdxd", Main.getPersonalKey("enc -k xdxdxd "));
    }
}
