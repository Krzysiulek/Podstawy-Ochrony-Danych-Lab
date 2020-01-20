package ModernStegano;

import Steganografia.Picture;

public class ModernSteganoRunner {

    public static void main(String args[]) throws Exception {
        Picture picture = new Picture();
        picture.loadPicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/xd.png");

        ModernStegano modernStegano = new ModernStegano(picture);
        modernStegano.generatePics();

        modernStegano.degeneratePicture(modernStegano.getFirstCrypted(), modernStegano.getSecCrypted());
    }

}
