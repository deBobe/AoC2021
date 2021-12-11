import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day_6 {

    public static void main(String[] args) throws IOException {
        List<Integer> list = Arrays.stream(Files.readString(Paths.get("res/d6.txt")).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        long start = System.nanoTime();
        long sum = 0;
        long[] numbers = new long[9];
        for (int i : list) {
            numbers[i]++;
        }

        for (int i = 0,j=0; i < 256; i++,j = (j+1)%9) {
            if (i == 80) {
                sum += System.nanoTime()-start;
                System.out.println(Arrays.stream(numbers).reduce(0,(e,s)->e+=s));
                start = System.nanoTime();
            }
            numbers[(j+7)%9]+=numbers[j];
        }
        long end = System.nanoTime();
        System.out.println(Arrays.stream(numbers).reduce(0,(e,s)->e+=s));
        System.out.println((sum + end - start) + "ns");


     /*   long start = System.nanoTime();
        long su = 0;
        for (int i = 0; i < 256; i++) {
            long n = numbers[0];
            if (i == 80) {
                su += System.nanoTime()-start;
                System.out.println(Arrays.stream(numbers).reduce(0,(e,s)->e+=s));
                start = System.nanoTime();
            }
            for (int j = 8; j >=0; j--) {
                long m = numbers[j];
                numbers[j] = n;
                n = m;
                if(j==6) numbers[6] += numbers[0];
            }
        }*/


     //   System.out.println((su + end - start) + "ns");
//1605400130036
    }



}
