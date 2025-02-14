import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day_21 {
    public static void main(String[] args) {
        System.out.println(Part1());
        System.out.println(Part2());
    }
    
    public static String Part1(){
        String[] input = GetPuzzleInput();
        String password = "abcdefgh";
        
        for (String line : input) {
            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "swap":
                    if (parts[1].equals("position")) {
                        int x = Integer.parseInt(parts[2]);
                        int y = Integer.parseInt(parts[5]);
                        password = SwapPosition(password, x, y);
                    } else if (parts[1].equals("letter")) {
                        char x = parts[2].charAt(0);
                        char y = parts[5].charAt(0);
                        password = SwapLetter(password, x, y);
                    }
                    break;
                case "rotate":
                    if (parts[1].equals("left")) {
                        int steps = Integer.parseInt(parts[2]);
                        password = RotateLeft(password, steps);
                    } else if (parts[1].equals("right")) {
                        int steps = Integer.parseInt(parts[2]);
                        password = RotateRight(password, steps);
                    } else if (parts[1].equals("based")) {
                        char x = parts[6].charAt(0);
                        password = RotateBasedOnPosition(password, x);
                    }
                    break;
                case "reverse":
                    int x = Integer.parseInt(parts[2]);
                    int y = Integer.parseInt(parts[4]);
                    password = ReversePositions(password, x, y);
                    break;
                case "move":
                    int from = Integer.parseInt(parts[2]);
                    int to = Integer.parseInt(parts[5]);
                    password = MovePosition(password, from, to);
                    break;
                default:
                    break;
            }
        }
        
        return password;
        }

    private static String SwapPosition(String password, int x, int y) {
        char[] chars = password.toCharArray();
        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
        return new String(chars);
        }

    private static String SwapLetter(String password, char x, char y) {
        return password.replace(x, '\0').replace(y, x).replace('\0', y);
        }

    private static String RotateLeft(String password, int steps) {
        int len = password.length();
        steps = steps % len;
        return password.substring(steps) + password.substring(0, steps);
        }

    private static String RotateRight(String password, int steps) {
        int len = password.length();
        steps = steps % len;
        return password.substring(len - steps) + password.substring(0, len - steps);
        }

    private static String RotateBasedOnPosition(String password, char x) {
        int index = password.indexOf(x);
        int steps = 1 + index + (index >= 4 ? 1 : 0);
        return RotateRight(password, steps);
        }

    private static String ReversePositions(String password, int x, int y) {
        StringBuilder sb = new StringBuilder(password.substring(x, y + 1));
        sb.reverse();
        return password.substring(0, x) + sb.toString() + password.substring(y + 1);
        }

    private static String MovePosition(String password, int from, int to) {
        char ch = password.charAt(from);
        StringBuilder sb = new StringBuilder(password);
        sb.deleteCharAt(from);
        sb.insert(to, ch);
        return sb.toString();
        }
        
    public static String Part2() {
        String[] input = GetPuzzleInput();
        String password = "fbgdceah";

        for (int i = input.length - 1; i >= 0; i--) {
            String line = input[i];
            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "swap":
                    if (parts[1].equals("position")) {
                        int x = Integer.parseInt(parts[2]);
                        int y = Integer.parseInt(parts[5]);
                        password = SwapPosition(password, x, y);
                    } else if (parts[1].equals("letter")) {
                        char x = parts[2].charAt(0);
                        char y = parts[5].charAt(0);
                        password = SwapLetter(password, x, y);
                    }
                    break;
                case "rotate":
                    if (parts[1].equals("left")) {
                        int steps = Integer.parseInt(parts[2]);
                        password = RotateRight(password, steps);
                    } else if (parts[1].equals("right")) {
                        int steps = Integer.parseInt(parts[2]);
                        password = RotateLeft(password, steps);
                    } else if (parts[1].equals("based")) {
                        char x = parts[6].charAt(0);
                        password = reverseRotateBasedOnPosition(password, x);
                    }
                    break;
                case "reverse":
                    int x = Integer.parseInt(parts[2]);
                    int y = Integer.parseInt(parts[4]);
                    password = ReversePositions(password, x, y);
                    break;
                case "move":
                    int from = Integer.parseInt(parts[5]);
                    int to = Integer.parseInt(parts[2]);
                    password = MovePosition(password, from, to);
                    break;
                default:
                    break;
            }
        }

        return password;
    }

        private static String reverseRotateBasedOnPosition(String password, char x) {
            int index = password.indexOf(x);
            int[] steps = {1, 1, 6, 2, 7, 3, 0, 4}; // Precomputed steps for reverse rotation
            return RotateLeft(password, steps[index]);
        }
    
    public static String[] GetPuzzleInput(){
        try {
            return Files.readAllLines(Paths.get("Day_21.txt")).toArray(String[]::new);
        } catch (IOException e) {
            return new String[0];
        }
    }
}