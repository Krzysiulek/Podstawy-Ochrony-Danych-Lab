package Utils;

import Steganografia.Picture;
import com.google.common.base.Utf8;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class PictureUtilsTest {

    @Test
    public void saveItd() throws Exception {
        Picture picture = new Picture();
        picture.loadPicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/male_zdjecie.jpg");

        picture.setAllLSBsToOne();
        picture.setStringIntoPicture("to jest zakodowany tekst");
        String xd = picture.getStringFromPicture();

        picture.savePicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/male_zdjecie22.png");
        System.out.println(xd);

//        System.out.println(Arrays.deepToString(picture.getImageArray()));

        Picture createdPicture = new Picture();
        createdPicture.loadPicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/male_zdjecie22.png");
        String tmp = createdPicture.getStringFromPicture();

        System.out.println(tmp);
    }

    @Test
    public void xd() {

    }
}