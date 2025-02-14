import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day_18 {
    public static void main(String[] args) {
        System.out.println(Part1());
        System.out.println(Part2());
    }

    public static int Part1(){
        String firstLine = GetPuzzleInput();
        String[] map = new String[40];
        map[0] = firstLine;
        for(int i = 1; i < map.length; i++) {
            map[i]="";
            for(int j = 0; j < map[0].length(); j++){
                map[i] += IsTrap(map, i-1, j)? "^": ".";
            }
        }
        int safes = 0;
        for(String s: map){
            safes += s.replace("^", "").length();
        }
        return safes;
    }

    public static boolean IsTrap(String[] map, int row, int col){
        if(col == 0){
            if(map[row].charAt(col+1) == '^') return true;
        }
        else if (col == map[row].length()-1) {
            if(map[row].charAt(col-1) == '^') return true;
        }
        else{
            if(map[row].charAt(col+1) != map[row].charAt(col-1)) return true;
        }
        return false;
    }

    public static int Part2(){
        String firstLine = GetPuzzleInput();
        String[] map = new String[400000];
        map[0] = firstLine;
        for(int i = 1; i < map.length; i++) {
            map[i]="";
            for(int j = 0; j < map[0].length(); j++){
                map[i] += IsTrap(map, i-1, j)? "^": ".";
            }
        }
        int safes = 0;
        for(String s: map){
            safes += s.replace("^", "").length();
        }
        return safes;
    }

    public static String GetPuzzleInput(){
        try {
            return Files.readAllLines(Paths.get("Day_18.txt")).toArray(String[]::new)[0];
        } catch (IOException e) {
            return null;
        }
    }
}
