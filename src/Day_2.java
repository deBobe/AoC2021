import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
public class Day_2 {
    public static void main(String[] args) throws IOException {

        //giga ugly but only two lines yeyyy
        System.out.println((Files.readAllLines(Paths.get("res/d2.txt")).stream().filter(s->s.split(" ")[0].equals("down")).map(s->Integer.parseInt(s.split(" ")[1])).reduce(0,Integer::sum)-Files.readAllLines(Paths.get("res/d2.txt")).stream().filter(s->s.split(" ")[0].equals("up")).map(s->Integer.parseInt(s.split(" ")[1])).reduce(0,Integer::sum)) * Files.readAllLines(Paths.get("res/d2.txt")).stream().filter(s->s.split(" ")[0].equals("forward")).map(s->Integer.parseInt(s.split(" ")[1])).reduce(0, Integer::sum));
        for (int i = 0,a=0,d=0; i < (new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).size();a += Integer.parseInt((new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).get(i).split(" ")[1]) * (((new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).get(i).charAt(0) == 'd') ? 1 : (new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).get(i).charAt(0) == 'u' ? -1 : 0),d += ((new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).get(i).charAt(0) == 'f') ? Integer.parseInt((new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).get(i).split(" ")[1]) * a : 0,i++) if (i == new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt"))).size() - 1) System.out.println((d + Integer.parseInt((new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).get(i).split(" ")[1]) * a) * Files.readAllLines(Paths.get("res/d2.txt")).stream().filter(s->s.charAt(0) == 'f').map(s->Integer.parseInt(s.split(" ")[1])).reduce(0, Integer::sum));

        //short but very ugly
        int d = 0;
        for (int i = 0,a=0; i < (new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).size();a += Integer.parseInt((new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).get(i).split(" ")[1]) * (((new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).get(i).split(" ")[0].equals("down")) ? 1 : (new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).get(i).split(" ")[0].equals("up") ? -1 : 0),i++) if ((new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).get(i).split(" ")[0].equals("forward")) d += Integer.parseInt((new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")))).get(i).split(" ")[1]) * a;
        System.out.println((Files.readAllLines(Paths.get("res/d2.txt")).stream().filter(s->s.split(" ")[0].equals("down")).map(s->Integer.parseInt(s.split(" ")[1])).reduce(0,Integer::sum)-Files.readAllLines(Paths.get("res/d2.txt")).stream().filter(s->s.split(" ")[0].equals("up")).map(s->Integer.parseInt(s.split(" ")[1])).reduce(0,Integer::sum)) * Files.readAllLines(Paths.get("res/d2.txt")).stream().filter(s->s.split(" ")[0].equals("forward")).map(s->Integer.parseInt(s.split(" ")[1])).reduce(0, Integer::sum));
        System.out.println(Files.readAllLines(Paths.get("res/d2.txt")).stream().filter(s->s.split(" ")[0].equals("forward")).map(s->Integer.parseInt(s.split(" ")[1])).reduce(0, Integer::sum)*d);

        //not as ugly but not as short

        int m = Files.readAllLines(Paths.get("res/d2.txt")).stream().filter(s->s.split(" ")[0].equals("forward")).map(s->Integer.parseInt(s.split(" ")[1])).reduce(0, Integer::sum);
        int x = Files.readAllLines(Paths.get("res/d2.txt")).stream().filter(s->s.split(" ")[0].equals("down")).map(s->Integer.parseInt(s.split(" ")[1])).reduce(0,Integer::sum);
        int y = Files.readAllLines(Paths.get("res/d2.txt")).stream().filter(s->s.split(" ")[0].equals("up")).map(s->Integer.parseInt(s.split(" ")[1])).reduce(0,Integer::sum);
        ArrayList<String> z = new ArrayList<>(Files.readAllLines(Paths.get("res/d2.txt")));

        int e = 0,a = 0;
        for (String s : z) {
            if (s.split(" ")[0].equals("forward")) e += Integer.parseInt(s.split(" ")[1]) * a;
            a += Integer.parseInt(s.split(" ")[1]) * ((s.split(" ")[0].equals("down")) ? 1 : s.split(" ")[0].equals("up") ? -1 : 0);
        }
        System.out.println((x-y) * m);
        System.out.println(m*e);

    }
}
