package Classes.Images;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import Classes.Pixel.Pixel;
import java.io.IOException;
import java.io.File;
import java.awt.*;

public class IOImage{
    /**
     *<pre>
     *Method {@code readImage} to read the image and create 2D gray scale pixels {@code pixels}
     *</pre>
     * @param path <strong style="color:'white'"> represent the path of the image</strong>
     */
    public static Pixel[][] readImage(String path){
        try {
            BufferedImage image = ImageIO.read(new File(path));
            Pixel[][] pixels = new Pixel[image.getWidth()][image.getHeight()] ;

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    Color color = new Color(image.getRGB(i, j)) ;
                    pixels[i][j] =
                            new Pixel((color.getBlue() + color.getRed() + color.getGreen()) / 3, color.getAlpha()) ;
                }
            }
            return pixels ;
        }
        catch (IOException ioException){
            System.out.println(ioException.getMessage());
            return null ;
        }
    }

    /**
     *<pre>
     *Method {@code createBufferedImage} creates the buffered image
     *from the pixels array{@code pixels}
     *</pre>
     * @param pixels <strong style="color:'white'"> represent the pixels of the image</strong>
     * @return <pre>{@code BufferedImage}</pre> <strong style="color:'white'"> represent the Buffered Image</strong>
     */
    public static BufferedImage createBufferedImage(Pixel[][] pixels){
        BufferedImage bufferedImage = new BufferedImage(pixels.length, pixels[0].length, BufferedImage.TYPE_BYTE_GRAY) ;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                bufferedImage.setRGB(i, j, new Color(pixels[i][j].getColor(),
                        pixels[i][j].getColor(), pixels[i][j].getColor(), pixels[i][j].getAlpha()).getRGB());
            }
        }

        return bufferedImage ;
    }

    /**
     *<pre>
     *Method {@code writeImage} writes the image
     *and save it in {@code project directory}
     *</pre>
     * @param pixels <strong style="color:'white'"> represent the pixels of the image</strong>
     */
    public static void writeImage(Pixel[][] pixels){
        try {
            BufferedImage bufferedImage = createBufferedImage(pixels) ;
            File image = new File(System.getProperty("user.dir") + "\\output.png") ;
            ImageIO.write(bufferedImage, "png", image) ;
        }
        catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
    }

}
