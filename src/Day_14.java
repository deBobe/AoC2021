import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day_14 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> start = new ArrayList<>(Arrays.asList(Files.readAllLines(Paths.get("res/d14.txt")).get(0).split("")));
        Map<String,String> replacements = Files.readAllLines(Paths.get("res/d14.txt")).stream().skip(2).map(s -> (s.split(" -> "))).collect(Collectors.toMap(k -> k[0],e -> e[1]));

        Map<String,Long> ruleOccurrences = new HashMap<>();
        Map<String,Long> singleOccurrences = new HashMap<>();

        for (int i = 0; i < start.size() - 1; i++) {
            if (!ruleOccurrences.containsKey(start.get(i) + start.get(i+1))) ruleOccurrences.put(start.get(i) + start.get(i+1),1L);
            else ruleOccurrences.replace(start.get(i) + start.get(i+1), ruleOccurrences.get(start.get(i) + start.get(i+1)) + 1);
        }
        for (String s : start) {
            if (!singleOccurrences.containsKey(s)) singleOccurrences.put(s, 1L);
            else singleOccurrences.replace(s, singleOccurrences.get(s) + 1);
        }

        for (int i = 0; i < 40; i++) {
            Map<String,Long> newRuleOccurrences = new HashMap<>();
            for (int j = 0; j < ruleOccurrences.size(); j++) {
                String key = (String) ruleOccurrences.keySet().toArray()[j];
                long amount = (long) ruleOccurrences.values().toArray()[j];

                if (!singleOccurrences.containsKey(replacements.get(key))) singleOccurrences.put(replacements.get(key), amount);
                else singleOccurrences.replace(replacements.get(key), singleOccurrences.get(replacements.get(key)) + amount);

                String newKey1 = key.charAt(0) + replacements.get(key);
                String newKey2 = replacements.get(key) + key.charAt(1);

                if (!newRuleOccurrences.containsKey(newKey1)) newRuleOccurrences.put(newKey1,amount);
                else newRuleOccurrences.replace(newKey1, newRuleOccurrences.get(newKey1) + amount);

                if (!newRuleOccurrences.containsKey(newKey2)) newRuleOccurrences.put(newKey2,amount);
                else newRuleOccurrences.replace(newKey2, newRuleOccurrences.get(newKey2) + amount);
            }
            ruleOccurrences = newRuleOccurrences;
        }

        long max = Collections.max(singleOccurrences.entrySet(), Map.Entry.comparingByValue()).getValue();
        long min = Collections.min(singleOccurrences.entrySet(), Map.Entry.comparingByValue()).getValue();

        System.out.println(max - min);
    }
}
