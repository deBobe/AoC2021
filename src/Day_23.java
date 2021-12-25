import java.util.*;
import java.util.stream.Stream;

public class Day_23 {

    static class Configuration {
        char[] hallway;
        char[] room1;
        char[] room2;
        char[] room3;
        char[] room4;
        int energyConsumed = 0;

        public Configuration(char[] h,char[] r1,char[] r2,char[] r3,char[] r4){
            hallway = h;
            room1 = r1;
            room2 = r2;
            room3 = r3;
            room4 = r4;
        }

        public Configuration copy() {
            Configuration copy = new Configuration(hallway.clone(),room1.clone(),room2.clone(),room3.clone(),room4.clone());
            copy.energyConsumed = energyConsumed;
            return copy;
        }

        public boolean equal(Configuration o) {
            for (int i = 0; i < hallway.length; i++) {
                if (hallway[i] != o.hallway[i]) return false;
            }
            if (room1[0] != o.room1[0]) return false;
            if (room1[1] != o.room1[1]) return false;
            if (room2[0] != o.room2[0]) return false;
            if (room2[1] != o.room2[1]) return false;
            if (room3[0] != o.room3[0]) return false;
            if (room3[1] != o.room3[1]) return false;
            if (room4[0] != o.room4[0]) return false;
            if (room4[1] != o.room4[1]) return false;
            if (room1[2] != o.room1[2]) return false;
            if (room1[3] != o.room1[3]) return false;
            if (room2[2] != o.room2[2]) return false;
            if (room2[3] != o.room2[3]) return false;
            if (room3[2] != o.room3[2]) return false;
            if (room3[3] != o.room3[3]) return false;
            if (room4[2] != o.room4[2]) return false;
            if (room4[3] != o.room4[3]) return false;
            if (energyConsumed < o.energyConsumed) return false;
            return true;
        }

        public int alreadyCorrect(){
            int x = 0;
            if (room1[0] == 'A' && room1[1] == 'A' && room1[2] == 'A' && room1[3] == 'A') x -= 50000; else x +=100000;
            if (room1[1] == 'A' && room1[2] == 'A' && room1[3] == 'A') x -= 50000; else x +=100000;
            if (room2[0] == 'B' && room2[1] == 'B' && room2[2] == 'B' && room2[3] == 'B') x -= 50000; else x +=100000;
            if (room2[1] == 'B' && room2[2] == 'B' && room2[3] == 'B') x -= 50000; else x +=100000;
            if (room3[0] == 'C' && room3[1] == 'C' && room3[2] == 'C' && room3[3] == 'C') x -= 50000; else x +=100000;
            if (room3[1] == 'C' && room3[2] == 'C' && room3[3] == 'C') x -= 50000; else x +=100000;
            if (room4[0] == 'D' && room4[1] == 'D' && room4[2] == 'D' && room4[3] == 'D') x -= 50000; else x +=100000;
            if (room4[1] == 'D' && room4[2] == 'D' && room4[3] == 'D') x -= 50000; else x +=100000;
            if (room1[2] == 'A' && room1[3] == 'A') x -= 50000; else x +=100000;
            if (room1[3] == 'A') x -= 50000; else x +=20000;
            if (room2[2] == 'B' && room2[3] == 'B') x -= 50000; else x +=100000;
            if (room2[3] == 'B') x -= 50000; else x +=100000;
            if (room3[2] == 'C' && room3[3] == 'C') x -= 50000; else x +=100000;
            if (room3[3] == 'C') x -= 50000; else x +=100000;
            if (room4[2] == 'D' && room4[3] == 'D') x -= 50000; else x +=100000;
            if (room4[3] == 'D') x -= 50000; else x +=100000;
            return x;
        }

        public int predictedEnergyConsumption() {
            int x = energyConsumed;
            for (int i = 0; i < hallway.length; i++) {
                switch (hallway[i]) {
                    case 'A' -> x += Math.abs(2 - i) + 1;
                    case 'B' -> x += (Math.abs(4 - i) + 1) * 10;
                    case 'C' -> x += (Math.abs(6 - i) + 1) * 100;
                    case 'D' -> x += (Math.abs(8 - i) + 1) * 1000;
                }
            }
            if (room1[0] == 'B') x  += 4 * 10; else if (room1[0] == 'C') x += 6 * 100; else if (room1[0] == 'D') x += 8 * 1000;
            if (room1[1] == 'B') x  += 5 * 10; else if (room1[1] == 'C') x += 7 * 100; else if (room1[1] == 'D') x += 9 * 1000;
            if (room2[0] == 'A') x  += 4 ; else if (room2[0] == 'C') x += 4 * 100; else if (room2[0] == 'D') x += 6 * 1000;
            if (room2[1] == 'A') x  += 5 ; else if (room2[1] == 'C') x += 5 * 100; else if (room2[1] == 'D') x += 7 * 1000;
            if (room3[0] == 'B') x  += 4 * 10; else if (room3[0] == 'A') x += 6; else if (room3[0] == 'D') x += 4 * 1000;
            if (room3[1] == 'B') x  += 5 * 10; else if (room3[1] == 'A') x += 7; else if (room3[1] == 'D') x += 5 * 1000;
            if (room4[0] == 'C') x  += 4 * 100; else if (room4[0] == 'B') x += 6 * 10; else if (room4[0] == 'A') x += 8;
            if (room4[1] == 'C') x  += 5 * 100; else if (room4[1] == 'B') x += 7 * 10; else if (room4[1] == 'A') x += 9;

            if (room1[0] == 'A' && room1[1] != 'A') x += 4;
            if (room2[0] == 'B' && room2[1] != 'B') x += 4 * 10;
            if (room3[0] == 'C' && room3[1] != 'C') x += 4 * 100;
            if (room4[0] == 'D' && room4[1] != 'D') x += 4 * 1000;

            return x;
        }
    }

    public static void main(String[] args) {
        //A = 1; B = 10; C = 100; D = 1000
        char[] hallway = new char[11];
        Arrays.fill(hallway,'.');
        char[] _room1 = new char[]{'A','D','D','B'};
        char[] _room2 = new char[]{'C','C','B','D'};
        char[] _room3 = new char[]{'C','B','A','A'};
        char[] _room4 = new char[]{'D','A','C','B'};
        //char[] room1 = new char[]{'A','B'};
       // char[] room2 = new char[]{'C','D'};
       // char[] room3 = new char[]{'C','A'};
       // char[] room4 = new char[]{'D','B'};

        Queue<Configuration> configQueue = new PriorityQueue<>(100, new Comparator<Configuration>() {
            @Override
            public int compare(Configuration o1, Configuration o2) {
                //return (o1.energyConsumed) - (o2.energyConsumed);
                return (o1.energyConsumed + o1.alreadyCorrect()) - (o2.energyConsumed + o2.alreadyCorrect());
            }
        });
        configQueue.add(new Configuration(hallway,_room1,_room2,_room3,_room4));

        ArrayList<Configuration> alreadyChecked = new ArrayList<>();

        int minEnergy = Integer.MAX_VALUE;

        while (!configQueue.isEmpty()){
            Configuration tmp = configQueue.poll();
            int vv = tmp.predictedEnergyConsumption();
            if (vv > minEnergy) continue;
            //is finished?
            if (tmp.room2[0] == 'B' && tmp.room2[1] == 'B' && tmp.room2[2] == 'B' && tmp.room2[3] == 'B') {
//                System.out.println("");
            }
            if (tmp.room1[0] == 'A' && tmp.room1[1] == 'A' && tmp.room1[2] == 'A' && tmp.room1[3] == 'A' && tmp.room2[0] == 'B' && tmp.room2[1] == 'B' && tmp.room2[2] == 'B' && tmp.room2[3] == 'B' && tmp.room3[0] == 'C' && tmp.room3[1] == 'C' && tmp.room3[2] == 'C' && tmp.room3[3] == 'C' && tmp.room4[0] == 'D' && tmp.room4[1] == 'D' && tmp.room4[2] == 'D' && tmp.room4[3] == 'D') {
                if (tmp.energyConsumed < minEnergy) {
                    minEnergy = tmp.energyConsumed;
                }
            } else {
                //all valid steps
                for (int i = 0; i < tmp.hallway.length; i++) {
                    if (tmp.hallway[i] != '.') {
                        switch (tmp.hallway[i]) {
                            case 'A'-> {
                                if ((tmp.room1[3] == '.') || (tmp.room1[2] == '.' && tmp.room1[3] == 'A') || (tmp.room1[1] == '.' && tmp.room1[2] == 'A' && tmp.room1[3] == 'A') || (tmp.room1[0] == '.' && tmp.room1[1] == 'A' && tmp.room1[2] == 'A' && tmp.room1[3] == 'A')) {
                                    if (!checkWayFree(tmp.hallway,i,2,true)) continue;
                                    Configuration newConfig = tmp.copy();
                                    newConfig.energyConsumed += Math.abs(i - 2) + (newConfig.room1[3] == '.' ? 4 : newConfig.room1[2] == '.' ? 3 : newConfig.room1[1] == '.' ? 2 :1);
                                    newConfig.room1[(newConfig.room1[3] == '.' ? 3 : newConfig.room1[2] == '.' ? 2 : newConfig.room1[1] == '.' ? 1 :0)] = 'A';
                                    newConfig.hallway[i] = '.';
                                    if (alreadyChecked.stream().noneMatch(newConfig::equal)) {
                                        configQueue.add(newConfig);
                                        alreadyChecked.add(newConfig.copy());
                                    }
                                   // if (configQueue.stream().noneMatch(newConfig::equal)) ;
                                }
                            }
                            case 'B' -> {
                                if ((tmp.room2[3] == '.') || (tmp.room2[2] == '.' && tmp.room2[3] == 'B') || (tmp.room2[1] == '.' && tmp.room2[2] == 'B' && tmp.room2[3] == 'B') || (tmp.room2[0] == '.' && tmp.room2[1] == 'B' && tmp.room2[2] == 'B' && tmp.room2[3] == 'B')) {
                                    if (!checkWayFree(tmp.hallway,i,4,true)) continue;
                                    Configuration newConfig = tmp.copy();
                                    newConfig.energyConsumed += (Math.abs(i - 4) + (newConfig.room2[3] == '.' ? 4 : newConfig.room2[2] == '.' ? 3 : newConfig.room2[1] == '.' ? 2 :1)) * 10;
                                    newConfig.room2[(newConfig.room2[3] == '.' ? 3 : newConfig.room2[2] == '.' ? 2 : newConfig.room2[1] == '.' ? 1 :0)] = 'B';
                                    newConfig.hallway[i] = '.';
                                    if (alreadyChecked.stream().noneMatch(newConfig::equal)) {
                                        configQueue.add(newConfig);
                                        alreadyChecked.add(newConfig.copy());
                                    }
                                }
                            }
                            case 'C' -> {
                                if ((tmp.room3[3] == '.') || (tmp.room3[2] == '.' && tmp.room3[3] == 'C') || (tmp.room3[1] == '.' && tmp.room3[2] == 'C' && tmp.room3[3] == 'C') || (tmp.room3[0] == '.' && tmp.room3[1] == 'C' && tmp.room3[2] == 'C' && tmp.room3[3] == 'C')) {
                                    if (!checkWayFree(tmp.hallway,i,6,true)) continue;
                                    Configuration newConfig = tmp.copy();
                                    newConfig.energyConsumed += (Math.abs(i - 6) + (newConfig.room3[3] == '.' ? 4 : newConfig.room3[2] == '.' ? 3 : newConfig.room3[1] == '.' ? 2 :1)) * 100;
                                    newConfig.room3[(newConfig.room3[3] == '.' ? 3 : newConfig.room3[2] == '.' ? 2 : newConfig.room3[1] == '.' ? 1 :0)] = 'C';
                                    newConfig.hallway[i] = '.';
                                    if (alreadyChecked.stream().noneMatch(newConfig::equal)) {
                                        configQueue.add(newConfig);
                                        alreadyChecked.add(newConfig.copy());
                                    }
                                }
                            }
                            case 'D' -> {
                                if ((tmp.room4[3] == '.') || (tmp.room4[2] == '.' && tmp.room4[3] == 'D') || (tmp.room4[1] == '.' && tmp.room4[2] == 'D' && tmp.room4[3] == 'D') || (tmp.room4[0] == '.' && tmp.room4[1] == 'D' && tmp.room4[2] == 'D' && tmp.room4[3] == 'D')) {
                                    if (!checkWayFree(tmp.hallway,i,8,true)) continue;
                                    Configuration newConfig = tmp.copy();
                                    newConfig.energyConsumed += (Math.abs(i - 8) + (newConfig.room4[3] == '.' ? 4 : newConfig.room4[2] == '.' ? 3 : newConfig.room4[1] == '.' ? 2 :1)) * 1000;
                                    newConfig.room4[(newConfig.room4[3] == '.' ? 3 : newConfig.room4[2] == '.' ? 2 : newConfig.room4[1] == '.' ? 1 :0)] = 'D';
                                    newConfig.hallway[i] = '.';
                                    if (alreadyChecked.stream().noneMatch(newConfig::equal)) {
                                        configQueue.add(newConfig);
                                        alreadyChecked.add(newConfig.copy());
                                    }
                                }
                            }
                        }
                    }
                }
                //TODO für die Räume
                int iR1 = firstFree(tmp.room1,'A');
                int iR2 = firstFree(tmp.room2,'B');
                int iR3 = firstFree(tmp.room3,'C');
                int iR4 = firstFree(tmp.room4,'D');
                if (iR1 >= 0) {
                    for (int i = 0; i < hallway.length; i++) {
                        if (i == 2 || i == 4 || i == 6 || i == 8) continue;
                        if (!checkWayFree(tmp.hallway,2,i,false)) continue;
                        Configuration newConfig = tmp.copy();
                        int factor = 1;
                        switch (tmp.room1[iR1]) {
                            case 'B' -> factor = 10;
                            case 'C' -> factor = 100;
                            case 'D' -> factor = 1000;
                        }
                        newConfig.energyConsumed += (Math.abs(2 - i) + (iR1 + 1)) * factor;
                        newConfig.hallway[i] = tmp.room1[iR1];
                        newConfig.room1[iR1] = '.';
                        if (alreadyChecked.stream().noneMatch(newConfig::equal)) {
                            configQueue.add(newConfig);
                            alreadyChecked.add(newConfig.copy());
                        }
                    }
                }
                if (iR2 >= 0) {
                    for (int i = 0; i < hallway.length; i++) {
                        if (i == 2 || i == 4 || i == 6 || i == 8) continue;
                        if (!checkWayFree(tmp.hallway,4,i,false)) continue;
                        Configuration newConfig = tmp.copy();
                        int factor = 1;
                        switch (tmp.room2[iR2]) {
                            case 'B' -> factor = 10;
                            case 'C' -> factor = 100;
                            case 'D' -> factor = 1000;
                        }
                        newConfig.energyConsumed += (Math.abs(4 - i) + (iR2 + 1)) * factor;
                        newConfig.hallway[i] = tmp.room2[iR2];
                        newConfig.room2[iR2] = '.';
                        if (alreadyChecked.stream().noneMatch(newConfig::equal)) {
                            configQueue.add(newConfig);
                            alreadyChecked.add(newConfig.copy());
                        }
                    }
                }
                if (iR3 >= 0) {
                    for (int i = 0; i < hallway.length; i++) {
                        if (i == 2 || i == 4 || i == 6 || i == 8) continue;
                        if (!checkWayFree(tmp.hallway,6,i,false)) continue;
                        Configuration newConfig = tmp.copy();
                        int factor = 1;
                        switch (tmp.room3[iR3]) {
                            case 'B' -> factor = 10;
                            case 'C' -> factor = 100;
                            case 'D' -> factor = 1000;
                        }
                        newConfig.energyConsumed += (Math.abs(6 - i) + (iR3 + 1)) * factor;
                        newConfig.hallway[i] = tmp.room3[iR3];
                        newConfig.room3[iR3] = '.';
                        if (alreadyChecked.stream().noneMatch(newConfig::equal)) {
                            configQueue.add(newConfig);
                            alreadyChecked.add(newConfig.copy());
                        }
                    }
                }
                if (iR4 >= 0) {
                    for (int i = 0; i < hallway.length; i++) {
                        if (i == 2 || i == 4 || i == 6 || i == 8) continue;
                        if (!checkWayFree(tmp.hallway,8,i,false)) continue;
                        Configuration newConfig = tmp.copy();
                        int factor = 1;
                        switch (tmp.room4[iR4]) {
                            case 'B' -> factor = 10;
                            case 'C' -> factor = 100;
                            case 'D' -> factor = 1000;
                        }
                        newConfig.energyConsumed += (Math.abs(8 - i) + (iR4 + 1)) * factor;
                        newConfig.hallway[i] = tmp.room4[iR4];
                        newConfig.room4[iR4] = '.';
                        if (alreadyChecked.stream().noneMatch(newConfig::equal)) {
                            configQueue.add(newConfig);
                            alreadyChecked.add(newConfig.copy());
                        }
                    }
                }
            }
        }
        System.out.println(minEnergy);

    }

    static boolean validSolution(char[] way, int to, int from, int fromVal) {
       /* for (int i = from + 1; i < way.length; i++) {
            switch (way[i]) {
                case 'A' -> {
                    if (to > 2 && to < fromVal) return false;
                }
            }
        }*/
        return true;
    }

    static int firstFree(char[] l, char check) {
        int c = 0;
        for (; c < l.length; c++) {
            if (l[c] != '.') break;
        }
        if (c == l.length - 1) if (l[c] == check) return -1; else return c;

        if (c < l.length) {
            if (l[c] != check) return c;
            for (int i = c + 1; i < l.length; i++) {
                if (l[i] != check) return c;
            }
        }
        return -1;
    }

    static boolean checkWayFree(char[] way, int start, int end, boolean ignoreStart) {
        if (start < end) for (int i = start + (ignoreStart ? 1 : 0); i <= end; i++) {
            if (way[i] != '.') return false;
        } else for (int i = start - (ignoreStart ? 1 : 0); i >= end; i--) {
            if (way[i] != '.') return false;
        }
        return true;
    }

}
