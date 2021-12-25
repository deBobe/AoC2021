import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day_24 {

    static class Entry {

        long[] perm;
        long value;

        public Entry(long[] perm, long value) {
            this.perm = perm;
            this.value = value;

        }

        public boolean equals(Entry e) {
            if (Math.abs(value) == Math.abs(e.value)) {
                for (int i = 0; i < perm.length; i++) {
                    if (perm[i] < e.perm[i]) return false;
                    else if (perm[i] > e.perm[i]) return true;
                }
            } else {
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {

        List<String> instructions = Files.readAllLines(Paths.get("res/d24.txt"));
        int[] xConstants = new int[]{0,-9,-5,0,-3,15,-5,10,10,-7,11,15,15,13};
        int[] yConstants = new int[]{10,12,11,5,3,5,10,1,8,15,2,10,7,6};
        int[] zConstants = new int[]{26,26,26,26,26,1,26,1,1,26,1,1,1,1};

        ArrayList<Entry> permutations = new ArrayList<>();
        permutations.add(new Entry(new long[0],0));

        for (int i = 0; i < 14; i++) {
            ArrayList<Entry> newPermutations = new ArrayList<>();

            for (Entry p : permutations) {
                for (int w = 1; w <= 9; w++) {
                    if ((p.value - (w + yConstants[i])) % 26 == 0) {
                        long z = ((p.value - (w + yConstants[i])) / 26) * zConstants[i];
                        for (long j = z; j < z + zConstants[i]; j++) {
                            if ((j % 26) + xConstants[i] != w) {
                                Entry e = new Entry(new long[p.perm.length + 1],j);
                                e.perm[0] = w;
                                System.arraycopy(p.perm, 0, e.perm, 1, p.perm.length);
                                newPermutations.add(e);
                            }
                        }
                    }
                }
            }

            for (Entry p : permutations) {
                long z = p.value * zConstants[i];
                for (long j = z; j < z + zConstants[i]; j++) {
                    for (int w = 1; w <= 9; w++) {
                        if (j % 26 + xConstants[i] == w) {
                            Entry e = new Entry(new long[p.perm.length + 1],j);
                            e.perm[0] = w;
                            System.arraycopy(p.perm, 0, e.perm, 1, p.perm.length);
                            newPermutations.add(e);
                        }
                    }
                }
            }
            HashMap<Long,Entry> valMap = new HashMap<>();
            for (Entry e : newPermutations) {
                if (valMap.containsKey(e.value)) {
                    if (valMap.get(e.value).equals(e)) {
                        valMap.put(e.value,e);
                    }
                } else valMap.put(e.value,e);
            }

            permutations = new ArrayList<>(valMap.values());
        }
        ArrayList<Long> perms = new ArrayList<>();

        for (Entry e : permutations) {
            System.out.println(Arrays.toString(evaluate(instructions, e.perm)));
            long val = 0;
            for (int i = 0; i < 14; i++) {
                val += e.perm[i] * Math.pow(10,13-i);
            }
            perms.add(val);
        }
        System.out.println(perms.stream().min(Comparator.comparingLong(Long::longValue)).orElse(0L));
    }

    static long[] evaluate(List<String> instructions, long[] input) {
        long[] register = new long[4];
        int w = -1;
        for (String s : instructions) {
            switch (s.split(" ")[0]) {
                case "inp" -> {
                    w++;
                    if (w >= 14) break;
                    register[0] = input[w];
                }
                case "add" -> {
                    String a1 = s.split(" ")[1];
                    String a2 = s.split(" ")[2];
                    try {
                        register[a1.charAt(0) - 119] += Integer.parseInt(a2);
                    } catch (Exception e) {
                        register[a1.charAt(0) - 119] += register[a2.charAt(0) - 119];
                    }
                }
                case "mul" -> {
                    String a1 = s.split(" ")[1];
                    String a2 = s.split(" ")[2];
                    try {
                        register[a1.charAt(0) - 119] *= Integer.parseInt(a2);
                    } catch (Exception e) {
                        register[a1.charAt(0) - 119] *= register[a2.charAt(0) - 119];
                    }
                }
                case "div" -> {
                    String a1 = s.split(" ")[1];
                    String a2 = s.split(" ")[2];
                    try {
                        register[a1.charAt(0) - 119] /= Integer.parseInt(a2);
                    } catch (Exception e) {
                        register[a1.charAt(0) - 119] /= register[a2.charAt(0) - 119];
                    }
                }
                case "mod" -> {
                    String a1 = s.split(" ")[1];
                    String a2 = s.split(" ")[2];
                    try {
                        register[a1.charAt(0) - 119] %= Integer.parseInt(a2);
                    } catch (Exception e) {
                        register[a1.charAt(0) - 119] %= register[a2.charAt(0) - 119];
                    }
                }
                case "eql" -> {
                    String a1 = s.split(" ")[1];
                    String a2 = s.split(" ")[2];
                    try {
                        register[a1.charAt(0) - 119] = register[a1.charAt(0) - 119] == Integer.parseInt(a2) ? 1 : 0;
                    } catch (Exception e) {
                        register[a1.charAt(0) - 119] = register[a1.charAt(0) - 119] == register[a2.charAt(0) - 119] ? 1 : 0;
                    }
                }
            }
        }
        return register;
    }

}
