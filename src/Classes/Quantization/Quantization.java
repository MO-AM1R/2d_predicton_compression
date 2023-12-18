package Classes.Quantization;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quantization {
    Map<Integer, Pair> quantizationTable ;

    public void quantize(int min, int max, int levels){
        int step = (max - min + 1) / levels ;
        quantizationTable = new HashMap<>() ;

        for (int i = 0; i < levels; i++) {
            List<Integer> range = new ArrayList<>() ;
            range.add(min + (i * step));
            range.add(range.get(0) + step);

            int q = (range.get(0) + range.get(1)) / 2;
            Pair pair = new Pair(range, q) ;
            quantizationTable.put(i, pair) ;
        }
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        for (Map.Entry<Integer, Pair> entry :
                quantizationTable.entrySet()) {
            ans.append(" ").append(entry.getKey()).
                    append(" | ").append(entry.getValue().range.get(0)).
                    append(" -> ").append(entry.getValue().range.get(1)).
                    append(" | ").append(entry.getValue().code).append(" \n");
        }
        return ans.toString();
    }

    public int getCode(int color) {
        for (Map.Entry<Integer, Pair> entry: quantizationTable.entrySet()) {
            if (entry.getValue().range.get(0) <= color && entry.getValue().range.get(1) > color){
                return entry.getKey() ;
            }
        }
        return 0 ;
    }
}
