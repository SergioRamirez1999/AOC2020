import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class p1 {

    public static List<String> read_lines(String path){
        List<String> lines = new ArrayList<>();    

        try {
            lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static int trees_by_slopes(List<String> lines, int slope_x, int slope_y){
        char tree = '#';
        int width = lines.get(0).length();
        int tree_count = 0;

        int x = 0;
        for(int i = 0; i < lines.size()-slope_y; i+=slope_y){
            String line = lines.get(i+slope_y);

            x = (x+slope_x)%width;

            if(line.toCharArray()[x]==tree)
                tree_count++;
        }

        return tree_count;
    }
    
    public static void main(String ... args){
        String filename = "pzzinput.txt";

        List<String> lines = read_lines(filename);

        System.out.println(trees_by_slopes(lines, 3, 1));
    }
}
