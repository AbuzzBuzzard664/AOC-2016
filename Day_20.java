import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day_20 {
    public static void main(String[] args) {
        System.out.println(Part1());   
        System.out.println(Part2());
    }

    public static long Part1() {
        List<long[]> ranges = GetPuzzleInput();
        ranges.sort(Comparator.comparingLong(a -> a[0]));
        long lowestAllowed = 0;
        for (long[] range : ranges) {
            if (lowestAllowed < range[0]) {
                return lowestAllowed;
            } else {
                lowestAllowed = Math.max(lowestAllowed, range[1] + 1);
            }
        }
        return lowestAllowed;
        }

        public static long Part2() {
        List<long[]> ranges = GetPuzzleInput();
        ranges.sort(Comparator.comparingLong(a -> a[0]));
        long lowestAllowed = 0;
        long allowedCount = 0;
        for (long[] range : ranges) {
            if (lowestAllowed < range[0]) {
            allowedCount += range[0] - lowestAllowed;
            }
            lowestAllowed = Math.max(lowestAllowed, range[1] + 1);
        }
        return allowedCount;
        }

        public static List<long[]> GetPuzzleInput() {
        try {
            List<long[]> ranges = new ArrayList<>();
            for(String line: Files.readAllLines(Paths.get("Day_20.txt"))) {
                String[] parts = line.split("-");
                ranges.add(new long[]{Long.parseLong(parts[0]), Long.parseLong(parts[1])});
            }
            return ranges;
        } catch (IOException e) {
            return null;
        }
    }

}
