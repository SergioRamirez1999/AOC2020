import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class p2 {


    public static int count_everyone_yes(List<String> group_answers){
        String first_value = group_answers.get(0);

        if(group_answers.size() == 1)
            return group_answers.get(0).length();

        int everyone_match = 0;
        for(int i = 0; i < first_value.length(); i++){
            char c = first_value.charAt(i);

            int group_matches = 0;
            for(int h = 0; h < group_answers.size(); h++){
                if(group_answers.get(h).contains(String.valueOf(c))){
                    group_matches++;
                }
            }
            if(group_matches == group_answers.size())
                everyone_match++;
        }

        return everyone_match;
    }   

    public static void main(String[] args) {
        String filename = "pzzinput.txt";

        try {
            String lines = Files.lines(Paths.get(filename))
                               .collect(Collectors.joining("\n"));


            System.out.println(Arrays.asList(lines.split("\n\\s*\n")[2].replace("\n", " ").split(" ")));

            List<List<String>> answers = Arrays.asList(lines.split("\n\\s*\n"))
                                                .stream()
                                                .map(x -> Arrays.asList(x.replace("\n", " ").split(" ")))
                                                .collect(Collectors.toList());
            int everyone_matches = answers.stream().mapToInt(e -> count_everyone_yes(e)).sum();

            System.out.println(everyone_matches);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
