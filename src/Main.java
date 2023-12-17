import Classes.Images.Image;
import static Classes.Images.IOImage.*;

public class Main {
    public static void main(String[] args) {
        Image image = new Image() ;
        image.setPixels(readImage(System.getProperty("user.dir") + "\\src\\image.png"));
        writeImage(image.getPixels());
    }
}