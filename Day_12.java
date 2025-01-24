
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Day_12 {

    public static void main(String[] args) {
        System.out.println(Part1(GetPuzzleInput()));
        System.out.println(Part2(GetPuzzleInput()));
    }

    public static int Part1(String[] instructions){
        Map<Character, Integer> registers = new HashMap<>();
        registers.put('a', 0);
        registers.put('b', 0);
        registers.put('c', 0);
        registers.put('d', 0);
        return Solve(instructions, registers);
    }

    public static int Part2(String[] instructions){
        Map<Character, Integer> registers = new HashMap<>();
        registers.put('a', 0);
        registers.put('b', 0);
        registers.put('c', 1);
        registers.put('d', 0);
        return Solve(instructions, registers);
    }

    public static int Solve(String[] instructions, Map<Character,Integer> registers){
        for (int i = 0; i < instructions.length; i++){
            switch (instructions[i].substring(0, 3)) {
                case "cpy" -> {
                    String[] parts = instructions[i].split(" ");
                    char x = parts[1].charAt(0);
                    char y = parts[2].charAt(0);

                    try {
                        int value = Integer.parseInt(parts[1]);
                        registers.put(y, value);
                    } catch (NumberFormatException e) {
                        registers.put(y, registers.get(x));
                    }
                }
                case "inc" -> {
                    String[] parts = instructions[i].split(" ");
                    registers.put(parts[1].charAt(0), registers.get(parts[1].charAt(0)) + 1);
                }
                case "dec" -> {
                    String[] parts = instructions[i].split(" ");
                    registers.put(parts[1].charAt(0), registers.get(parts[1].charAt(0)) - 1);
                }
                case "jnz" -> {
                    String[] parts = instructions[i].split(" ");
                    int x;
                    try {
                        x = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        x = registers.get(parts[1].charAt(0));
                    }
                    if (x != 0) {
                        i += Integer.parseInt(parts[2]) - 1;
                    }
                }
            }
        }
        return registers.get('a');
    }

    public static String[] GetPuzzleInput(){
        try {
            String[] input = Files.readAllLines(Paths.get("Day_12.txt")).toArray(String[]::new );
            return input;
        } catch (IOException e) {
            return null;
        }
    }

}
