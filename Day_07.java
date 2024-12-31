import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day_07 {
    public static void main(String[] args) {
        System.out.println("Part 1: " + Part1());
        System.out.println("Part 1: " + Part2());
    }

    public static int Part1() {
        String[] input = GetPuzzleInput();
        int answer = 0;
        for (String s : input) {
            answer += IsTLS(s)? 1: 0;
        }
        return answer;
    }

    public static int Part2(){
        String[] input = GetPuzzleInput();
        int answer = 0;
        for (String s: input){
            answer += IsSSL(s)? 1: 0;
        }
        return answer;
    }

    public static boolean IsTLS(String iP) {
        String temp = "";
        List<String> subNets = new ArrayList<>(), hyperNets = new ArrayList<>();
        for(char c: iP.toCharArray()) {
            if(c!= '[' && c !=']') {
                temp+=c + "";
            }
            else if(c == '[') {
                subNets.add(temp);
                temp = "";
            }
            else if (c == ']') {
                hyperNets.add(temp);
                temp = "";
            }
        }
        subNets.add(temp);
        for(String s: hyperNets){
            if(CheckABBA(s)) return false;
        }
        for(String s: subNets){
            if (CheckABBA(s)) return true;
        }
        return false;
    }

    public static boolean CheckABBA(String s){
        char[] word = s.toCharArray();
        for(int i = 0; i < s.length()-3; i++){
            if (word[i] != word[i+1]){
                if(word[i] == word[i+3] && word[i+1] == word[i+2]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean IsSSL(String iP){
        String temp = "";
        List<String> subNets = new ArrayList<>(), hyperNets = new ArrayList<>();
        for(char c: iP.toCharArray()) {
            if(c!= '[' && c !=']') {
                temp+=c + "";
            }
            else if(c == '[') {
                subNets.add(temp);
                temp = "";
            }
            else if (c == ']') {
                hyperNets.add(temp);
                temp = "";
            }
        }
        subNets.add(temp);
        List<Character> savedChars = new ArrayList<>();
        for (String s: subNets){
            char[] word = s.toCharArray();
            for(int i = 0; i < word.length-2; i++){
                if(word[i] == word[i+2] && word[i]!= word[i+1]){
                    savedChars.add(word[i]);
                    savedChars.add(word[i+1]);
                }
            }
        }
        for(String s: hyperNets){
            char[] word = s.toCharArray();
            for(int i =0 ;i < word.length-2; i++){
                if(word[i] == word[i+2] && word[i] != word[i+1]){
                    for(int j = 0; j < savedChars.size(); j+=2){
                        if(word[i] == savedChars.get(j+1) && word[i+1] == savedChars.get(j)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static String[] GetPuzzleInput() {
        try {
            return Files.readAllLines(Paths.get("Day_07.txt")).toArray(String[] :: new);
        } catch (IOException e) {
            return new String[0];
        }
    }
}
