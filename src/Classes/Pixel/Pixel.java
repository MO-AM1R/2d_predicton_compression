package Classes.Pixel;

public class Pixel implements Comparable<Pixel>{
    int color;
    int alpha ;

    public Pixel(Pixel pixel) {
        color = pixel.color ;
        alpha = pixel.alpha ;
    }

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

    public static Pixel min(Pixel pixel1, Pixel pixel2) {
        if (pixel1.color < pixel2.color){
            return pixel1 ;
        }
        else{
            return pixel2 ;
        }
    }

    public static Pixel max(Pixel pixel1, Pixel pixel2) {
        if (pixel2.color < pixel1.color){
            return pixel1 ;
        }
        else{
            return pixel2 ;
        }
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
        return Integer.toString(color);
    }

    /**
     * Compares this object with the specified object for order.  Returns a negative integer, zero, or a positive
     * integer as this object is less than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for all {@code x} and {@code y}.  (This implies that
     * {@code x.compareTo(y)} must throw an exception if and only if {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z)) == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o
     *         the object to be compared.
     *
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     * the specified object.
     *
     * @throws NullPointerException
     *         if the specified object is null
     * @throws ClassCastException
     *         if the specified object's type prevents it from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any class that implements the
     * {@code Comparable} interface and violates this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is inconsistent with equals."
     */
    @Override
    public int compareTo(Pixel o) {
        return Integer.compare(color, o.color);
    }
    public Pixel minus(Pixel b) {
        return new Pixel(Math.max((this.color - b.getColor()), 0), this.alpha);
    }
    public Pixel add(Pixel b) {
        return new Pixel(Math.min((this.color + b.getColor()), 255), this.alpha);
    }

    public void setColor(int color) {
        this.color = color ;
    }
}
