package Classes.Prediction;
import static Classes.BinaryFileHanlder.BinaryFileHandler.*;
import Classes.Quantization.Quantization;
import static Classes.Pixel.Pixel.*;
import Classes.Images.Image;
import Classes.Pixel.Pixel;

public class PredictionHelper {
    public static Pixel getPredictor(int i, int j, Image image){
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

        return ans ;
    }
    public static int[] fillPredicted(Image image, Image predicted, Image difference){
        int minDiff = Integer.MAX_VALUE, maxDiff = Integer.MIN_VALUE ;

        for (int i = 1; i < image.getWidth(); i++) {
            for (int j = 1; j < image.getHeight(); j++) {
                Pixel predictor = getPredictor(i, j, image) ;

                predicted.setPixel(i, j, predictor);
                difference.setPixel(i, j, image.getPixel(i, j).minus(predictor));

                maxDiff = Integer.max(difference.getPixel(i, j).getColor(), maxDiff);
                minDiff = Integer.min(difference.getPixel(i, j).getColor(), minDiff);
            }
        }

        return new int[] {minDiff, maxDiff} ;
    }
    public static void fillDecoded(Image deQuantized, Image deCoded){
        for (int i = 1; i < deCoded.getWidth(); i++) {
            for (int j = 1; j < deCoded.getHeight(); j++) {
                Pixel predictor = getPredictor(i, j, deCoded) ;

                predictor.setColor(predictor.getColor() + deQuantized.getPixel(i, j).getColor()) ;
                deCoded.setPixel(i, j, predictor);
            }
        }
    }
    public static void fillQuantized(Image difference, Image quantizedDifference, Quantization quantization){
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
    }
    public static void fillDeQuantized(Image deQuantized, Image quantizedDifference, Quantization quantization){
        for (int i = 1; i < quantizedDifference.getWidth(); i++) {
            for (int j = 1; j < quantizedDifference.getHeight(); j++) {
                deQuantized.setPixel(i, j, new Pixel(quantization.getQ(quantizedDifference.getPixel(i, j)),
                        quantizedDifference.getPixel(i, j).getAlpha()));
            }
        }
    }
    public static void readFromFile(Image quantizedDifference, Quantization quantization){
        read() ;
    }
    public static void writeIntoFile(Image quantizedDifference, Quantization quantization){
        write(quantizedDifference, quantization) ;
    }
}
