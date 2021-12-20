import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day_19 {

    public static void main(String[] args) throws IOException {
        List<List<List<Integer>>> scanner = Arrays.stream(Files.readString(Paths.get("res/d19.txt")).split("(--- scanner [0-9]+ ---)")).map(s-> Arrays.stream(s.split("\n")).map(e-> Arrays.stream(e.split(",")).map(i -> !i.equals("") ? Integer.parseInt(i) : null).collect(Collectors.toList())).collect(Collectors.toList())).collect(Collectors.toList());
        scanner.remove(0);
        for (List<List<Integer>> l : scanner) l.remove(0);

        int[][][] rotationMatrix = new int[][][]
                {{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}},
                {{0, -1, 0}, {1, 0, 0}, {0, 0, 1}},
                {{-1, 0, 0}, {0, -1, 0}, {0, 0, 1}},
                {{0, 1, 0}, {-1, 0, 0}, {0, 0, 1}},

                {{0, 1, 0}, {0, 0, -1}, {-1, 0, 0}},
                {{0, 0, 1}, {0, 1, 0}, {-1, 0, 0}},
                {{0, -1, 0}, {0, 0, 1}, {-1, 0, 0}},
                {{0, 0, -1}, {0, -1, 0}, {-1, 0, 0}},

                {{0, 0, 1}, {-1, 0, 0}, {0, -1, 0}},
                {{1, 0, 0}, {0, 0, 1}, {0, -1, 0}},
                {{0, 0, -1}, {1, 0, 0}, {0, -1, 0}},
                {{-1, 0, 0}, {0, 0, -1}, {0, -1, 0}},

                {{1, 0, 0}, {0, -1, 0}, {0, 0, -1}},
                {{0, 1, 0}, {1, 0, 0}, {0, 0, -1}},
                {{-1, 0, 0}, {0, 1, 0}, {0, 0, -1}},
                {{0, -1, 0}, {-1, 0, 0}, {0, 0, -1}},

                {{0, 1, 0}, {0, 0, 1}, {1, 0, 0}},
                {{0, 0, -1}, {0, 1, 0}, {1, 0, 0}},
                {{0, -1, 0}, {0, 0, -1}, {1, 0, 0}},
                {{0, 0, 1}, {0, -1, 0}, {1, 0, 0}},

                {{0, 0, 1}, {1, 0, 0}, {0, 1, 0}},
                {{-1, 0, 0}, {0, 0, 1}, {0, 1, 0}},
                {{0, 0, -1}, {-1, 0, 0}, {0, 1, 0}},
                {{1, 0, 0}, {0, 0, -1}, {0, 1, 0}}};


        List<Integer> knownScanners = new ArrayList<>();
        Map<Integer,int[][]> knownRotations = new HashMap<>();
        Map<Integer,int[]> knownDifferences = new HashMap<>();

        knownScanners.add(0);
        knownDifferences.put(0,new int[]{0,0,0});
        knownRotations.put(0,rotationMatrix[0]);

        List<List<Integer>> beamerPoints = new ArrayList<>(scanner.get(0));

        while (knownScanners.size() < scanner.size()) {

            for (int i = 0; i < scanner.size(); i++) {
                if (knownScanners.contains(i)) continue;

                List<List<Integer>> list = scanner.get(i);

                for (List<Integer> point: list) {
                    for (int[][] rotation : rotationMatrix) {
                        for (List<Integer> controlPoint : beamerPoints) {
                            int diffX = controlPoint.get(0) - (rotation[0][0] * point.get(0) + rotation[0][1] * point.get(1) + rotation[0][2] * point.get(2));
                            int diffY = controlPoint.get(1) - (rotation[1][0] * point.get(0) + rotation[1][1] * point.get(1) + rotation[1][2] * point.get(2));
                            int diffZ = controlPoint.get(2) - (rotation[2][0] * point.get(0) + rotation[2][1] * point.get(1) + rotation[2][2] * point.get(2));

                            int[] difference = new int[]{diffX,diffY,diffZ};

                            int overlapping = 0;

                            List<List<Integer>> tempBeamerPoints = new ArrayList<>();

                            for (List<Integer> p : list) {
                                int xVal = (rotation[0][0] * p.get(0) + rotation[0][1] * p.get(1) + rotation[0][2] * p.get(2)) + difference[0];
                                int yVal = (rotation[1][0] * p.get(0) + rotation[1][1] * p.get(1) + rotation[1][2] * p.get(2)) + difference[1];
                                int zVal = (rotation[2][0] * p.get(0) + rotation[2][1] * p.get(1) + rotation[2][2] * p.get(2)) + difference[2];
                                if(beamerPoints.stream().anyMatch(s -> s.get(0).equals(xVal) && s.get(1).equals(yVal) && s.get(2).equals(zVal))) {
                                    overlapping++;
                                } else {
                                    ArrayList<Integer> n = new ArrayList<>(p);
                                    n.set(0,xVal);
                                    n.set(1,yVal);
                                    n.set(2,zVal);
                                    tempBeamerPoints.add(n);
                                }
                            }
                            if (overlapping >= 12) {
                                knownDifferences.put(i,difference);
                                knownScanners.add(i);
                                beamerPoints.addAll(tempBeamerPoints);
                                break;
                            }
                        }
                        if (knownScanners.contains(i)) break;
                    }
                    if (knownScanners.contains(i)) break;
                }
            }
        }

        System.out.println(knownScanners);
        System.out.println(beamerPoints.size());

        Object[] x = knownDifferences.values().toArray();
        int max = 0;
        for (int i = 0; i < knownDifferences.values().size(); i++) {
            for (int j = 0; j < knownDifferences.values().size(); j++) {
                int tmp = Math.abs(((int[]) x[i])[0] - ((int[]) x[j])[0]) + Math.abs(((int[]) x[i])[1] - ((int[]) x[j])[1]) + Math.abs(((int[]) x[i])[2] - ((int[]) x[j])[2]);
                if (tmp > max) max = tmp;
            }
        }
        System.out.println(max);
    }
}
