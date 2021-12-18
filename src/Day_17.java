import java.sql.Array;
import java.util.*;

public class Day_17 {

    public static void main(String[] args) {
        int[][] target = new int[][]{{288, 330}, {-96, -50}};
        //x=20..30, y=-10..-5
        int sum = 0;
        for (int i = 95; i > 0; i--) {
            sum+=i;
        }
        System.out.println(sum);


        //very disgusting shit

        HashMap<Integer, ArrayList<Integer>> mapY = new HashMap<>();
        HashMap<Integer,ArrayList<Integer>> mapX = new HashMap<>();

        for (int i = 1; i < 96; i++) {
            int pos = 0;
            int steps = i*2 + 2;
            for (int j = i + 1; j <= 96; j++) {
                pos+=j;
                if (pos <= 96 && pos >=50) {
                    ArrayList<Integer> n = new ArrayList<>();
                    n.add(steps);
                    if (mapY.putIfAbsent(i, n) != null) mapY.get(i).add(steps);
                }
                if (pos>96) break;
                steps++;
            }
        }

        for (int i = 0; i <= 96; i++) {
            int pos = 0;
            int steps = 1;
            for (int j = i; j <=96 ; j++) {
                pos += j;
                if (pos <= 96 && pos >=50) {
                    ArrayList<Integer> n = new ArrayList<>();
                    n.add(steps);
                    if (mapY.putIfAbsent(-1 * i, n) != null) mapY.get(-1*i).add(steps);
                }
                if (pos>96) break;
                steps++;
            }
        }

        for (int i = 0; i <= 330; i++) {
            int pos = 0;
            int steps = 1;
            for (int j = i; j > 0; j--) {
                pos += j;
                if(pos<=330 && pos >= 288) {
                    ArrayList<Integer> n = new ArrayList<>();
                    n.add(steps);
                    if (mapX.putIfAbsent(i, n) != null) mapX.get(i).add(steps);
                }
                if (pos>330) break;
                steps ++;
            }
        }

        int total = 0;

        for (int i = 0; i < mapX.size(); i++) {
            for (int j = 0; j < mapY.size(); j++) {
                if ((containsAtLeastOne((ArrayList<Integer>)mapX.values().toArray()[i],(ArrayList<Integer>)mapY.values().toArray()[j])) || (((ArrayList<Integer>)mapX.values().toArray()[i]).contains((int)mapX.keySet().toArray()[i]) && ((ArrayList<Integer>)mapY.values().toArray()[j]).stream().max(Integer::compareTo).orElse(0) >= (int)mapX.keySet().toArray()[i])) {
                    System.out.println((int)mapX.keySet().toArray()[i] + "," + (int)mapY.keySet().toArray()[j]);
                    total++;
                }
            }
        }

        System.out.println(total);
    }

    static boolean containsAtLeastOne(ArrayList<Integer> l1, ArrayList<Integer> l2) {
        for (int i : l1) {
            if (l2.contains(i)) return true;
        }
        return false;
    }

}
