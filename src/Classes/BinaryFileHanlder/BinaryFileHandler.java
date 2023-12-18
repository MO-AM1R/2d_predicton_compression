package Classes.BinaryFileHanlder;
import Classes.Quantization.Quantization;
import Classes.Quantization.Pair;
import Classes.Images.Image;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class BinaryFileHandler {
    public static void write(Image quantizedDifference, Quantization quantization){
        try {
            if (!Files.exists(Paths.get(System.getProperty("user.dir") + "Output.bin"))){
                Files.createFile(Paths.get(System.getProperty("user.dir") + "Output.bin")) ;
            }
            FileOutputStream fileOutputStream = new FileOutputStream("Output.bin");
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            Map<Integer, Pair> table = quantization.getQuantizationTable() ;

            for (Map.Entry<Integer, Pair> entry : table.entrySet()) {
                dataOutputStream.write(entry.getKey());
                dataOutputStream.write(entry.getValue().range.get(0));
                dataOutputStream.write(entry.getValue().range.get(1));
                dataOutputStream.write(entry.getValue().code);
            }
            dataOutputStream.writeByte((byte) '\n');

            for (int i = 0; i < quantizedDifference.getWidth(); i++) {
                for (int j = 0; j < quantizedDifference.getHeight(); j++) {
                    dataOutputStream.write(quantizedDifference.getPixel(i, j).getColor());
                    dataOutputStream.write(quantizedDifference.getPixel(i, j).getAlpha());
                }
            }
            dataOutputStream.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static Object read(){
        //read from bin
        return null ;
    }
}
