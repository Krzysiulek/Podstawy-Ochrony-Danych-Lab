package Utils;

import Steganografia.Picture;
import org.junit.Test;

public class PictureUtilsTest {

    @Test
    public void saveItd() throws Exception {
        Picture picture = new Picture();
        picture.loadPicture("/Users/krzysztofczarnecki/Desktop/bbb.jpg");

        picture.setAllLSBsToOne();
        picture.setStringIntoPicture("John Fitzgerald Kennedy, the 35th President of the United States, was assassinated on November 22, 1963, at 12:30 p.m. Central Standard Time in Dallas, Texas, while riding in a presidential motorcade through Dealey Plaza.[1] Kennedy was riding with his wife Jacqueline, Texas Governor John Connally, and Connally's wife Nellie when he was fatally shot by former U.S. Marine Lee Harvey Oswald firing in ambush from a nearby building. Governor Connally was seriously wounded in the attack. The motorcade rushed to Parkland Memorial Hospital where President Kennedy was pronounced dead about 30 minutes after the shooting; Connally recovered.");
        picture.savePicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/do_odszyfrowania.png");

        Picture createdPicture = new Picture();
        createdPicture.loadPicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/78674036_1213331508870050_5922948557622476800_n.png");
        String tmp = createdPicture.getStringFromPicture();

        System.out.println(tmp);
    }
}