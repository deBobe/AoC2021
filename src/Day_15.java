import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class Day_15 {

    static class Node {
        int value;
        int pathValue;
        int[] direction;

        public Node(int v) {
            value = v;
            pathValue = Integer.MAX_VALUE;
            direction = new int[]{-1,-1};
        }
        public Node add() {
            int v = value + 1;
            if (v > 9) v = 1;
            return new Node(v);
        }
    }


    public static void main(String[] args) throws IOException {

        List<List<Node>> m = Files.readAllLines(Paths.get("res/d15.txt")).stream().map(s -> Arrays.stream(s.split("")).map(e -> new Node(Integer.parseInt(e))).collect(Collectors.toList())).collect(Collectors.toList());

        int len = m.size();
        for (int i = 0; i < 4; i++) {
            for (List<Node> l : m) {
                for (int j = i * len; j < (i+1) * len; j++) {
                    l.add(l.get(j).add());
                }
            }
        }
        
        int length = m.size();
        for (int i = 0; i < 4; i++) {
            for (int j = i * length; j < (i+1) * length; j++) {
                m.add(m.get(j).stream().map(Node::add).collect(Collectors.toList()));
            }
        }
        
        int[][] s = new int[][]{{0,1},{-1,0},{1,0},{0,-1}};

        m.get(0).get(0).pathValue = 0;

        Queue<int[]> nextPositions = new ConcurrentLinkedQueue<>();

        nextPositions.add(new int[]{0,0});
        nextPositions.add(new int[]{1,0});
        nextPositions.add(new int[]{0,1});

        while (!nextPositions.isEmpty()) {

            int[] current = nextPositions.poll();
            int x = current[0];
            int y = current[1];

            int smallest = Integer.MAX_VALUE;
            int[] smallestPos = new int[]{-1,-1};
            for (int[] ints : s) {
                int[] newPos = new int[]{x + ints[0], y + ints[1]};
                if (newPos[0] < 0 || newPos[1] < 0 || newPos[0] >= m.size() || newPos[1] >= m.size()) continue;
                long t = (long)(m.get(newPos[0]).get(newPos[1]).pathValue) + m.get(newPos[0]).get(newPos[1]).value;
                if (t < smallest) {
                    smallest = (int) t;
                    smallestPos = newPos;
                }
            }

            if (smallest < m.get(x).get(y).pathValue) {
                m.get(x).get(y).pathValue = smallest;
                m.get(x).get(y).direction = smallestPos;
                for (int[] i : s) {
                    int[] newPos = new int[]{x + i[0], y + i[1]};
                    if (newPos[0] < 0 || newPos[1] < 0 || newPos[0] >= m.size() || newPos[1] >= m.size()) continue;
                    nextPositions.add(new int[]{newPos[0],newPos[1]});
                }
            }
        }

        System.out.println(m.get(m.size() - 1).get(m.size() - 1).pathValue + m.get(m.size() - 1).get(m.size() - 1).value - m.get(0).get(0).value);
    }
}
