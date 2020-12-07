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

public class p1 {

    public static void main(String[] args) {
        String filename = "pzzinput.txt";

        try {
           String lines = Files.lines(Paths.get(filename))
                               .collect(Collectors.joining("\n"));

           List<Set<Character>> answers = Arrays.asList(lines.split("\n\\s*\n"))
                                             .stream()
                                             .map(x -> x.replace("\n", " ")
                                                        .chars()
                                                        .mapToObj(i -> (char)i)
                                                        .filter(c -> c != ' ')
                                                        .collect(Collectors.toSet()))
                                             .collect(Collectors.toList());
            int c_yes = answers.stream().mapToInt(s -> s.size()).sum();

            System.out.println(c_yes);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
