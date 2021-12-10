import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day_7 {

    public static void main(String[] args) throws IOException {
        List<Long> list = Arrays.stream(Files.readString(Paths.get("src/entries_d7.txt")).split(",")).map(Long::parseLong).collect(Collectors.toList());
        long max = list.stream().max(Comparator.naturalOrder()).isPresent() ? list.stream().max(Comparator.naturalOrder()).get() : 0;
        long min = 1;
        while (Math.abs(min - max) > 1) {
            long finalMin = min;
            long minVal = list.stream().reduce(0L,(s, e) -> s += Math.abs(e- finalMin));
            long finalMax = max;
            long maxVal = list.stream().reduce(0L,(s, e) -> s += Math.abs(e- finalMax));
            if (minVal < maxVal) max = min + (max-min) / 2;
            else min += (max-min) / 2;
        }
        long finalMin1 = min;
        long finalMax1 = max;
        System.out.println(Math.min(list.stream().reduce(0L,(s, e) -> s += Math.abs(e- finalMin1)),list.stream().reduce(0L,(s, e) -> s += Math.abs(e- finalMax1))));

        long x = (long) (list.stream().reduce(0L,(s, e)->s+=e) / (float)list.size());
        System.out.println(Math.min(list.stream().reduce(0L,(s, e) -> s += (Math.abs(e- x)* (Math.abs(e- x) + 1) / 2)),list.stream().reduce(0L,(s, e) -> s += (Math.abs(e- (x+1))* (Math.abs(e- (x+1)) + 1) / 2))));
    }

}
