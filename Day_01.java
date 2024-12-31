import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

public class Day_01 {

    public static char[] directions = {'N', 'E', 'S', 'W'};

    public static void main(String[] args) {
        System.out.println("Part 1: " + Part1());
        System.out.println("Part 2: " + Part2());
        }

        public static int Part1() {
            String[] moves = GetPuzzleInput();
            char curDirection = 'N';
            Point answer = new Point(0,0);
            for (String move : moves) {
                curDirection = GetDirection(curDirection, move.charAt(0));
                answer = Move(move, answer, curDirection);
            }
            return Math.abs(answer.x) + Math.abs(answer.y);
        }

        public static int Part2() {
            String[] moves = GetPuzzleInput();
            char curDirection = 'N';
            Point answer = new Point(0, 0);
            HashSet<Point> visited = new HashSet<>();
            visited.add(new Point(0, 0));
            for (String move : moves){
                curDirection = GetDirection(curDirection, move.charAt(0));
                int steps = Integer.parseInt(move.substring(1));
                for (int i = 0; i < steps; i++) {
                    answer = Move("R1", answer, curDirection);
                    if (visited.contains(new Point(answer.x, answer.y))) {
                        return Math.abs(answer.x) + Math.abs(answer.y);
                    }
                    visited.add(new Point(answer.x, answer.y));
                }
            }
            return -1;
        }

    public static char GetDirection(char curDirection, char turn){
        int index = 0;
        for (int i = 0; i < directions.length; i++) {
            if (directions[i] == curDirection) {
                index = i;
                break;
            }
        }
        if (turn == 'R'){
            return directions[(index + 1) % 4];
        }
        else if (turn == 'L'){
            return directions[(index + 3) % 4];
        }
        return 'N';
    }

    public static Point Move(String move, Point curPoint, char curDirection){
        switch (curDirection) {
            case 'N' -> curPoint.y += Integer.parseInt(move.substring(1));
            case 'E' -> curPoint.x += Integer.parseInt(move.substring(1));
            case 'S' -> curPoint.y -= Integer.parseInt(move.substring(1));
            case 'W' -> curPoint.x -= Integer.parseInt(move.substring(1));
        }
        return curPoint;
    }

    public static String[] GetPuzzleInput() {
        try{
        String input = Files.readString(Paths.get("Day_01.txt"), Charset.defaultCharset());
        return input.split(", ");
        }
        catch (IOException e) {
            return null;
        }
    }
}


