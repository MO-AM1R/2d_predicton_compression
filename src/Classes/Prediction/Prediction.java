package Classes.Prediction;
import Classes.Images.Image;
import Classes.Pixel.Pixel;
import Classes.Quantization.Quantization;

import java.util.Arrays;

import static Classes.Pixel.Pixel.*;

public class Prediction {
    public void compress(Image image, int levels){
        int[][] quantizedDifference = new int[image.getWidth()][image.getHeight()] ;
        Image predicted = new Image(image.getWidth(), image.getHeight(), image.getPixels()) ;
        Image difference = new Image(image.getWidth(), image.getHeight(), image.getPixels()) ;

        int minDiff = Integer.MAX_VALUE, maxDiff = Integer.MIN_VALUE ;

        for (int i = 1; i < image.getWidth(); i++) {
            for (int j = 1; j < image.getHeight(); j++) {
                Pixel a = image.getPixel(i, j - 1);
                Pixel b = image.getPixel(i - 1, j - 1);
                Pixel c = image.getPixel(i - 1, j);
                Pixel ans;

                if (b.compareTo(min(a, c)) <= 0){
                    ans = new Pixel(max(a, c));
                }
                else if (b.compareTo(max(a, c)) >= 0){
                    ans = new Pixel(min(a, c));
                }
                else{
                    ans = (a.add(c)).minus(b) ;
                }
                predicted.setPixel(i, j, ans);
                difference.setPixel(i, j, image.getPixel(i, j).minus(ans));

                maxDiff = Integer.max(difference.getPixel(i, j).getColor(), maxDiff);
                minDiff = Integer.min(difference.getPixel(i, j).getColor(), minDiff);
            }
        }
        Quantization quantization = new Quantization();
        quantization.quantize(minDiff, maxDiff + 1, levels);

        for (int i = 0; i < difference.getWidth(); i++) {
            for (int j = 0; j < difference.getHeight(); j++) {
                if (j == 0 || i == 0){
                    quantizedDifference[i][j] = difference.getPixel(i, j).getColor() ;
                }
                else{
                    int color = difference.getPixel(i, j).getColor();
                    int code = quantization.getCode(color);
                    quantizedDifference[i][j] = code;
                }
            }
        }

    }
    public void deCompress(){

    }
}
