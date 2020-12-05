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

public class p2 {

    public static boolean isValidPassport(Map<String, String> passport){
        if(passport.containsKey("byr")){
            int year = Integer.parseInt(passport.get("byr"));
            if(!(year >= 1920 && year <= 2002))
                return false;
        }

        if(passport.containsKey("iyr")){
            int birth_year = Integer.parseInt(passport.get("iyr"));
            if(!(birth_year >= 2010 && birth_year <= 2020))
                return false;
        }

        if(passport.containsKey("eyr")){
            int issue_year = Integer.parseInt(passport.get("eyr"));
            if(!(issue_year >= 2020 && issue_year <= 2030))
                return false;
        }

        if(passport.containsKey("hgt")){
            String exp_year = passport.get("hgt");
            int year = Integer.parseInt(exp_year.substring(0, exp_year.length()-2));
            String metric = exp_year.substring(exp_year.length()-2, exp_year.length());
            if(metric.equals("cm")){
                if(!(year >= 150 && year <= 193))
                    return false;
            }else if(metric.equals("in")){
                if(!(year >= 59 && year <= 76))
                    return false;
            }
        }

        if(passport.containsKey("hcl")){
            String hair_color = passport.get("hcl");
            if(!hair_color.matches("#[a-f0-9]{6}"))
                return false;
        }

        if(passport.containsKey("ecl")){
            String eye_color = passport.get("ecl");
            List<String> possible_colors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
            if(!possible_colors.contains(eye_color))
                return false;
        }

        if(passport.containsKey("pid")){
            String id = passport.get("pid");
            if(!id.matches("[0-9]{9}"))
                return false;
        }

        return true;
    }

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

            List<Map<String, String>> passports_valid_level_1 = passports.stream().filter(k -> k.keySet().containsAll(mandatory_fields)).collect(Collectors.toList());

            Long passports_valid_level_2 = passports_valid_level_1.stream().filter(kv -> isValidPassport(kv)).count();

            System.out.println(passports_valid_level_2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
