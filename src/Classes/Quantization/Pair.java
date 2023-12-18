package Classes.Quantization;
import java.util.List;

public class Pair {
    public List<Integer> range ;
    public int code ;

    public Pair(List<Integer> range, int code) {
        this.range = range;
        this.code = code;
    }
}