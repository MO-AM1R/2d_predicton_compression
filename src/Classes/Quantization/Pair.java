package Classes.Quantization;
import java.io.Serializable;
import java.util.List;

public class Pair implements Serializable {
    public List<Integer> range ;
    public int code ;

    public Pair(List<Integer> range, int code) {
        this.range = range;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "range=" + range +
                ", code=" + code +
                '}';
    }
}