import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class p1 {

    public static void main(String ... args) {
        String filename = "pzzinput.txt";

        List<String> mandatory_fields = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

        try {
            String file_raw = Files.lines(Paths.get(filename)).collect(Collectors.joining("\n"));

            List<String> passports_raw = Arrays.asList(file_raw.split("\n\\s*\n")).stream().map(p -> p.replace("\n", " ")).collect(Collectors.toList());

            List<Map<String, String>> passports = passports_raw.stream().map(e -> Arrays.stream(e.split(" "))
                                                                                        .map(f -> f.split(":"))
                                                                                        .collect(Collectors.toMap(f -> f[0], f -> f[1])))
                                                                        .collect(Collectors.toList());

            Long valid_passports = passports.stream().map(Map::keySet).filter(k -> k.containsAll(mandatory_fields)).count();

            System.out.println(valid_passports);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
