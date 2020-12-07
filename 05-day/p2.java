import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public static Integer[] determineSeat(String line){
        char[] characters = line.toCharArray();
        Integer[] min_max_row = new Integer[]{0, 127};
        Integer[] min_max_col = new Integer[]{0, 7};
        Integer[] row_col = new Integer[2];

        for(int i = 0; i < characters.length; i++){
            if(characters[i] == 'F'){
                min_max_row[1] = min_max_row[1] - (int)Math.ceil((double)(min_max_row[1]-min_max_row[0])/2);
            }else if(characters[i] == 'B'){
                min_max_row[0] = min_max_row[0] + (int)Math.ceil((double)(min_max_row[1]-min_max_row[0])/2);
            }else if(characters[i] == 'R'){
                min_max_col[0] = min_max_col[0] + (int)Math.ceil((double)(min_max_col[1]-min_max_col[0])/2);
            }else if(characters[i] == 'L'){
                min_max_col[1] = min_max_col[1] - (int)Math.ceil((double)(min_max_col[1]-min_max_col[0])/2);
            }
        }
        row_col[0] = min_max_row[0];
        row_col[1] = min_max_col[0];
        return row_col;
    }

    public static boolean has_consecutive(List<Integer> id_passports, Integer value) {

        int value_pos = id_passports.indexOf(value);

        int next_value = id_passports.get(value_pos+1);

        return next_value-value == 1;

    }

    public static void main(String[] args) {
        String filename = "pzzinput.txt";
        List<String> lines = read_lines(filename);

        List<Integer> id_passports = lines.stream().map(p -> Arrays.stream(determineSeat(p))
                                                                   .reduce(0, (a,b) -> a*8+b))
                                                    .sorted()
                                                    .collect(Collectors.toList());

                                                    System.out.println(id_passports);
        
        Integer id_seat = id_passports.stream().filter(e -> !has_consecutive(id_passports, e))
                                               .findFirst()
                                               .get()+1;

        System.out.println(id_seat);

    }
    
}
