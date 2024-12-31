import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day_06 {
    
    public static void main(String[] args){
        System.out.println("Part 1: " + Part1());
        System.out.println("Part 1: " + Part2());
    }

    public static String Part1(){
        String[] input = GetPuzzleInput();
        String password = "";
        Map<Character, Integer> counts = new HashMap<>();
        for(char c = 'a'; c <= 'z'; c++){
            counts.put(c, 0);
        }
        for(int i = 0; i < input[0].length(); i++){
            for(String s: input){
                counts.replace(s.charAt(i), counts.get(s.charAt(i))+1);
            }
            char maxChar = Collections.max(counts.entrySet(), Map.Entry.comparingByValue()).getKey();
            password += maxChar;
            counts.clear();
            for(char c = 'a'; c <= 'z'; c++){
                counts.put(c, 0);
            }
        }
        return password;
    }

    public static String Part2(){
        String[] input = GetPuzzleInput();
        String password = "";
        Map<Character, Integer> counts = new HashMap<>();
        for(char c = 'a'; c <= 'z'; c++){
            counts.put(c, 0);
        }
        for(int i = 0; i < input[0].length(); i++){
            for(String s: input){
                counts.replace(s.charAt(i), counts.get(s.charAt(i))+1);
            }
            char maxChar = Collections.min(counts.entrySet(), Map.Entry.comparingByValue()).getKey();
            password += maxChar;
            counts.clear();
            for(char c = 'a'; c <= 'z'; c++){
                counts.put(c, 0);
            }
        }
        return password;
    }

    public static String[] GetPuzzleInput(){
        try {
            List<String> input = Files.readAllLines(Paths.get("Day_06.txt"));
            return input.toArray(String[]::new);
        } catch (IOException e) {
            return new String[0];
        }
    }

}
