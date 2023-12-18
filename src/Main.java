import Classes.Images.Image;
import Classes.Pixel.Pixel;
import Classes.Prediction.Prediction;
import static Classes.Images.IOImage.*;

public class Main {
    public static void main(String[] args) {
        Image image = new Image() ;
        image.setPixels(readImage(System.getProperty("user.dir") + "\\src\\image.png"));
        Prediction prediction = new Prediction();
        image.setPixels(new Pixel[][]{
                {
                        new Pixel(5, 0),
                        new Pixel(7, 0),
                        new Pixel(8, 0),
                        new Pixel(10, 0)
                },
                {
                        new Pixel(6, 0),
                        new Pixel(6, 0),
                        new Pixel(9, 0),
                        new Pixel(11, 0)
                },
                {
                        new Pixel(7, 0),
                        new Pixel(8, 0),
                        new Pixel(11, 0),
                        new Pixel(13, 0)
                },
                {
                        new Pixel(9, 0),
                        new Pixel(10, 0),
                        new Pixel(11, 0),
                        new Pixel(14, 0)
                },
        });
        prediction.compress(image, 4);
    }
}