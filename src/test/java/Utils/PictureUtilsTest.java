package Utils;

import Steganografia.Picture;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class PictureUtilsTest {

    @Test
    public void saveItd() throws IOException {
        Picture picture = new Picture();
        picture.loadPicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/male_zdjecie.jpg");

        picture.savePicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/crated.jpg");
    }


    @Test
    public void xd() {
        Integer p = -7954525;

        int a = (p>>24) & 0xff;
        int r = (p>>16) & 0xff;
        int g = (p>>8) & 0xff;
        int b = p & 0xff;
    }
}