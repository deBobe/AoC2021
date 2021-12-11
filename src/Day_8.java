import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day_8 {

    public static void main(String[] args) throws IOException {

        //1.
        int list = Files.readAllLines(Paths.get("res/d8.txt")).stream().map(s -> Arrays.stream((s.split(Pattern.quote(" | "))[1].split(" "))).reduce(0, (x,e) -> x+= e.length() == 2  || e.length() == 4 || e.length() == 3 || e.length() == 7 ? 1:0,Integer::sum)).reduce(0,Integer::sum);
        System.out.println(list);

        //2.
        List<String[]> right = Files.readAllLines(Paths.get("res/d8.txt")).stream().map(s -> s.split(Pattern.quote(" | "))[1].split(" ")).collect(Collectors.toList());
        List<String[]> left = Files.readAllLines(Paths.get("res/d8.txt")).stream().map(s -> s.split(Pattern.quote(" | "))[0].split(" ")).collect(Collectors.toList());

        long sum = 0;
        for (int i = 0; i < right.size(); i++) {
            String[] l = left.get(i);
            String[] r = right.get(i);
            String str1 = Arrays.stream(l).filter(s -> s.length() == 2).collect(Collectors.toList()).get(0);
            String str4 = Arrays.stream(l).filter(s -> s.length() == 4).collect(Collectors.toList()).get(0);
            String str7 = Arrays.stream(l).filter(s -> s.length() == 3).collect(Collectors.toList()).get(0);
            String str8 = Arrays.stream(l).filter(s -> s.length() == 7).collect(Collectors.toList()).get(0);
            String str9 = Arrays.stream(l).filter(s -> s.length() == 6 && contains(s,str4)==0 && contains(s,str7)==0).collect(Collectors.toList()).get(0);
            String str3 = Arrays.stream(l).filter(s -> s.length() == 5 && contains(s,str1)==0).collect(Collectors.toList()).get(0);
            String str0 = Arrays.stream(l).filter(s -> s.length() == 6 && contains(s,str4)<0 && contains(s,str7)==0).collect(Collectors.toList()).get(0);
            String str6 = Arrays.stream(l).filter(s -> s.length() == 6 && contains(s,str7)<0).collect(Collectors.toList()).get(0);
            String str5 = Arrays.stream(l).filter(s -> s.length() == 5 && contains(s,str6)==-1).collect(Collectors.toList()).get(0);
            String str2 = Arrays.stream(l).filter(s -> s.length() == 5 && contains(s,str9)==-2).collect(Collectors.toList()).get(0);

            String[] boring = {str0,str1,str2,str3,str4,str5,str6,str7,str8,str9};

            for (int j = 0; j < r.length; j++) {
                for (int k = 0; k < boring.length; k++) {
                    if (contains(boring[k], r[j]) == 0 && contains(r[j],boring[k]) == 0) {
                        sum += Math.pow(10,3-j) * k;
                        break;
                    }
                }
            }
        }
        System.out.println(sum);

    }

    static int contains(String s1, String s2) {
        int wrong = 0;
        for (char c : s2.toCharArray()) {
            if (s1.indexOf(c) == -1) wrong--;
        }
        return wrong;
    }

}
