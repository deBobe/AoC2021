import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day_9 {

    public static void main(String[] args) throws IOException {
        List<List<Integer>> list = Files.readAllLines(Paths.get("src/entries_d9.txt")).stream().map(s -> Arrays.stream(s.split("")).map(Integer::parseInt).collect(Collectors.toList())).collect(Collectors.toList());

        //1. und 2.
        long sum = 0;

        List<Integer> basins = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                int x = list.get(i).get(j);
                if ((i>0 && list.get(i-1).get(j) <= x) || (i< list.size()-1 && list.get(i+1).get(j) <= x) || (j > 0 && list.get(i).get(j-1) <=x) || (j < list.get(i).size() -1 && list.get(i).get(j+1) <= x)) continue;
                sum += x +1;
                basins.add(findBasin(i,j,list));
            }
        }
        Collections.sort(basins);
        System.out.println(sum);
        System.out.println(basins.get(basins.size() -1)*basins.get(basins.size() -2)*basins.get(basins.size() -3));
    }

    //für 2.
    static int findBasin(int x, int y, List<List<Integer>> list) {
        if (x < 0 || x > list.size() -1 || y < 0 || y > list.get(x).size() -1) return 0;
        if (list.get(x).get(y) == 9) return 0;
        list.get(x).set(y,9);
        return findBasin(x-1,y,list) + findBasin(x+1,y,list) + findBasin(x,y-1,list) + findBasin(x,y+1,list) + 1;
    }
}
