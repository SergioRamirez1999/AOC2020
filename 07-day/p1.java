import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static Map<String, Map<String, String>> determine_color_content(List<String> lines){
        Map<String, Map<String, String>> color_content = new HashMap<>();
        for(String line: lines){
            line = line.replace("bags", "").replace("bag", "").replace(".", "").replace(" ,", ",").trim();
            String[] color_content_split = line.split(" contain ");
            String[] content = color_content_split[1].split(", ");

            Map<String, String> color_quantity_content = new HashMap<>();

            for(String c: content){
                String key = null;
                String value = null;
                if(!c.contains("other")){
                    key = c.substring(2, c.length());
                    value = c.substring(0,1);
                }

                color_quantity_content.put(key, value);
                
            }
            color_content.put(color_content_split[0].trim(), color_quantity_content);
        }

        return color_content;
    }

    public static boolean is_candidate(Map<String, Map<String, String>> color_content, String color){
        if(color == null){
            return 0;
        }
        else if(color.equals("shiny gold")){
            return true;
        }
        else {
            boolean is_candidate = false;
            for(Entry<String, String> kv: color_content.get(color).entrySet()){
                is_candidate = is_candidate(color_content, kv.getKey());
                if(is_candidate)
                    break;
            }
            return is_candidate;
        }
    }

    public static int count_total_candidates(Map<String, Map<String, String>> color_content){
        int total_candidates = 0;

        for(String key: color_content.keySet()){
            if(is_candidate(color_content, key))
                total_candidates++;
        }

        return total_candidates-1;
    }



    public static void main(String[] args) {
        String filename = "pzzinput.txt";

        List<String> lines = read_lines(filename);

        Map<String, Map<String, String>> color_content = determine_color_content(lines);

        System.out.println(color_content);

        System.out.println(count_total_candidates(color_content));

    }
    
}

