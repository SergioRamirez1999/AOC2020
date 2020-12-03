import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    public static void main(String ... args){
        String filename = "pzzinput.txt";

        List<String> lines = read_lines(filename);

        Long valid_passwords = lines.stream().map(l -> PasswordPolicy.createPassword(l)).filter(p -> p.isValidPassword()).count();

        System.out.println(valid_passwords);

    }
}

class PasswordPolicy {
    int min;
    int max;
    char letter;
    String password;

    public static String REGEX_PATTERN = "^([0-9]*)-([0-9]*) ([a-z]): ([a-z]*)$";

    private PasswordPolicy(int min, int max, char letter, String password){
        this.min = min;
        this.max = max;
        this.letter = letter;
        this.password = password;
    }
    
    public static PasswordPolicy createPassword(String line){
        Pattern pattern = Pattern.compile(REGEX_PATTERN);
        Matcher matcher = pattern.matcher(line);

        if(matcher.matches()){
            return new PasswordPolicy(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
            matcher.group(3).charAt(0), matcher.group(4));
        }
        return null;
    }

    public boolean isValidPassword() {
        Long occurrences = password.chars().filter(ch -> ch == letter).count();
        return (occurrences >= min && occurrences <= max);
    }
}
