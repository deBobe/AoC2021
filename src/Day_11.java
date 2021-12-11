import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day_11 {

    public static void main(String[] args) throws IOException {
        List<List<Integer>> matrix = Files.readAllLines(Paths.get("res/d11.txt")).stream().map(s -> Arrays.stream(s.split("")).map(Integer::parseInt).collect(Collectors.toList())).collect(Collectors.toList());

        long flashes = 0;
        boolean allFlashed = false;
        int i = 0;
        while (!allFlashed){
            if (i == 100) System.out.println(flashes);
            long tmpFlashes = 0;
            for (int x = 0; x < matrix.size(); x++) {
                for (int y = 0; y < matrix.size(); y++) {
                    tmpFlashes += flashRec(x,y,matrix);
                }
            }
            if (tmpFlashes == 100) {
                System.out.println("all flashed at i = " + (i + 1));
                allFlashed=true;
            }
            flashes+=tmpFlashes;
            for (int x = 0; x < matrix.size(); x++) {
                for (int y = 0; y < matrix.size(); y++) {
                    if (matrix.get(x).get(y) < 0) matrix.get(x).set(y,0);
                }
            }
            i++;
        }
    }
    static int flashRec(int x, int y, List<List<Integer>> list) {
        if(x <0||y<0||x>=list.size()||y>=list.size()) return 0;
        if (list.get(x).get(y) < 0) return 0;
        list.get(x).set(y,list.get(x).get(y) + 1);
        if (list.get(x).get(y) <= 9) return 0;
        list.get(x).set(y,-1);
        return 1 + flashRec(x+1,y,list)+flashRec(x+1,y+1,list)+flashRec(x+1,y -1,list)+flashRec(x,y+1,list)+flashRec(x,y-1,list)+flashRec(x-1,y,list)+flashRec(x-1,y+1,list)+flashRec(x-1,y-1,list);
    }

}
