import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day_4 {

    static class Board {
        ArrayList<List<Integer>> rows = new ArrayList<>();
        ArrayList<List<Integer>> cols = new ArrayList<>();

        Board() {
            for (int i = 0; i < 5; i++) {
                cols.add(new ArrayList<>());
            }
        }

        public void addRow(List<Integer> m) {
            rows.add(m);
            for (int i = 0; i < 5; i++) {
                cols.get(i).add(m.get(i));
            }
        }

        public void addValue(int n) {
            for (List<Integer> m: rows){
                if (m.contains(n)) {
                    m.set(m.indexOf(n),-1);
                }
            }
            for (List<Integer> m: cols){
                if (m.contains(n)) {
                    m.set(m.indexOf(n),-1);
                }
            }
        }

        public boolean hasWon() {
            for (List<Integer> m: rows){
                if (m.stream().reduce(0,(e,s) -> e+=s) == -5)
                    return true;
            }
            for (List<Integer> m: cols){
                if (m.stream().reduce(0,(e,s) -> e+=s) == -5)
                    return true;
            }
            return false;
        }

        public int getValue() {
            return rows.stream().reduce(0,(g,l) -> g += l.stream().reduce(0,(s,e) ->s+= e >= 0 ? e : 0),Integer::sum);
        }
    }


    public static void main(String[] args) throws IOException {
        List<Integer> draws = Arrays.stream(Files.readAllLines(Paths.get("src/entries_d4.txt")).get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        List<List<Integer>> lines = Files.readAllLines(Paths.get("src/entries_d4.txt")).subList(1, Files.readAllLines(Paths.get("src/entries_d4.txt")).size()).stream().map(s -> s.equals("\n") ? new ArrayList<Integer>() : Arrays.stream(s.split("\s")).filter(f -> !f.equals("")).map(Integer::parseInt).collect(Collectors.toList())).filter(s -> !s.isEmpty()).collect(Collectors.toList());

        ArrayList<Board> boards = new ArrayList<>();
        for (int i = 0; i < lines.size(); i+=5) {
            Board b = new Board();
            for (int j = 0; j < 5; j++) {
                b.addRow(lines.get(i+j));
            }
            boards.add(b);
        }

        for (int d : draws) {
            ArrayList<Board> newBoards = new ArrayList<>();
            for (Board b : boards) {
                b.addValue(d);

                if (b.hasWon()) {
                    if (boards.size() == 1 ) {
                        System.out.println(b.getValue() * d);
                        return;
                    }
                }else {
                    newBoards.add(b);
                }
            }
            boards = newBoards;

        }
    }
}