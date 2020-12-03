import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static void two_entries(List<String> lines) {
        int op1 = 0, op2 = 0;
        exit:
        for(int i = 0; i < lines.size(); i++){
            for(int h = i+1; h < lines.size(); h++){
                if((Integer.parseInt(lines.get(i)) + Integer.parseInt(lines.get(h))) == 2020){
                    op1 = Integer.parseInt(lines.get(i));
                    op2 = Integer.parseInt(lines.get(h));
                    break exit;
                }
            }
        }

        System.out.println(op1 * op2);
    }

    public static void main(String[] args){
        String path = "pzzinput.txt";

        List<String> lines = read_lines(path);

        two_entries(lines);

    }
}