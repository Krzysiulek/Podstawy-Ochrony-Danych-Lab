package ModernStegano;

import Steganografia.Picture;
import Steganografia.Pixel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ModernStegano {
    private Picture originalPicture;

    @Getter
    private Picture firstCrypted = new Picture();
    @Getter
    private Picture secCrypted = new Picture();

    public ModernStegano(Picture picture) throws Exception {
        originalPicture = picture;
        int width = picture.getWidth() * 2;
        int height = picture.getHeight() * 2;

        Pixel[][] pix1 = new Pixel[height][width];
        Pixel[][] pix2 = new Pixel[height][width];

        firstCrypted.setHeight(height);
        firstCrypted.setWidth(width);
        firstCrypted.setImageArray(pix1);

        secCrypted.setHeight(height);
        secCrypted.setWidth(width);
        secCrypted.setImageArray(pix2);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int x = (int) (Math.random() * 255);

                if (x > 120)
                    x = 0;
                else
                    x = 255;

                firstCrypted.getImageArray()[i][j] = new Pixel(255, 255, 255, 255);
                secCrypted.getImageArray()[i][j] = new Pixel(0, 0, 0, 0);

            }
        }
    }

    public void generatePics() throws Exception {
        for (int i = 0; i < originalPicture.getHeight(); i++){
            for (int j = 0; j < originalPicture.getWidth(); j++) {
                int orgPixel = originalPicture.getImageArray()[i][j].getBlue();

                if (orgPixel == 255) {
                    firstCrypted.getImageArray()[i*2][j*2] = getRandPixels255()[0];
                    firstCrypted.getImageArray()[i*2][j*2 + 1] = getRandPixels255()[1];
                    firstCrypted.getImageArray()[i*2 + 1][j*2] = getRandPixels255()[2];
                    firstCrypted.getImageArray()[i*2+1][j*2+1] = getRandPixels255()[3];

                    secCrypted.getImageArray()[i*2][j*2] = getRandPixels255()[4];
                    secCrypted.getImageArray()[i*2][j*2 + 1] = getRandPixels255()[5];
                    secCrypted.getImageArray()[i*2 + 1][j*2] = getRandPixels255()[6];
                    secCrypted.getImageArray()[i*2+1][j*2+1] = getRandPixels255()[7];
                }
                else if (orgPixel == 0) {
                    firstCrypted.getImageArray()[i*2][j*2] = getRandPixels0()[0];
                    firstCrypted.getImageArray()[i*2][j*2 + 1] = getRandPixels0()[1];
                    firstCrypted.getImageArray()[i*2 + 1][j*2] = getRandPixels0()[2];
                    firstCrypted.getImageArray()[i*2+1][j*2+1] = getRandPixels0()[3];

                    secCrypted.getImageArray()[i*2][j*2] = getRandPixels0()[4];
                    secCrypted.getImageArray()[i*2][j*2 + 1] = getRandPixels0()[5];
                    secCrypted.getImageArray()[i*2 + 1][j*2] = getRandPixels0()[6];
                    secCrypted.getImageArray()[i*2+1][j*2+1] = getRandPixels0()[7];
                }
                else {
                    System.out.println(orgPixel);
                    throw new Exception("Unexpected pixel value");
                }

            }

            firstCrypted.savePicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/xd_2.png");
            secCrypted.savePicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/xd_3.png");
        }
    }

    public void degeneratePicture(Picture p1, Picture p2) throws Exception {
        Picture result = new Picture();
        int width = p1.getWidth();
        int height = p1.getHeight();
        Pixel[][] pix2 = new Pixel[height][width];

        result.setHeight(height);
        result.setWidth(width);
        result.setImageArray(pix2);

        for (int i = 0; i < p1.getHeight(); i+=2) {
            for (int j = 0; j < p1.getWidth(); j+=2) {
                int x = getSum(i, j, p1, p2);

                result.getImageArray()[i][j] = new Pixel(x, x, x, x);
                result.getImageArray()[i+1][j] = new Pixel(x, x, x, x);
                result.getImageArray()[i][j+1] = new Pixel(x, x, x, x);
                result.getImageArray()[i+1][j+1] = new Pixel(x, x, x, x);
            }
        }

        result.savePicture("/Users/krzysztofczarnecki/Documents/GitHub/POD/src/main/resources/xd_4.png");
    }

    public int getSum(int i, int j, Picture p1, Picture p2) {
        int pix1 = p1.getImageArray()[i][j].getBlue();
        int pix2 = p1.getImageArray()[i+1][j].getBlue();
        int pix3 = p1.getImageArray()[i][j+1].getBlue();
        int pix4 = p1.getImageArray()[i+1][j+1].getBlue();

        int pix11 = p2.getImageArray()[i][j].getBlue();
        int pix22 = p2.getImageArray()[i+1][j].getBlue();
        int pix33 = p2.getImageArray()[i][j+1].getBlue();
        int pix44 = p2.getImageArray()[i+1][j+1].getBlue();

        if (pix1 + pix11 == 255 && pix2 + pix22 == 255 && pix3 + pix33 == 255 & pix4 + pix44 == 255) {
            return 255;
        }
        else return 0;
    }

    private Pixel[] getRandPixels255() {
        List<Pixel[]> list = new ArrayList<>();
        int z = 0;
        int f = 255;

        list.add(new Pixel[] {
                new Pixel(f, f, f, f),
                new Pixel(f, f, f, f),
                new Pixel(z, z, z, z),
                new Pixel(z,z,z,z),

                new Pixel(z,z,z,z),
                new Pixel(z,z,z,z),
                new Pixel(f, f, f, f),
                new Pixel(f, f, f, f),
        });

        int ind = (int) (Math.random() * list.size());
        return list.get(ind);
    }

    private Pixel[] getRandPixels0() {
        List<Pixel[]> list = new ArrayList<>();
        int z = 0;
        int f = 255;

        list.add(new Pixel[] {
                new Pixel(f, f, f, f),
                new Pixel(f, f, f, f),
                new Pixel(z,z,z,z),
                new Pixel(f, f, f, f),

                new Pixel(z,z,z,z),
                new Pixel(f, f, f, f),
                new Pixel(z, z, z, z),
                new Pixel(z,z,z,z),
        });


        int ind = (int) (Math.random() * list.size());
        return list.get(ind);
    }
}
