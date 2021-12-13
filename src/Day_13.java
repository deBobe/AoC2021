import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day_13 {

    public static void main(String[] args) throws IOException {

        List<List<Integer>> pointList = Arrays.stream(Files.readString(Paths.get("res/d13.txt")).split("\n\n")[0].split("\n")).map(s-> Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList())).collect(Collectors.toList());
        String[] foldList = (Files.readString(Paths.get("res/d13.txt")).split("\n\n")[1].split("\n"));

        for (String s : foldList) {
            int axis = s.split(" ")[2].split("=")[0].equals("x") ? 0 : 1;
            int value = Integer.parseInt(s.split(" ")[2].split("=")[1]);

            for (List<Integer> point : pointList) {
                if (point.get(axis) < value) point.set(axis,Math.abs(point.get(axis) - (value - 1)));
                else if (point.get(axis) > value) point.set(axis,point.get(axis) - (value + 1));
            }
            List<List<Integer>> newPointList = new ArrayList<>();
            for (int i = 0; i < pointList.size(); i++) {
                int finalI = i;
                List<List<Integer>> finalPointList = pointList;
                if (newPointList.stream().noneMatch(e -> e.get(0).equals(finalPointList.get(finalI).get(0)) && e.get(1).equals(finalPointList.get(finalI).get(1)))) newPointList.add(pointList.get(i));
            }
            System.out.println(newPointList.size());
            pointList = newPointList;
        }
        int maxX = pointList.stream().max(Comparator.comparing(c -> c.get(0))).orElseThrow().get(0);
        int maxY = pointList.stream().max(Comparator.comparing(c -> c.get(1))).orElseThrow().get(1);

        int[][] display = new int[maxY+1][maxX+1];

        for (List<Integer> p : pointList) {
            display[p.get(1)][p.get(0)] = 1;
        }
        for (int[] i : display) {
            for (int j : i) {
                if (j == 0) System.out.print(".");
                else System.out.print("#");
            }
            System.out.println();
        }
    }

}
