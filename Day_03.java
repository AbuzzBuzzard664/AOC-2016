import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day_03 {
    
    public static class Triangle {
        public int a, b, c;
    
        public Triangle(int x, int y, int z) {
            a = x;
            b = y;
            c = z;
        }
    
        public boolean IsTriangle(){
            return a + b > c && a + c > b && b + c > a;
        }
    }
    

    public static void main(String[] args) {
        System.out.println("Part 1: " + Part1());
        System.out.println("Part 2: " + Part2());
    }

    public static int Part1(){
        Triangle[] triangles = GetPuzzleInput();
        int answer = 0;
        for (Triangle triangle : triangles){
            answer += triangle.IsTriangle()? 1:0;
        }
        return answer;
        }

        public static int Part2(){
            Triangle[] triangles = GetPuzzleInput();
            int answer = 0;
            for (int i = 0; i < triangles.length; i += 3){
                if (i + 2 < triangles.length) {
                    Triangle temp = new Triangle(triangles[i].a, triangles[i+1].a, triangles[i+2].a);
                    answer += temp.IsTriangle()? 1:0;
                    temp = new Triangle(triangles[i].b, triangles[i+1].b, triangles[i+2].b);
                    answer += temp.IsTriangle()? 1:0;
                    temp = new Triangle(triangles[i].c, triangles[i+1].c, triangles[i+2].c);
                    answer += temp.IsTriangle()? 1:0;
                }
            }
            return answer;
        }

    public static Triangle[] GetPuzzleInput(){     
        try{
            String input = Files.readString(Paths.get("Day_03.txt"), Charset.defaultCharset());
            String[] lines = input.split("\n");
            Triangle[] triangles = new Triangle[lines.length];
            for(int i = 0; i < triangles.length; i++){
                String[] parts = lines[i].trim().split("\\s+");
                int[] lengths = new int[3];
                for (int j = 0; j < parts.length; j++) {
                        lengths[j] = Integer.parseInt(parts[j]);
                }
                triangles[i] = new Triangle(lengths[0], lengths[1], lengths[2]);
            }
            return triangles;
        }
        catch (IOException e){
            return null;
        }
    }
}