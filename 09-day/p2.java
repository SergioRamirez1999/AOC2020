import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    public static boolean satisfy(List<String> preamble, int number_target){
        for(int i = 0; i < preamble.size(); i++){
            for(int h = i+1; h < preamble.size(); h++){
                int n1 = Integer.parseInt(preamble.get(i));
                int n2 = Integer.parseInt(preamble.get(h));
                if(n1+n2==number_target)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String filename = "pzzinput.txt";

        List<String> lines = read_lines(filename);

        List<String> preamble = new ArrayList<>(lines.subList(0, 25));

        List<String> output_numbers = new ArrayList<>(lines.subList(25, lines.size()));

        boolean found = false;
        int number_weak = 0;

        while(!found){
            for(String number_str: output_numbers){
                int number_target = Integer.parseInt(number_str);

                if(!satisfy(preamble, number_target)){
                    found = true;
                    number_weak = number_target;
                    break;
                }

                preamble.remove(0);
                preamble.add(number_str);
                
            }
        }

        List<String> contiguous_set = new ArrayList<>();
        found = false;
        int init = 0;

        while(!found){
            for(int i = init; i < lines.size(); i++){
                contiguous_set.add(lines.get(i));
                if(contiguous_set.size()>10){
                    long sum = contiguous_set.stream().map(x -> Long.parseLong(x)).collect(Collectors.toList()).stream().mapToLong(x ->x).sum();
                    if(sum == number_weak){
                        found = true;
                        break;
                    }
                }

            }
            init++;
            if(!found)
                contiguous_set.clear();
        }

        long smallest = contiguous_set.stream().mapToLong(x->Long.parseLong(x)).min().getAsLong();
        long largest = contiguous_set.stream().mapToLong(x->Long.parseLong(x)).max().getAsLong();

        System.out.println(smallest+largest);
    }

}