import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day_25 {

    public static void main(String[] args) throws IOException {

        List<String[]> gridList = Files.readAllLines(Paths.get("res/d25.txt")).stream().map(s -> s.split("")).collect(Collectors.toList());
        String[][] grid = new String[gridList.size()][gridList.get(0).length];
        gridList.toArray(grid);

        int movements = 1;

        int i = 0;

        while (movements > 0) {
            movements = 0;
            String[][] newGrid = new String[grid.length][grid[0].length];
            for (String[] s : newGrid) {
                Arrays.fill(s,".");
            }

            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    if (grid[y][x].equals(">")) {
                        if (grid[y][(x + 1) % grid[0].length].equals(".")) {
                            newGrid[y][(x + 1) % grid[0].length] = ">";
                            movements++;
                        } else newGrid[y][x] = ">";
                    }
                }
            }
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    if (grid[y][x].equals("v")) {
                        if (!grid[(y + 1) % grid.length][(x)].equals("v") && !newGrid[(y + 1) % grid.length][(x)].equals(">")) {
                            newGrid[(y + 1) % grid.length][x] = "v";
                            movements++;
                        }
                        else newGrid[y][x] = "v";
                    }
                }
            }
            grid = newGrid;
            i++;
        }

        System.out.println(i);

    }

}
