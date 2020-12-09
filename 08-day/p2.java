import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    public static void main(String[] args) {
        String filename = "pzzinput.txt";
        List<String> instructions = read_lines(filename);
        Console console = new Console(instructions, false);
        console.run();
    }
    
}


class Console {
    private int pc = 0;
    private int accumulator = 0;
    private List<String> instructions;
    private boolean debug;

    public Console(List<String> instructions, boolean debug){
        this.instructions = instructions;
        this.debug = debug;
    }

    private String[] decode_instruction(String instruction){
        return instruction.split(" ");
    }

    public void step() {

        String[] instruction = decode_instruction(instructions.get(pc++));

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

    public void run(){

        List<String> cp_instruction = new ArrayList<>(instructions);
        for(int i = 0; i < instructions.size(); i++){
            String[] instruction = decode_instruction(cp_instruction.get(i));
            instructions = new ArrayList<>(cp_instruction);
            if(instruction[0].equals("jmp")){
                instruction[0] = "nop";
                instructions.set(i, instruction[0]+" "+instruction[1]);
            }else if(instruction[0].equals("nop")){
                instruction[0] = "jmp";
                instructions.set(i, instruction[0]+" "+instruction[1]);
            }else
                continue;

            int c = 0;
            pc = 0;
            accumulator = 0;
            while(pc < instructions.size() && pc >= 0 && c <= 1000){
                c++;
                step();
            }
            
            if(pc == instructions.size()){
                System.out.println(accumulator);
            }
            
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