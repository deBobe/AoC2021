public class Day_1 {
    public static void main(String[] args) throws java.io.IOException {
        java.util.List<Integer> l = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("src/entrys_d1.txt")).stream().map(Integer::parseInt).collect(java.util.stream.Collectors.toList());
        System.out.println(java.util.stream.IntStream.range(0,l.size()-1).map(i -> l.get(i+1) < l.get(i) ? 1 : 0).filter(s->s==0).count()); //Teil 1
        System.out.println(java.util.stream.IntStream.range(0,l.size()-3).map(i -> l.get(i) + l.get(i+1) + l.get(i+2) < l.get(i+1) + l.get(i+2) + l.get(i+3) ? 0 : 1).filter(s->s==0).count()); //Teil 2
    }
}