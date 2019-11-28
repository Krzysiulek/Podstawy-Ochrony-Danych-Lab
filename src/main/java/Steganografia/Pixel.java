package Steganografia;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pixel {
    private int alpha;
    private int red;
    private int green;
    private int blue;

    public Pixel(int pixelValue) {
        alpha = (pixelValue>>24) & 0xff;
        red = (pixelValue>>16) & 0xff;
        green = (pixelValue>>8) & 0xff;
        blue = pixelValue & 0xff;
    }



    public int getPixelValue() {
        int p = (alpha<<24) | (red<<16) | (green<<8) | blue;
        return p;
    }
}
