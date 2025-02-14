import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Day_22 {
    // Node class represents a node in the grid with size, used, and available space
    public static class Node extends Point {
        final int size;
        final int used;
        final int avail;

        public Node(int x, int y, int size, int used, int avail) {
            super(x, y);
            this.size = size;
            this.used = used;
            this.avail = avail;
        }
    }

    private static List<Node> GetPuzzleInput() {
        try {
            List<String> input = Files.readAllLines(Paths.get("Day_22.txt"));
            return (List<Node>) input.stream().map(s -> {
                Matcher m = Pattern.compile("x(\\d+)-y(\\d+)\\s+(\\d+)T\\s+(\\d+)T\\s+(\\d+)T").matcher(s);
                if (m.find()) {
                    return new Node(
                        Integer.parseInt(m.group(1)),
                        Integer.parseInt(m.group(2)), 
                        Integer.parseInt(m.group(3)), 
                        Integer.parseInt(m.group(4)), 
                        Integer.parseInt(m.group(5))  
                    );
                } else {
                    return null;
                }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        }catch (IOException e){
            return null;
        }
    }
    private static long Part1(List<Node> nodes) {
        return nodes.stream()
            .filter(n -> n.used > 0) 
            .mapToLong(n -> nodes.stream()
                .filter(other -> !n.equals(other) && other.avail >= n.used)
                .count())
            .sum();
    }

    private static long Part2(List<Node> nodes) {
        int x_size = nodes.stream().max(Comparator.comparingInt(n -> n.x)).get().x;
        int y_size = nodes.stream().max(Comparator.comparingInt(n -> n.y)).get().y;
        Node wStart = null, hole = null;
        Node[][] grid = new Node[x_size + 1][y_size + 1];

        nodes.forEach(n -> grid[n.x][n.y] = n);

        for (int x = 0; x <= x_size; x++) {
            for (int y = 0; y <= y_size; y++) {
                Node n = grid[x][y];
                if (n.used == 0) {
                    hole = n; 
                } else if (n.size > 250) {
                    if (wStart == null) {
                        wStart = grid[x - 1][y]; 
                    }
                }
            }
        }

        int steps = Math.abs(hole.x - wStart.x) + Math.abs(hole.y - wStart.y);
        steps += Math.abs(wStart.x - x_size) + wStart.y;
        return steps + 5 * (x_size - 1);
    }

    public static void main(String[] args) {
        // Read input from file
        List<Node> nodes = GetPuzzleInput();
        // Print results for part one and part two
        System.out.println(Part1(nodes));
        System.out.println(Part2(nodes));
    }
}