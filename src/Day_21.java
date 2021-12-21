import java.util.ArrayList;
import java.util.stream.Collectors;

public class Day_21 {

    static class Entry {
        int position;
        int value;
        String permutation;
        int count;

        public Entry(int p, int v, String perm, int c) {
            position = p;
            value = v;
            permutation = perm;
            count = c;

        }

        void addCount(int c) {
            count += c;
        }
    }

    public static void main(String[] args) {
        int scorePlayerOne = 0;
        int scorePlayerTwo = 0;

        int positionPlayerOne = 4;
        int positionPlayerTwo = 8;

        int diceValue = 1;
        int diceCount = 0;

        while (scorePlayerTwo < 1000) {
            positionPlayerOne += ((diceValue - 1) % 100 + 1) + ((diceValue) % 100 + 1) + (diceValue + 1) % 100 + 1;
            positionPlayerOne = (positionPlayerOne - 1) % 10 + 1;
            diceValue = (diceValue + 2) % 100 + 1;
            diceCount+=3;
            scorePlayerOne += positionPlayerOne;
            if (scorePlayerOne >= 1000) break;
            positionPlayerTwo += ((diceValue - 1) % 100 + 1) + ((diceValue) % 100 + 1) + (diceValue + 1) % 100 + 1;
            positionPlayerTwo = (positionPlayerTwo - 1) % 10 + 1;


            scorePlayerTwo += positionPlayerTwo;

            diceCount+=3;
            diceValue = (diceValue + 2) % 100 + 1;
        }

        System.out.println(Math.min(scorePlayerOne,scorePlayerTwo) * diceCount);

        System.out.println(Math.max(calculate(5,6,true),calculate(5,6,false)));
    }

    static long calculate(int p1,int p2, boolean which) { //which = true -> p1
        ArrayList<Entry> permutationsP1 = new ArrayList<>();
        permutationsP1.add(new Entry(which ? p1 : p2,0,"",1));

        int[] newGenerated = new int[]{1,3,6,7,6,3,1};
        int changes = 1;
        while (changes > 0) {
            changes = 0;
            ArrayList<Entry> newPermP1 = new ArrayList<>();

            for (Entry entry : permutationsP1) {
                if (entry.value>=21) {
                    newPermP1.add(entry);
                    continue;
                }
                for (int i = 3; i < 10; i++) {
                    changes++;
                    String tmp = entry.permutation + i;
                    int pos = ((entry.position + i) - 1) % 10 + 1;
                    int val = entry.value + pos;
                    int count = entry.count * newGenerated[i-3];
                    if (newPermP1.stream().anyMatch(s -> s.position == pos && s.value == val && s.permutation.length() == tmp.length())) {
                        newPermP1.stream().filter(s -> s.position == pos && s.value == val && s.permutation.length() == tmp.length()).collect(Collectors.toList()).get(0).addCount(count);
                    } else {
                        newPermP1.add(new Entry(pos,val,tmp,count));
                    }
                }
            }

            permutationsP1 = newPermP1;
        }

        long sumP1 = 0;

        for (Entry e1 : permutationsP1) {
            ArrayList<Entry> permutationsP2 = new ArrayList<>();
            permutationsP2.add(new Entry(which ? p2 : p1,0,"",1));

            for (int j = 0; j < e1.permutation.length() + (which ? 0 : 1); j++) {
                ArrayList<Entry> newPermP2 = new ArrayList<>();

                for (Entry entry : permutationsP2) {
                    if (j == e1.permutation.length() - (which ? 1 : 0))
                        sumP1 += (long) e1.count * entry.count;
                    for (int i = 3; i < 10; i++) {
                        String tmp = entry.permutation + i;
                        int pos = ((entry.position + i) - 1) % 10 + 1;
                        int val = entry.value + pos;
                        if (val >= 21) continue;
                        int count = entry.count * newGenerated[i-3];
                        if (newPermP2.stream().anyMatch(s -> s.position == pos && s.value == val && s.permutation.length() == tmp.length())) {
                            newPermP2.stream().filter(s -> s.position == pos && s.value == val && s.permutation.length() == tmp.length()).collect(Collectors.toList()).get(0).addCount(count);
                        } else {
                            newPermP2.add(new Entry(pos,val,tmp,count));
                        }
                    }
                }
                permutationsP2 = newPermP2;
            }
        }
        return sumP1;
    }
}
