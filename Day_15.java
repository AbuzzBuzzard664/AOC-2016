import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Day_15 {

    public static class Disk{
        public int positions, current;

        public Disk(String line){
            String[] parts = line.split(" ");
            this.positions = Integer.parseInt(parts[3]);
            this.current = Integer.parseInt(parts[parts.length-1].substring(0,parts[parts.length-1].length()-1));
        }

        public boolean isAligned(int time) {
            return (current + time) % positions == 0;
        }
    }

    public static void main(String[] args){
        System.out.println(Part1(GetPuzzleInput()));
        System.out.println(Part2(GetPuzzleInput()));
    }

    public static int Part1(Disk[] disks){
        return Solve(disks);
    }


    public static int Part2(Disk[] disks){
        List<Disk> tempDisks = new ArrayList<>();
        Collections.addAll(tempDisks, disks);
        tempDisks.add(new Disk("Disc #7 has 11 positions; at time=0, it is at position 0."));
        return Solve(tempDisks.toArray(Disk[]::new));
    }

    public static int Solve(Disk[] disks){
        int time = 0;
        int alligned = 0;
        while(true){
            for(int i = 0; i < disks.length; i++){
                if(disks[i].isAligned(i + 1)){
                    alligned++;
                }
            }
            if(alligned == disks.length){
                return time;
            }
            else{
                for (Disk disk : disks) {
                    disk.current += 1 % disk.positions;
                }
            }
            time++;
            alligned = 0;
        }
    }

    public static Disk[] GetPuzzleInput(){
        try {
            List<String> lines = Files.readAllLines(Paths.get("Day_15.txt"));
            Disk[] disks = new Disk[lines.size()];
            for (int i = 0; i < lines.size(); i++){
                disks[i] = new Disk(lines.get(i));
            }
            return disks;
        } catch (IOException e) {
            return null;
        }
    }
}
