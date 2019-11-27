package Utils;

import Steganografia.Picture;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class PictureUtilsTest {

    @Test
    public void saveItd() throws IOException {
        Picture picture = new Picture();
        picture.loadPicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/male_zdjecie.jpg");

        picture.savePicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/crated.jpg");

        Assert.assertEquals(picture.getImageArray()[0][0], picture.getNthPixel(1));
        Assert.assertEquals(picture.getImageArray()[0][9], picture.getNthPixel(10));
        Assert.assertEquals(picture.getImageArray()[2][0], picture.getNthPixel(31));
        Assert.assertEquals(picture.getImageArray()[9][9], picture.getNthPixel(100));
    }

}