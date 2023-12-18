package Classes.Prediction;
import Classes.Images.Image;
import Classes.Pixel.Pixel;
import Classes.Quantization.Quantization;

import static Classes.Images.IOImage.writeImage;
import static Classes.Pixel.Pixel.*;

public class Prediction {
    public void compress(Image image, int levels){
        Image quantizedDifference = new Image(image.getWidth(), image.getHeight(), image.getPixels()) ;
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
                    quantizedDifference.setPixel(i, j, difference.getPixel(i, j)) ;
                }
                else{
                    int color = difference.getPixel(i, j).getColor();
                    int code = quantization.getCode(color);
                    quantizedDifference.setPixel(i, j, new Pixel(code, difference.getPixel(i, j).getAlpha()));
                }
            }
        }

        // write in bin file
    }
    public void deCompress(Image quantizedDifference){
        // quantizedDifference read from bin file

        Image deQuantized = new Image(quantizedDifference.getWidth(), quantizedDifference.getHeight(), quantizedDifference.getPixels()) ;
        Image deCoded = new Image(quantizedDifference.getWidth(), quantizedDifference.getHeight(), quantizedDifference.getPixels()) ;

        Quantization quantization = new Quantization() ;
        // should load the table from bin file

        for (int i = 1; i < quantizedDifference.getWidth(); i++) {
            for (int j = 1; j < quantizedDifference.getHeight(); j++) {
                deQuantized.setPixel(i, j, new Pixel(quantization.getQ(quantizedDifference.getPixel(i, j)),
                        quantizedDifference.getPixel(i, j).getAlpha()));
            }
        }

        for (int i = 1; i < deCoded.getWidth(); i++) {
            for (int j = 1; j < deCoded.getHeight(); j++) {
                Pixel a = deCoded.getPixel(i, j - 1);
                Pixel b = deCoded.getPixel(i - 1, j - 1);
                Pixel c = deCoded.getPixel(i - 1, j);
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
                ans.setColor(ans.getColor() + deQuantized.getPixel(i, j).getColor()) ;
                deCoded.setPixel(i, j, ans);
            }
        }

        writeImage(deCoded.getPixels()) ;
    }
}
