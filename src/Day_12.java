import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day_12 {

    static class Node implements Comparable<Node> {

        ArrayList<Node> connected;
        String name;
        boolean isSmall;

        public Node(String n) {
            name = n;
            isSmall = n.charAt(0) > 90;
            connected = new ArrayList<>();
        }

        @Override
        public int compareTo(Node o) {
            if (name.equals(o.name)) return 0;
            else return -1;
        }
        public void addNode(Node n) {
            if (!connected.contains(n)) connected.add(n);
        }
    }

    public static void main(String[] args) throws IOException {

        List<String[]> list = Files.readAllLines(Paths.get("res/d12.txt")).stream().map(s->s.split("-")).collect(Collectors.toList());

        ArrayList<Node> nodeList = new ArrayList<>();

        for (String[] l : list) {
            Node n1 = nodeList.stream().filter(s -> s.name.equals(l[0])).findFirst().orElse(new Node(l[0]));
            Node n2 = nodeList.stream().filter(s -> s.name.equals(l[1])).findFirst().orElse(new Node(l[1]));
            n1.addNode(n2);
            n2.addNode(n1);
            if (!nodeList.contains(n1)) nodeList.add(n1);
            if (!nodeList.contains(n2)) nodeList.add(n2);
        }

        Node start = nodeList.stream().filter(s -> s.name.equals("start")).findFirst().orElse(null);

        ArrayList<Node> ll = new ArrayList<>();
        ll.add(start);

        List<List<Node>> paths = findPaths(ll);
        System.out.println(paths.size());

    }

    static List<List<Node>> findPaths(List<Node> n) {
        List<List<Node>> l = new ArrayList<>();
        if (n.get(n.size() - 1).name.equals("end")) {
            l.add(n);
            return l;
        }
        for (Node node : n.get(n.size() - 1).connected) {
            if (!node.isSmall || (!n.contains(node)) || (!node.name.equals("start") && !node.name.equals("end") && smallCavesOnlyOnce(n,node))) {
                List<Node> x = new ArrayList<>(n);
                x.add(node);
                l.addAll(findPaths(x));
            }
        }
        return l;
    }

    static boolean smallCavesOnlyOnce(List<Node> list, Node node) {
        for (Node n : list) {
            if (n.isSmall && list.stream().reduce(0,(s,e)-> s += e.name.equals(n.name) ? 1: 0, Integer::sum) >= 2) return false;
        }
        return !node.isSmall || list.stream().reduce(0, (s, e) -> s += e.name.equals(node.name) ? 1 : 0, Integer::sum) <= 1;
    }
}
