import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Day_17 {
    public static class State{
        public Point p;
        public String hashCode;
        public String shortestPath;
        public State(Point p, String lastHash, String shortestPath){
            this.p = p;
            hashCode = MD5Hash(lastHash);
            this.shortestPath = shortestPath;
        }

        private String MD5Hash(String string) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] hashInBytes = md.digest(string.getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for (byte b : hashInBytes) {
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        public List<State> GetAdj() {
            List<State> adjList = new ArrayList<>();
            int[] directions = {1, 0, 0, 1, -1, 0, 0, -1};
            for (int i = 0; i < 8; i += 2) {
                int nx = p.x + directions[i];
                int ny = p.y + directions[i + 1];
                if (IsValid(new Point(nx, ny), (i+1)/2 + 1 )) {
                    adjList.add(new State(new Point(nx, ny), hashCode, shortestPath));
                }
            }
            return adjList;
        }

        private boolean IsValid(Point p, int index) {
            if (p.x >= 0 && p.y >= 0) {
                if("bcdef".contains(hashCode.substring(index, index+1))) {
                    return true;
                }
            }
            return false;
        }
    }

    public static String input = "pgflpeqp";
    public static void main(String[] args) {

    }

    public static String Part(){
        String output = BFS()
    }

    public static String BFS(Point start, Point end) {
        String shortPath = "";
        Queue<State> frontier = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        frontier.add(new State(start, input, ""));
        while (!frontier.isEmpty()) {
            State current = frontier.poll();
            if (current.p.equals(end)) {
                return current.shortestPath;
            }
            if (!visited.contains(current.p)) {
                visited.add(current.p);
                for (Point nodes : current.GetAdj()) {
                    frontier.add(new State(nodes, ));
                }
            }
        }
        return null;
    }

}
