public class Day_16 {
    public static String start = "00111101111101000";
    public static int size;

    public static void main(String[] args) {
        System.out.println(Part1());
        System.out.println(Part2());
    }

    public static String Part1(){
        size = 272;
        return Solve();
    }

    public static String Part2(){
        size = 35651584;
        return Solve();
    }

    public static String Solve(){
        String line = start;
        while (line.length() < size){
            String end = "";
            StringBuilder endBuilder = new StringBuilder(line.length());
            for(int i = line.length() - 1; i >= 0; i--){
                endBuilder.append(line.charAt(i) == '0' ? '1' : '0');
            }
            end = endBuilder.toString();
            line = line + "0" + end;
        }
        line = line.substring(0, size);
        while(line.length() % 2 == 0) {
            StringBuilder endBuilder = new StringBuilder(line.length() / 2);
            for(int i = 0; i < line.length(); i+=2){
                endBuilder.append(line.charAt(i) == line.charAt(i+1) ? '1' : '0');
            }
            String end = endBuilder.toString();
            line = "" + end;
        }
        return line;
    }
}
