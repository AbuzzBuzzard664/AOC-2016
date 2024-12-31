import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Day_04 {

    public static class Room{
        public String sequence= "";
        public String order = "";
        public int iD;
        private final boolean part2;
        public Map<Character, Integer> letterFrequency = new HashMap<>();
        
        public Room(String input, boolean part2){
            String id = "";
            boolean isorder =false;
            for(char c: input.toCharArray()){
                if (Character.isDigit(c)){
                    id+=c;
                }
                else if (c == '['){
                    isorder = true;
                }
                else if (c != '-' && c != ']'){
                    if(isorder) order += c;
                    else sequence += c;
                }
            }
            this.part2 = part2;
            iD = Integer.parseInt(id);
        }

        public void CaeserCipher(){
            char[] chars = sequence.toCharArray();
            List<Character> temp = new ArrayList<>();
            for(char c: chars) {
                int x = (c-97+iD)%26;
                temp.add((char)(97+x));
            }
            sequence = "";
            for(char c: temp) {
                sequence +=c;
            }
        }

        public Map<Character, Integer> GetFrequencies(){
            letterFrequency = new HashMap<>();
            for(char c: sequence.toCharArray()){
                if(!letterFrequency.containsKey(c)){
                    int value = 0;
                    for(char k: sequence.toCharArray()){
                        if (c == k){
                            value++;
                        }
                    }
                    letterFrequency.put(c, value);
                }
            }
            if(this.part2) CaeserCipher();
            return letterFrequency;
        }

        public boolean IsRealRoom(){
            for(char c: order.toCharArray()){
                char alphaFirst = ' ';
                for (char key : letterFrequency.keySet()) {
                    if (letterFrequency.get(key).equals(Collections.max(letterFrequency.values()))) {
                        if (alphaFirst > key || alphaFirst == ' '){
                        alphaFirst = key;
                        }
                    }
                }
                if (c != alphaFirst){
                    return false;
                }
                else{
                    letterFrequency.remove(c);
                }
            }
            return true;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Part 1: " + Part1());
        System.out.println("Part 2: " + Part2());
    }

    public static int Part1(){
        Room[] rooms = GetPuzzleInput(false);
        int answer = 0;
        for(Room room: rooms){
            if(room.IsRealRoom()){
                answer += room.iD;
            }
        }
        return answer;
    }

    public static int Part2(){
        Room[] rooms = GetPuzzleInput(true);
        for(Room room: rooms){
            if (room.sequence.startsWith("northpole")) return room.iD;
        }
        return -1;
    }

    public static Room[] GetPuzzleInput(boolean part){
        try{
            List<String> input = Files.readAllLines(Paths.get("Day_04.txt"));
            Room[] rooms = new Room[input.size()];
            for(int i = 0; i < rooms.length; i++){
                rooms[i] = new Room(input.get(i), part);
                rooms[i].letterFrequency = rooms[i].GetFrequencies();
            }
            return rooms;
        }
        catch (IOException e){
            return null;
        }
    }
}