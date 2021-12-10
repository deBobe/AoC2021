import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day_10 {

    public static void main(String[] args) throws IOException {
        List<char[]> list = Files.readAllLines(Paths.get("src/entries_d10.txt")).stream().map(String::toCharArray).collect(Collectors.toList());

        long sum = 0;

        List<Stack<Character>> incompleteLists = new ArrayList<>();

        for (char[] s : list) {
            Stack<Character> stack = new Stack<>();
            boolean discard = false;
            for (char c : s) {
                if (c == '(' || c == '{' || c == '[' || c == '<') stack.push(c);
                else if (c == ')' && stack.pop() != '(') {
                    sum += 3;
                    discard = true;
                    break;
                }
                else if (c == '>' && stack.pop() != '<') {
                    sum += 25137;
                    discard = true;
                    break;
                }
                else if (c == '}' && stack.pop() != '{') {
                    sum += 1197;
                    discard = true;
                    break;
                }
                else if (c == ']' && stack.pop() != '[') {
                    sum += 57;
                    discard = true;
                    break;
                }
            }
            if (!discard) incompleteLists.add(stack);
        }
        System.out.println(sum);

        ArrayList<Long> values = new ArrayList<>();

        for (Stack<Character> s : incompleteLists) {
            values.add(0L);
            final int f_size = s.size();
            for (int i = 0; i < f_size; i++) {
                values.set(values.size()-1, values.get(values.size() -1) * 5);
                switch (s.pop()) {
                    case '(' -> values.set(values.size()-1, values.get(values.size() -1) + 1);
                    case '{' -> values.set(values.size()-1, values.get(values.size() -1) + 3);
                    case '<' -> values.set(values.size()-1, values.get(values.size() -1) + 4);
                    case '[' -> values.set(values.size()-1, values.get(values.size() -1) + 2);
                }
            }
        }
        Collections.sort(values);
        System.out.println(values.get(values.size() / 2));

    }

}
