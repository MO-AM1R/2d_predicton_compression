import Classes.Image.Image;

public class Main {
    public static void main(String[] args) {
        Image image = new Image() ;
        image.readImage(System.getProperty("user.dir") + "\\src\\image.png");
        image.writeImage(image.getPixels());
    }
}