package ModernStegano;

import Steganografia.Picture;
import Steganografia.Pixel;


public class RandomPictureCreator {

    public static void main(String args[]) throws Exception {
        Picture pic = new Picture();
        int width = 10;
        int height = 10;

        pic.setImageArray(new Pixel[height][width]);
        pic.setWidth(width);
        pic.setHeight(height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int x = (int) (Math.random() * 255);

                if (x > 120)
                    x = 0;
                else
                    x = 255;

                pic.getImageArray()[i][j] = new Pixel(x, x, x, x);
            }
        }

        pic.savePicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/xd.png");
    }
}
