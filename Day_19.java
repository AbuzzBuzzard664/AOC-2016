import java.util.Arrays;

public class Day_19 {
    
    public static int numofElves = 3017957;
    
    public static void main(String[] args) {
        System.out.println(Part1());
        System.out.println(Part2());
    }

    public static int Part1(){
        boolean[] elves = new boolean[numofElves];
        Arrays.fill(elves, true);
        int remainingElves = numofElves;
        int currentIndex = 0;

        while (remainingElves > 1) {
            int nextIndex = (currentIndex + 1) % numofElves;
            while (!elves[nextIndex]) {
                nextIndex = (nextIndex + 1) % numofElves;
            }
            elves[nextIndex] = false;
            remainingElves--;

            do {
                currentIndex = (currentIndex + 1) % numofElves;
            } while (!elves[currentIndex]);
        }

        return currentIndex + 1;
    }

    public static int Part2() {
        int[] elves = new int[numofElves];
        for (int i = 0; i < numofElves; i++) {
            elves[i] = i + 1;
        }
        
        int currentIndex = 0;
        int remainingElves = numofElves;
        while (remainingElves > 1) {
            int removeIndex = (currentIndex + remainingElves / 2) % remainingElves;
            for (int i = removeIndex; i < remainingElves - 1; i++) {
                elves[i] = elves[i + 1];
            }
            remainingElves--;
            if (removeIndex > currentIndex) {
                currentIndex++;
            }
            currentIndex %= remainingElves;
        }
        
        return elves[0];
    }

}
