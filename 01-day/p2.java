import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class p2 {

    public static List<String> read_lines(String path){
        List<String> lines = new ArrayList<>();    

        try {
            lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void three_entries(List<String> lines) {
        int op1 = 0, op2 = 0, op3 = 0;
        exit:
        for(int i = 0; i < lines.size(); i++){
            for(int h = i+1; h < lines.size(); h++){
                for(int j = h+1; j < lines.size(); j++){
                    if((Integer.parseInt(lines.get(i)) + Integer.parseInt(lines.get(h))) + Integer.parseInt(lines.get(j)) == 2020){
                        op1 = Integer.parseInt(lines.get(i));
                        op2 = Integer.parseInt(lines.get(h));
                        op3 = Integer.parseInt(lines.get(j));
                        break exit;
                    }
                }
            }
        }


        System.out.println(op1 * op2 * op3);
    }

    public static void main(String[] args){
        String path = "pzzinput.txt";

        List<String> lines = read_lines(path);

        three_entries(lines);

    }
    
}
