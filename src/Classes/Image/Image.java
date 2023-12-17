package Classes.Image;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import Classes.Pixel.Pixel;
import java.util.Arrays;
import java.io.File;
import java.awt.*;

public class Image {
    private Pixel[][] pixels ;

    /**
     *<pre>
     *Method {@code readImage} to read the image and create 2D gray scale pixels {@code pixels}
     *</pre>
     * @param path <strong style="color:'white'"> represent the path of the image</strong>
     */
    public void readImage(String path){
        try {
            BufferedImage image = ImageIO.read(new File(path));
            pixels = new Pixel[image.getWidth()][image.getHeight()] ;

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    Color color = new Color(image.getRGB(i, j)) ;
                    pixels[i][j] =
                            new Pixel((color.getBlue() + color.getRed() + color.getGreen()) / 3, color.getAlpha()) ;
                }
            }
        }
        catch (IOException ioException){
            System.out.println(ioException.getMessage());
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
    public BufferedImage createBufferedImage(Pixel[][] pixels){
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
    public void writeImage(Pixel[][] pixels){
        try {
            BufferedImage bufferedImage = createBufferedImage(pixels) ;
            File image = new File(System.getProperty("user.dir") + "\\output.png") ;
            ImageIO.write(bufferedImage, "png", image) ;
        }
        catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
    }

    /**
     <pre>
     *Method {@code toString} to convert the pixels 2D array to string and return it
     *</pre>
     * @return <pre>{@code String}</pre> <strong style="color:'white'"> represent the string of the pixels' array</strong>
     */
    @Override
    public String toString() {
        return "Image{" +
                "pixels=" + Arrays.deepToString(pixels) +
                '}';
    }

    /**
     *<pre>
     *A getter gets the pixels of the image
     *</pre>
     * @return <pre>{@code 2D array pixel}</pre> <strong style="color:'white'"> represent the pixels of the image</strong>
     */
    public Pixel[][] getPixels() {
        return pixels ;
    }

    /**
     *<pre>
     *A getter gets the height of the image
     *</pre>
     * @return <pre>{@code int}</pre> <strong style="color:'white'"> represent the height of the image</strong>
     */
    public int getHeight() {
        return pixels[0].length;
    }

    /**
     *<pre>
     *A getter gets the width of the image
     *</pre>
     * @return <pre>{@code int}</pre> <strong style="color:'white'"> represent the width of the image</strong>
     */
    public int getWidth() {
        return pixels.length;
    }
}