package Classes.Prediction;
import Classes.Images.Image;

public class Prediction {
    public void compress(Image image){
        Image predicted = new Image(image.getWidth(), image.getHeight(), image.getPixels()) ;
        Image quantizedImage = new Image(image.getWidth(), image.getHeight(), image.getPixels()) ;
        Image difference = new Image(image.getWidth(), image.getHeight(), image.getPixels()) ;
        Image decodedImage = new Image(image.getWidth(), image.getHeight(), image.getPixels()) ;


    }
    public void deCompress(){

    }
}
