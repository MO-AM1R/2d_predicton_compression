import Classes.Images.Image;
import Classes.Prediction.Prediction;

import static Classes.Images.IOImage.*;

public class Main {
    public static void main(String[] args) {
        Image image = new Image() ;
        image.setPixels(readImage(System.getProperty("user.dir") + "\\src\\image.png"));
        Prediction prediction = new Prediction();
        prediction.compress(image);
//        writeImage(image.getPixels());
    }
}