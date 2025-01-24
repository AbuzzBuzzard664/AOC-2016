import java.util.*;
public class Day_13 {

    public static class State {
        public Point p;
        public int cost;

        public State(Point p, int cost) {
            this.p = p;
            this.cost = cost;
        }

        public List<Point> GetAdj() {
            List<Point> adjList = new ArrayList<>();
            int[] directions = {1, 0, 0, 1, -1, 0, 0, -1};
            for (int i = 0; i < 8; i += 2) {
                int nx = p.x + directions[i];
                int ny = p.y + directions[i + 1];
                if (IsValid(nx, ny)) {
                    adjList.add(new Point(nx, ny));
                }
            }
            return adjList;
        }

        private boolean IsValid(int x, int y) {
            if (x >= 0 && y >= 0) {
                int val = x * x + 3 * x + 2 * x * y + y + y * y + input;
                int ones = Integer.bitCount(val);
                return ones % 2 == 0;
            }
            return false;
        }
    }

    public static int input = 1350;

    public static void main(String[] args) {
        System.out.println(Part1());
        System.out.println(Part2());
    }

    public static int Part1() {
        return BFS1(new Point(1, 1), new Point(31, 39));
    }

    public static int Part2() {
        int res = 0;
        for(int i = 0; i <= 50; i++){
            res += BFS2(new Point(1,1), i);
        }
        return res;
    }

    public static int BFS1(Point start, Point end) {
        Queue<State> frontier = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        frontier.add(new State(start, 0));
        while (!frontier.isEmpty()) {
            State current = frontier.poll();
            if (current.p.equals(end)) {
                return current.cost;
            }
            if (!visited.contains(current.p)) {
                visited.add(current.p);
                for (Point nodes : current.GetAdj()) {
                    frontier.add(new State(nodes, current.cost + 1));
                }
            }
        }
        return -1;
    }

    public static int BFS2(Point start, int maxCost) {
        Queue<State> frontier = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        int answer = 0;
        frontier.add(new State(start, 0));
        while (!frontier.isEmpty()) {
            State current = frontier.poll();
            if (visited.contains(current.p)) {
                continue;
            }
            visited.add(current.p);
            if (current.cost == maxCost) {
                answer++;
            } else if (current.cost < maxCost) {
                for (Point nodes : current.GetAdj()) {
                    frontier.add(new State(nodes, current.cost + 1));
                }
            }
        }
        return answer;
    }
}