import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Day_17 {
    public static class State {
        Point p;
        String path;
        String shortestPath;

        public State(Point p, String input, String path) {
            this.p = p;
            this.path = path;
            this.shortestPath = path;
        }

        public List<State> GetAdj() {
            List<State> adj = new ArrayList<>();
            String hash = md5Hash(input + path);
            if (isOpen(hash.charAt(0)) && p.y > 0) {
                adj.add(new State(new Point(p.x, p.y - 1), input, path + "U"));
            }
            if (isOpen(hash.charAt(1)) && p.y < 3) {
                adj.add(new State(new Point(p.x, p.y + 1), input, path + "D"));
            }
            if (isOpen(hash.charAt(2)) && p.x > 0) {
                adj.add(new State(new Point(p.x - 1, p.y), input, path + "L"));
            }
            if (isOpen(hash.charAt(3)) && p.x < 3) {
                adj.add(new State(new Point(p.x + 1, p.y), input, path + "R"));
            }
            return adj;
        }

        public boolean isOpen(char c) {
            return "bcdef".indexOf(c) != -1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return p.equals(state.p) && path.equals(state.path);
        }

        @Override
        public int hashCode() {
            return Objects.hash(p, path);
        }
    }

    private static String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String input = "pgflpeqp";
    public static void main(String[] args) {
        System.out.println(Part1());
        System.out.println(Part2());
    }

    public static String Part1(){
        Point start = new Point(0,0);
        Point end = new Point(3,3);
        String output = BFS(start, end);
        return  output;
    }

    public static int Part2(){
        Point start = new Point(0,0);
        Point end = new Point(3,3);
        int output = LongPass(start, end);
        return output;
    }

    public static String BFS(Point start, Point end) {
        Queue<State> frontier = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        frontier.add(new State(start, input, ""));
        while (!frontier.isEmpty()) {
            State current = frontier.poll();
            if (current.p.equals(end)) {
                return current.shortestPath;
            }
            if (!visited.contains(current)) {
                visited.add(current);
                for (State nodes : current.GetAdj()) {
                    frontier.add(nodes);
                }
            }
        }
        return null;
    }

    public static int LongPass(Point start, Point end) {
        Queue<State> frontier = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        Set<Integer> costs = new HashSet<>();
        frontier.add(new State(start, input, ""));
        while(!frontier.isEmpty()){
            State current = frontier.poll();
            if(current.p.equals(end)){
                costs.add(current.path.length());
            }
            else if(!visited.contains(current)){
                visited.add(current);
                for(State node : current.GetAdj()) {
                    frontier.add(node);
                }
            }
        }
        return Collections.max(costs);
    }

}
