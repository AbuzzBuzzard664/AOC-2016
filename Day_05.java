import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Day_05 {

    public static void main(String[] args) {
        System.out.println("Part 1: " + Part1());
        System.out.println("Part 2: " + Part2());
    }

    public static String Part1(){
        String input = GetPuzzleInput();
        int i = 0;
        String Password = "";
        while (Password.length()< 8) {
            String hash = MD5Hash((input + i));
            if(hash.startsWith("00000")){
                Password+= hash.charAt(5);
            }
            i++;
        }
        return Password;
    }


    public static String Part2(){
        String input = GetPuzzleInput();
        int i = 0, digitsFound = 0;
        String Password = "xxxxxxxx";
        char[] passwordArr = "xxxxxxxx".toCharArray();
        while (digitsFound < 8) {
            String hash = MD5Hash(input+i);
            if (hash.startsWith(("00000"))){
                int pos = Character.getNumericValue(hash.charAt(5));
                if ((pos <8) && (passwordArr[pos] == 'x')) {
                    passwordArr[pos] = hash.charAt(6);
                    Password = Password.substring(0, pos) + passwordArr[pos] + Password.substring(pos+1);
                    digitsFound++;
                }
            }
            i++;
        }
        return Password;
    }

    public static String MD5Hash(String string) {
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

    public static String GetPuzzleInput() {
        try {
            List<String> input = Files.readAllLines(Paths.get("Day_05.txt"));
            return input.get(0); 
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
