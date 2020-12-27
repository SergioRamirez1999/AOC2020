import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public static void main(String[] args) {
        String filename = "pzzinput.txt";

        List<String> lines = read_lines(filename);

        List<Integer> output_jolts = lines.stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        //add jolt of charge outlet
        output_jolts.add(0);

        //add jolt of device built-in jolts adapter
        output_jolts.add(output_jolts.stream().mapToInt(x -> x).max().getAsInt()+3);

        Collections.sort(output_jolts);

        int one_diff_jolts = 0;
        int three_diff_jolts = 0;

        for(int i = 0; i < output_jolts.size()-1; i++){
            int n1 = output_jolts.get(i);
            int n2 = output_jolts.get(i+1);

            if(n2-n1 == 1)
                one_diff_jolts++;
            else if(n2-n1 == 3)
                three_diff_jolts++;
        }

        System.out.println(MessageFormat.format("1-jolt: [{0}]\n3-jolt: [{1}]\nProduct: [{2}] ", one_diff_jolts, three_diff_jolts, one_diff_jolts*three_diff_jolts));

    }

}