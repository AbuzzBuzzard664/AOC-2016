import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_14 {
    public static String input = "zpqevtbw";

    public static void main(String[] args) {
        System.out.println("Part 1: " + solve(input, Day_14::getHashPart1));
        System.out.println("Part 2: " + solve(input, Day_14::getHashPart2));
    }

    public static String getHashPart1(String salt, int index) {
        return md5Hash(salt + index);
    }

    public static String getHashPart2(String salt, int index) {
        String hash = getHashPart1(salt, index);
        for (int i = 0; i < 2016; i++) {
            hash = md5Hash(hash);
        }
        return hash;
    }

    public static boolean findFiveRepeats(List<String> hashes, char ch) {
        String quintuple = String.valueOf(ch).repeat(5);
        for (String hash : hashes) {
            if (hash.contains(quintuple)) {
                return true;
            }
        }
        return false;
    }

    public static int solve(String salt, HashFunction hashFunction) {
        Pattern triple = Pattern.compile("(.)\\1\\1");
        List<String> hashes = new ArrayList<>();
        for (int x = 0; x <= 1000; x++) {
            hashes.add(hashFunction.apply(salt, x));
        }

        int index = 0;
        int found = 0;

        while (true) {
            Matcher md = triple.matcher(hashes.remove(0));
             if (md.find() && findFiveRepeats(hashes, md.group(1).charAt(0))) {
                found++;
                if (found >= 64) {
                    break;
                }
            }

            index++;
            hashes.add(hashFunction.apply(salt, index + hashes.size()));
        }

        return index;
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

    @FunctionalInterface
    interface HashFunction {
        String apply(String salt, int index);
    }
}