package Classes.Prediction;
import static Classes.Prediction.PredictionHelper.*;
import static Classes.Images.IOImage.writeImage;
import Classes.Quantization.Quantization;
import Classes.Images.Image;

public class Prediction {
    public void compress(Image image, int levels){
        Image quantizedDifference = new Image(image.getWidth(), image.getHeight(), image.getPixels()) ;
        Image predicted = new Image(image.getWidth(), image.getHeight(), image.getPixels()) ;
        Image difference = new Image(image.getWidth(), image.getHeight(), image.getPixels()) ;

        // fill predicted image
        int[] values = fillPredicted(image, predicted, difference) ;
        int minDiff = values[0], maxDiff = values[1] ;

        // quantization part
        Quantization quantization = new Quantization();
        quantization.quantize(minDiff, maxDiff + 1, levels);

        // fill quantized differences
        fillQuantized(difference, quantizedDifference, quantization);

        // write in bin file
        writeIntoFile(quantizedDifference, quantization);
    }
    public void deCompress(){
        Image quantizedDifference = new Image();
        Quantization quantization = new Quantization();

        // read from bin file
        readFromFile(quantizedDifference, quantization);

        Image deQuantized = new Image(quantizedDifference.getWidth(), quantizedDifference.getHeight(), quantizedDifference.getPixels()) ;
        Image deCoded = new Image(quantizedDifference.getWidth(), quantizedDifference.getHeight(), quantizedDifference.getPixels()) ;

        // fill deQuantized differences
        fillDeQuantized(deQuantized, quantizedDifference, quantization);

        // fill deCoded Image
        fillDecoded(deQuantized, deCoded);

        // write deCoded Image
        writeImage(deCoded.getPixels()) ;
    }
}
