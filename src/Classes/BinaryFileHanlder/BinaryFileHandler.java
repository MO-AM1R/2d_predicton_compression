package Classes.BinaryFileHanlder;
import Classes.Pixel.Pixel;
import Classes.Quantization.Quantization;
import Classes.Quantization.Pair;
import Classes.Images.Image;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

//class WrapperContainer{
//    Map<Integer, Pair> table ;
//    Pixel[][] pixels ;
//
//
//}

public class BinaryFileHandler {
    public static void write(Image quantizedDifference, Quantization quantization){
        try {
            if (!Files.exists(Paths.get(System.getProperty("user.dir") + "Output.bin"))){
                Files.createFile(Paths.get(System.getProperty("user.dir") + "Output.bin")) ;
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Output.bin"));
            Map<Integer, Pair> table = quantization.getQuantizationTable() ;
            Pixel[][] pixels = quantizedDifference.getPixels() ;

            objectOutputStream.writeObject(table);
            objectOutputStream.writeObject(pixels);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void read(Image quantizedDifference, Quantization quantization){
        try (FileInputStream fis = new FileInputStream("Output.bin")) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(fis));

            quantization.setTable((Map<Integer, Pair>)objectInputStream.readObject());
            quantizedDifference.setPixels((Pixel[][]) objectInputStream.readObject());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
