package Classes.Images;
import Classes.Pixel.Pixel;
import java.util.Arrays;

public class Image {
    private Pixel[][] pixels ;

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

    public void setPixels(Pixel[][] pixels) {
        this.pixels = pixels ;
    }
}