import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public static Long DP(List<Integer> output_jolts, Map<Integer, Long> memoization, Integer n){
        if(n == output_jolts.size()-1)
            return 1l;

        if(memoization.containsKey(n))
            return memoization.get(n);

        Long ans = 0l;

        for(Integer i = n+1; i < output_jolts.size(); i++){
            if(output_jolts.get(i) - output_jolts.get(n) <= 3){
                ans += DP(output_jolts, memoization, i);
            }
        }

        memoization.put(n, ans);
        return ans;
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

        Map<Integer, Long> memoization = new HashMap<>();

        Long distinct_arragements = DP(output_jolts, memoization, 0);

        System.out.println(distinct_arragements);

    }

}