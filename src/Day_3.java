import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day_3 {

    public static void main(String[] args) throws IOException {

        //first ugly try
/*
        List<String> l = new ArrayList<>(Files.readAllLines(java.nio.file.Paths.get("src/entries_d3.txt")));
        ArrayList<ArrayList<Integer>> d = new ArrayList<>();
        for (int i = 0; i < l.get(0).length(); i++) {
            d.add(i,new ArrayList<>());
            d.get(i).add(0,0);
            d.get(i).add(1,0);
            for (int j = 0; j < l.size(); j++) {
               // System.out.println(l.get(j).charAt(i) =);
                d.get(i).set(l.get(j).charAt(i) == '0' ? 0 : 1, d.get(i).get(l.get(j).charAt(i) == '0' ? 0 : 1) + 1);
            }
        }
        String n = "";
        String x = "";
        for (int i = 0; i < d.size(); i++) {
            n += d.get(i).get(0) > d.get(i).get(1) ? "0" : "1";
            x += d.get(i).get(0) < d.get(i).get(1) ? "0" : "1";
        }
        System.out.println(n);
        System.out.println(x);

        java.util.List<String> g = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("src/entries_d3.txt"));
        List<String> s = new ArrayList<>(g);
        List<String> v = new ArrayList<>(g);
        for (int i = 0; i < g.get(0).length(); i++) {
            int finalI = i;
            char c = count(i,s);
            s = s.stream().filter(e-> e.charAt(finalI) == c).collect(Collectors.toList());
        }
        for (int i = 0; i < g.get(0).length(); i++) {
            int finalI = i;
            char c = count2(i,v);
            v = v.stream().filter(e-> e.charAt(finalI) == c).collect(Collectors.toList());
        }
        System.out.println(s);
        System.out.println(v);
*/


        //shorter prettier second try
        //1.)
        StringBuilder str = new StringBuilder();
        List<String> list = new ArrayList<>(Files.readAllLines(Paths.get("src/entries_d3.txt")));
        for (int i = 0; i < 12; i++) {
            int finalI = i;
            str.append(list.stream().reduce(0, (o, p) -> o += p.charAt(finalI) == '1' ? 1 : 0, Integer::sum) >= list.stream().reduce(0, (o, p) -> o += p.charAt(finalI) == '0' ? 1 : 0, Integer::sum) ? '1' : '0');
        }
        System.out.println(str + " " + Long.toBinaryString(0B111111111111 - Long.parseLong(String.valueOf(str),2)));

        //2.)
        List<String> ss = new ArrayList<>(list);
        for (int i = 0; i <= 11 && ss.size() > 1; i++) {
            int finalI = i;
            List<String> finalSs = ss;
            ss = ss.stream().filter(e -> e.charAt(finalI) == (finalSs.stream().reduce(0, (o, p) -> o += p.charAt(finalI) == '1' ? 1 : 0, Integer::sum) >= finalSs.stream().reduce(0, (o, p) -> o += p.charAt(finalI) == '0' ? 1 : 0, Integer::sum) ? '1' : '0')).collect(Collectors.toList());
        }

        List<String> ss2 = new ArrayList<>(list);
        for (int i = 0; i <= 11 && ss2.size() > 1; i++) {
            int finalI = i;
            List<String> finalSs = ss2;
            int sum0 = finalSs.stream().reduce(0, (o, p) -> o += p.charAt(finalI) == '0' ? 1 : 0, Integer::sum);
            int sum1 = finalSs.stream().reduce(0, (o, p) -> o += p.charAt(finalI) == '1' ? 1 : 0, Integer::sum);
            ss2 = ss2.stream().filter(e -> e.charAt(finalI) == ((sum0 != 0 && sum0 <= sum1) ? '0' : '1')).collect(Collectors.toList());
        }

        System.out.println(ss);
        System.out.println(ss2);

    }
    static char count(int i, List<String> s) {
       int c0 = 0;
       int c1 = 0;
        for (String st : s
             ) {
            if (st.charAt(i) == '0') c0++;
            if (st.charAt(i) == '1') c1++;
        }
        return c1>=c0?'1':'0';
    }
    static char count2(int i, List<String> s) {
        int c0 = 0;
        int c1 = 0;
        for (String st : s
        ) {
            if (st.charAt(i) == '0') c0++;
            if (st.charAt(i) == '1') c1++;
        }
        if (c1 == 0) return  '0';
        if (c0 == 0) return  '1';
        return c1<c0?'1':'0';
    }

}
