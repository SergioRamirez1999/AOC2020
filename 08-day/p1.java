import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
        List<String> instructions = read_lines(filename);
        Console console = new Console(instructions, false);
        console.run();
        System.out.println(console.getAccumulator());
    }
    
}


class Console {
    private int pc = 0;
    private int accumulator = 0;
    private List<String> instructions;
    private boolean debug;
    private Set<Integer> pc_rep = new LinkedHashSet<>();

    public Console(List<String> instructions, boolean debug){
        this.instructions = instructions;
        this.debug = debug;
    }

    private String[] decode_instruction(String instruction){
        return instruction.split(" ");
    }

    public void step() throws InfiniteLoopException {

        if(pc < instructions.size()){
            String[] instruction = decode_instruction(instructions.get(pc++));

            int size_prev = pc_rep.size();

            pc_rep.add((pc-1));

            if(pc_rep.size() == size_prev)
                throw new InfiniteLoopException("Repeated");

            if(debug){
                System.out.println(MessageFormat.format("Instruction [{0}] [{1}]", instruction[0], instruction[1]));
                System.out.println(MessageFormat.format("Console Params: PC -> [{0}], Accumulator -> [{1}]", pc, accumulator));
            }

            int operand = Integer.parseInt(instruction[1]);

            switch(instruction[0]){

                case "acc":
                    accumulator += operand;
                    step();
                    break;
                case "jmp":
                    pc += operand-1;
                    break;
                case "nop":
                    step();
                    break;
              
            }
        }
    }

    public void run(){

        try {
            while(pc < instructions.size()){
                step();
            }
        } catch (InfiniteLoopException e) {
            return;
        }

    }

    public int getAccumulator(){
        return accumulator;
    }

    public boolean getDebug(){
        return debug;
    }

    public void setDebug(boolean debug){
        this.debug = debug;
    }
    
}

class InfiniteLoopException extends Exception {
    public InfiniteLoopException(String errorMessage) {
        super(errorMessage);
    }
}
