import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Day_02 {
    public static Map<Point, Character> keyPad = new HashMap<>();
    public static void main(String[] args) {
        keyPad.put(new Point(-1, 1), '1');
        keyPad.put(new Point(0, 1), '2');
        keyPad.put(new Point(1, 1), '3');
        keyPad.put(new Point(-1, 0), '4');
        keyPad.put(new Point(0, 0), '5');
        keyPad.put(new Point(1, 0), '6');
        keyPad.put(new Point(-1, -1), '7');
        keyPad.put(new Point(0, -1), '8');
        keyPad.put(new Point(1, -1), '9');
        System.out.println("Part 1: " + Solve(false));
        keyPad.clear();
        keyPad.put(new Point(0, 2), '1');
        keyPad.put(new Point(-1, 1), '2');
        keyPad.put(new Point(0, 1), '3');
        keyPad.put(new Point(1, 1), '4');
        keyPad.put(new Point(-2, 0), '5');
        keyPad.put(new Point(-1, 0), '6');
        keyPad.put(new Point(0, 0), '7');
        keyPad.put(new Point(1, 0), '8');
        keyPad.put(new Point(2, 0), '9');
        keyPad.put(new Point(-1, -1), 'A');
        keyPad.put(new Point(0, -1), 'B');
        keyPad.put(new Point(1, -1), 'C');
        keyPad.put(new Point(0, -2), 'D');
        System.out.println("Part 2: " + Solve(true));
    }

    public static String Solve(boolean part2) {
        String[] sequence = GetPuzzleInput();
        String code = "";
        Point curPoint = new Point(part2? -2: 0, 0);
        for (String s: sequence) {
            for (char c: s.toCharArray()) {
                switch (c) {
                    case 'U' -> {
                        Point up = new Point(curPoint.x, curPoint.y + 1);
                        if (keyPad.containsKey(up)) {
                            curPoint = up;
                        }
                    }
                    case 'D' -> {
                        Point down = new Point(curPoint.x, curPoint.y - 1);
                        if (keyPad.containsKey(down)) {
                            curPoint = down;
                        }
                    }
                    case 'L' -> {
                        Point left = new Point(curPoint.x - 1, curPoint.y);
                        if (keyPad.containsKey(left)) {
                            curPoint = left;
                        }
                    }
                    case 'R' -> {
                        Point right = new Point(curPoint.x + 1, curPoint.y);
                        if (keyPad.containsKey(right)) {
                            curPoint = right;
                        }
                    }
                }
            }
            code += keyPad.get(curPoint);
        }
        return code;
    }

    public static String[] GetPuzzleInput(){
        try{
        String input = Files.readString(Paths.get("Day_02.txt"), Charset.defaultCharset());
        return input.split("\n");
        }
        catch (IOException e) {
            return null;
        }
    }
}
