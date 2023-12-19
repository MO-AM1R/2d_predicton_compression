import Classes.Images.Image;
import static Classes.Images.IOImage.readImage;
import Classes.Prediction.Prediction;

public class Main {
    public static void main(String[] args) {
        Image image = new Image() ;
        image.setPixels(readImage(System.getProperty("user.dir") + "\\src\\image.png"));
        Prediction prediction = new Prediction();
        prediction.compress(image, 256);
        prediction.deCompress();
    }
}