import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day_5 {

    public static void main(String[] args) throws IOException {
        List<List<Integer>> points = Files.readAllLines(Paths.get("res/d5.txt")).stream().map(s-> Arrays.stream(s.split(",| -> ")).map(Integer::parseInt).collect(Collectors.toList())).collect(Collectors.toList());

        int[][] grid = new int[1000][1000];

        //Aufgabe 1
        for (List<Integer> l: points){
            if (l.get(0).equals(l.get(2))) {
                for (int i = Math.min(l.get(1),l.get(3)); i <= Math.max(l.get(1),l.get(3)); i++) {
                    grid[l.get(0)][i]++;
                }
            }else if (l.get(1).equals(l.get(3))) {
                for (int i = Math.min(l.get(0),l.get(2)); i <= Math.max(l.get(0),l.get(2)); i++) {
                    grid[i][l.get(1)]++;
                }
            }/* else {
                for (int i = 0,j = 0; i <= Math.abs(l.get(0)- l.get(2)) && j <= Math.abs(l.get(1)- l.get(3)); i += i <= Math.abs(l.get(0)- l.get(2)) ? 1 : 0,j += j <= Math.abs(l.get(1)- l.get(3))? 1 : 0){
                    grid[l.get(0)+(i * (int)Math.signum(l.get(2)- l.get(0)))][l.get(1)+(j * (int)Math.signum(l.get(3)- l.get(1)))]++;
                }
            }*/
        }
        System.out.println(Arrays.stream(grid).reduce(0,(e,s)-> e += Arrays.stream(s).reduce(0,(d,t)->d += t>1?1:0),Integer::sum));

        //Aufgabe 2
        grid = new int[1000][1000];
        for (List<Integer> l : points) {
            for (int i = 0,j = 0; i <= Math.abs(l.get(0)- l.get(2)) || j <= Math.abs(l.get(1)- l.get(3)); i += i <= Math.abs(l.get(0)- l.get(2)) ? 1 : 0,j += j <= Math.abs(l.get(1)- l.get(3))? 1 : 0){
                grid[l.get(0)+(i * (int)Math.signum(l.get(2)- l.get(0)))][l.get(1)+(j * (int)Math.signum(l.get(3)- l.get(1)))]++;
            }
        }
        System.out.println(Arrays.stream(grid).reduce(0,(e,s)-> e += Arrays.stream(s).reduce(0,(d,t)->d += t>1?1:0),Integer::sum));
    }
}
