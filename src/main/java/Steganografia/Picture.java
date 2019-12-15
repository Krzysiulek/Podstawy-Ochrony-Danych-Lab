package Steganografia;

import Utils.BitUtils;
import Utils.PictureUtils;
import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture {
    private Pixel[][] imageArray;

    @Getter
    private int width;
    @Getter
    private int height;

    public void loadPicture(String filePath) throws IOException {
        BufferedImage hugeImage = ImageIO.read(new File(filePath));
        width = hugeImage.getWidth();
        height = hugeImage.getHeight();

        imageArray = PictureUtils.convertTo2DUsingGetRGB(hugeImage);
    }

    public void savePicture(String filePath) throws Exception {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                bufferedImage.setRGB(j, i, imageArray[i][j].getPixelValue());
            }
        }

        File file = new File(filePath);
        if (!ImageIO.write(bufferedImage, "png", file)) {
            throw new Exception("Cannot save file");
        }
    }

    /** N value start from 1. */
    public Pixel getNthPixel(int n) {
        int y = n / width;
        int x = (n - 1) % height;

        return imageArray[x][y];
    }

    public void setNthPixel(Pixel pixel, int n) {
        int y = n / width;
        int x = (n - 1) % height;

        imageArray[x][y] = pixel;
    }

    /** N value start from 1.
     *
     * RGBA RGBA*/
    public void setLetterIntoNthPlace(char value, int n) throws Exception {
        int secNum = n * 2;
        int firstNum = secNum - 1;

        Pixel p1 = getNthPixel(firstNum);
        Pixel p2 = getNthPixel(secNum);

        p1.setRed(BitUtils.setLSB(p1.getRed(), BitUtils.getNthBit((int) value, 7)));
        p1.setGreen(BitUtils.setLSB(p1.getGreen(), BitUtils.getNthBit((int) value, 6)));
        p1.setBlue(BitUtils.setLSB(p1.getBlue(), BitUtils.getNthBit((int) value, 5)));
        p1.setAlpha(BitUtils.setLSB(p1.getAlpha(), BitUtils.getNthBit((int) value, 4)));

        p2.setRed(BitUtils.setLSB(p2.getRed(), BitUtils.getNthBit((int) value, 3)));
        p2.setGreen(BitUtils.setLSB(p2.getGreen(), BitUtils.getNthBit((int) value, 2)));
        p2.setBlue(BitUtils.setLSB(p2.getBlue(), BitUtils.getNthBit((int) value, 1)));
        p2.setAlpha(BitUtils.setLSB(p2.getAlpha(), BitUtils.getNthBit((int) value, 0)));

        setNthPixel(p1, firstNum);
        setNthPixel(p2, secNum);
    }

    public void setStringIntoPicture(String value) throws Exception {
        setAllLSBsToOne();

        for (int i = 1; i <= value.length(); i++) {
            setLetterIntoNthPlace(value.charAt(i - 1), i);
        }
    }

    public String getStringFromPicture() {
        StringBuilder toReturn = new StringBuilder();

        for (int i = 1; i <= (width * height) / 2; i++) {
            char toAdd = readLetterFromNthPlace(i);

            if (toAdd == 255)
                break;

            toReturn.append(toAdd);
        }

        return toReturn.toString();
    }

    public char readLetterFromNthPlace(int n) {
        int secNum = n * 2;
        int firstNum = secNum - 1;
        int charValue = 0;

        Pixel p1 = getNthPixel(firstNum);
        Pixel p2 = getNthPixel(secNum);

        charValue = BitUtils.setNthBit(charValue, 7, BitUtils.getLSB(p1.getRed()));
        charValue = BitUtils.setNthBit(charValue, 6, BitUtils.getLSB(p1.getGreen()));
        charValue = BitUtils.setNthBit(charValue, 5, BitUtils.getLSB(p1.getBlue()));
        charValue = BitUtils.setNthBit(charValue, 4, BitUtils.getLSB(p1.getAlpha()));

        charValue = BitUtils.setNthBit(charValue, 3, BitUtils.getLSB(p2.getRed()));
        charValue = BitUtils.setNthBit(charValue, 2, BitUtils.getLSB(p2.getGreen()));
        charValue = BitUtils.setNthBit(charValue, 1, BitUtils.getLSB(p2.getBlue()));
        charValue = BitUtils.setNthBit(charValue, 0, BitUtils.getLSB(p2.getAlpha()));

        return (char) charValue;
    }

    public void setAllLSBsToOne() throws Exception {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Pixel pixel = imageArray[i][j];
                int a = pixel.getAlpha();
                int r = pixel.getRed();
                int g = pixel.getGreen();
                int b = pixel.getBlue();

                a = BitUtils.setLSB(a, 1);
                r = BitUtils.setLSB(r, 1);
                g = BitUtils.setLSB(g, 1);
                b = BitUtils.setLSB(b, 1);

                imageArray[i][j] = new Pixel(a, r, g, b);
            }
        }
    }
}
