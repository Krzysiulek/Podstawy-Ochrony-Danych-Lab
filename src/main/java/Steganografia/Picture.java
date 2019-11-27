package Steganografia;

import Utils.PictureUtils;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture {
    @Getter
    @Setter
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

    public void savePicture(String filePath) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                bufferedImage.setRGB(j, i, imageArray[i][j].getPixelValue());
            }
        }

        File file = new File(filePath);
        ImageIO.write(bufferedImage, "jpg", file);
    }

    public Pixel getNthPixel(int n) {
        int x = n / (width + 1);
        int y = (n - 1) % width;

        return imageArray[x][y];
    }

}
