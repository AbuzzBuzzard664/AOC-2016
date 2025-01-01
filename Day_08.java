import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day_08 {

    public static Map<Point, Boolean> map = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Part 1: " + Part1());
        System.out.println("Part 2:");
        Part2();
    }

    public static int Part1(){
        String[] lines = GetPuzzleInput();
        for (String line: lines){
            String[] parts = line.split(" ");
            switch (parts.length) {
                case 2 -> {
                    String[] dimensions = parts[1].split("x");
                    int endx = Integer.parseInt(dimensions[0]);
                    int endy = Integer.parseInt(dimensions[1]);
                    for (int x = 0; x < endx; x++) {
                        for (int y = 0; y < endy; y++) {
                            map.replace(new Point(x, y), true);
                        }
                    }
                }
                default -> {
                    switch (parts[1]) {
                        case "row" -> {
                            int row = Integer.parseInt(parts[2].split("=")[1]);
                            int shift = Integer.parseInt(parts[4]);
                            for (int i = 0; i < shift; i++) {
                                boolean temp = map.get(new Point(49, row));
                                for (int x = 49; x > 0; x--) {
                                    map.replace(new Point(x, row), map.get(new Point(x - 1, row)));
                                }
                                map.replace(new Point(0, row), temp);
                            }
                        }
                        case "column" -> {
                            int column = Integer.parseInt(parts[2].split("=")[1]);
                            int shift = Integer.parseInt(parts[4]);
                            for (int i = 0; i < shift; i++) {
                                boolean temp = map.get(new Point(column, 5));
                                for (int y = 5; y > 0; y--) {
                                    map.replace(new Point(column, y), map.get(new Point(column, y - 1)));
                                }
                                map.replace(new Point(column, 0), temp);
                            }
                        }
                    }
                }
            }
        }
        int answer = 0;
        for(int y= 0; y < 6; y++){
            for(int x = 0; x < 50; x++){
                answer += map.get(new Point(x, y))? 1: 0;
            }
        }
        return answer;
    }

    public static void Part2(){
        for(int y= 0; y < 6; y++){
            for(int x = 0; x < 50; x++){
                System.out.print(map.get(new Point(x, y))? "#": ".");
            }
            System.out.println();
        }
    }

    public static String[] GetPuzzleInput(){
        try {
            for(int x = 0; x < 50; x++){
                for (int y= 0; y < 6; y++){
                    Point temp = new Point(x, y);
                    map.put(temp, false);
                }
            }   
            return Files.readAllLines(Paths.get("Day_08.txt")).toArray(String[] :: new);
        } catch (IOException e) {
            return null;
        }
    }

}
  