import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day_09 {
    

    public static void main (String[] args){
        System.out.println("Part 1: " + Part1());
        System.out.println("Part 2: " + Part2());
    }

    public static long Part1(){
        return decompress(GetPuzzleInput(), false);
    }

    public static long decompress(String s, boolean part2) {
        if (!s.contains("(")) {
            return s.length();
        }
        long ret = 0;
        while (s.contains("(")) {
            ret += s.indexOf('(');
            s = s.substring(s.indexOf('('));
            String marker = s.substring(1, s.indexOf(')'));
            String[] parts = marker.split("x");
            int length = Integer.parseInt(parts[0]);
            int repeat = Integer.parseInt(parts[1]);
            s = s.substring(s.indexOf(')') + 1);
            if (part2) {
                ret += decompress(s.substring(0, length), true) * repeat;
            } else {
                ret += s.substring(0, length).length() * repeat;
            }
            s = s.substring(length);
        }
        ret += s.strip().length();
        return ret;
    }

    public static long Part2(){
        return decompress(GetPuzzleInput(), true);
    }

    public static String GetPuzzleInput(){
        try {
            return Files.readAllLines(Path.of("Day_09.txt")).toArray(String[] :: new)[0];

        } catch (IOException e) {
            return  null;
        }
    }
}
