package Classes.Pixel;

public class Pixel {
    int color;
    int alpha ;

    /**
     *<pre>
     *A Parameterized Constructor initialize the Pixel {@code color}, {@code alpha}
     *</pre>
     * @param color <strong style="color:'white'"> represent the color of the pixel</strong>
     * @param alpha <strong style="color:'white'"> represent the alpha of the pixel</strong>
     */
    public Pixel(int color, int alpha) {
        this.color = color;
        this.alpha = alpha;
    }

    /**
     *<pre>
     *A Getter gets the Pixel {@code color}
     *</pre>
     * @return <pre>{@code int}</pre> <strong style="color:'white'"> represent the color of the pixel</strong>
     */
    public int getColor() {
        return color;
    }

    /**
     *<pre>
     *A Getter gets the Pixel {@code alpha}
     *</pre>
     * @return <pre>{@code int}</pre> <strong style="color:'white'"> represent the alpha of the pixel</strong>
     */
    public int getAlpha() {
        return alpha;
    }

    /**
     <pre>
     *Method {@code toString} to convert the pixel to string and return it
     *</pre>
     * @return <pre>{@code String}</pre> <strong style="color:'white'"> represent the string of the pixel's info</strong>
     */
    public String toString() {
        return "Pixel{ color = " + color + ", alpha = " + alpha + "}";
    }
}
