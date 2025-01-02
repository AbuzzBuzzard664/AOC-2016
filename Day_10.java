import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_10 {

    public static Map<Integer, List<Integer>> bot = new HashMap<>();
    public static Map<Integer, List<Integer>> output = new HashMap<>();
    public static Map<Integer, String[]> pipeline = new HashMap<>();

    public static void main(String[] args) throws IOException {
        GetPuzzleInput();
        System.out.println("Part 1: " + Part1());
        System.out.println("Part 2: " + Part2());
    }

    public static int Part1() {
        int answer = -1;
        while (!bot.isEmpty()) {
            Map<Integer, List<Integer>> botCopy = new HashMap<>(bot);
            for (Map.Entry<Integer, List<Integer>> entry : botCopy.entrySet()) {
                int k = entry.getKey();
                List<Integer> v = entry.getValue();
                if (v.size() == 2) {
                    Collections.sort(v);
                    int v1 = v.get(0);
                    int v2 = v.get(1);
                    bot.remove(k);
                    if (v1 == 17 && v2 == 61) {
                        answer = k;
                    }
                    String[] instructionsArray = pipeline.get(k);
                    if (instructionsArray != null) {
                        String t1 = instructionsArray[0];
                        int n1 = Integer.parseInt(instructionsArray[1]);
                        String t2 = instructionsArray[2];
                        int n2 = Integer.parseInt(instructionsArray[3]);

                        if (t1.equals("bot")) {
                            bot.computeIfAbsent(n1, x -> new ArrayList<>()).add(v1);
                        } else {
                            output.computeIfAbsent(n1, x -> new ArrayList<>()).add(v1);
                        }

                        if (t2.equals("bot")) {
                            bot.computeIfAbsent(n2, x -> new ArrayList<>()).add(v2);
                        } else {
                            output.computeIfAbsent(n2, x -> new ArrayList<>()).add(v2);
                        }
                    }
                }
            }
        }
        return answer;
    }

    public static int Part2() {
        int a = output.get(0).get(0);
        int b = output.get(1).get(0);
        int c = output.get(2).get(0);
        return a * b * c;
    }

    public static void GetPuzzleInput() throws IOException {
        List<String> instructions = Files.readAllLines(Paths.get("Day_10.txt"));

        Pattern valuePattern = Pattern.compile("value (\\d+) goes to bot (\\d+)");
        Pattern botPattern = Pattern.compile("bot (\\d+) gives low to (bot|output) (\\d+) and high to (bot|output) (\\d+)");

        for (String line : instructions) {
            Matcher valueMatcher = valuePattern.matcher(line);
            Matcher botMatcher = botPattern.matcher(line);

            if (valueMatcher.find()) {
                int n = Integer.parseInt(valueMatcher.group(1));
                int b = Integer.parseInt(valueMatcher.group(2));
                bot.computeIfAbsent(b, k -> new ArrayList<>()).add(n);
            } else if (botMatcher.find()) {
                int who = Integer.parseInt(botMatcher.group(1));
                String t1 = botMatcher.group(2);
                int n1 = Integer.parseInt(botMatcher.group(3));
                String t2 = botMatcher.group(4);
                int n2 = Integer.parseInt(botMatcher.group(5));
                pipeline.put(who, new String[]{t1, String.valueOf(n1), t2, String.valueOf(n2)});
            }
        }
    }
}