import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day_18 {

    public static void main(String[] args) throws IOException {

        List<String> input = Files.readAllLines(Paths.get("res/d18.txt"));

        //very ugly and very much pain

        String finalNumber = input.get(0);
        input.remove(0);
        for (String s : input) {
            String newNumber = "[" + finalNumber + "," + s +"]";
            int reductions = 1;

            while (reductions > 0) {
                reductions = 0;
                int bracketsCount = 0;
                int i = 0;
                for (char c : newNumber.toCharArray()) {
                    if (c == '[') bracketsCount ++;
                    else if (c == ']') bracketsCount --;

                    if (bracketsCount > 4) {
                        int leftEnd = i;
                        int rightEnd = i;
                        while (newNumber.charAt(leftEnd) != ',') leftEnd ++;
                        while (newNumber.charAt(rightEnd) != ']') rightEnd ++;
                        int left = Integer.parseInt(newNumber.substring(i+1,leftEnd));
                        int right = Integer.parseInt(newNumber.substring(leftEnd+1,rightEnd));
                        int indexLeft = i + 1;
                        int indexRight = rightEnd;
                        boolean found = false;
                        while (!found && indexLeft > 0) {
                            indexLeft--;
                            if (newNumber.charAt(indexLeft) < 58 && newNumber.charAt(indexLeft) > 47) {
                                found = true;
                            }
                        }

                        while (newNumber.charAt(indexLeft) < 58 && newNumber.charAt(indexLeft) > 47) indexLeft--;
                        if (indexLeft != 0) indexLeft++;

                        int indexLeftEnd = indexLeft;
                        while (newNumber.charAt(indexLeftEnd) < 58 && newNumber.charAt(indexLeftEnd) > 47) indexLeftEnd++;

                        found = false;
                        while (!found && indexRight < newNumber.length() - 1) {
                            indexRight++;
                            if (newNumber.charAt(indexRight) < 58 && newNumber.charAt(indexRight) > 47) found = true;
                        }
                        int indexRightEnd = indexRight;
                        while (newNumber.charAt(indexRightEnd) < 58 && newNumber.charAt(indexRightEnd) > 47) indexRightEnd++;

                        String leftPart = newNumber.substring(0, indexLeft) + (indexLeft != 0 ? Integer.parseInt(newNumber.substring(indexLeft,indexLeftEnd)) + left : "")
                                + newNumber.substring(indexLeftEnd,i);
                        String rightPart = newNumber.substring(rightEnd + 1,indexRight) + (indexRight != newNumber.length() - 1 ? Integer.parseInt(newNumber.substring(indexRight,indexRightEnd)) + right : "")
                                + newNumber.substring(indexRightEnd);

                        newNumber = leftPart + 0 + rightPart;
                        reductions++;
                        break;
                    }

                    i++;
                }

                if (reductions == 0) {
                    i = 0;
                    for (char c : newNumber.toCharArray()) {
                        if ((newNumber.charAt(i) < 58 && newNumber.charAt(i) > 47) && (newNumber.charAt(i+1) < 58 && newNumber.charAt(i+1) > 47)) {
                            double value = Double.parseDouble(newNumber.substring(i,i+2));
                            int left = (int) Math.floor(value/2);
                            int right = (int) Math.ceil(value / 2);

                            newNumber = newNumber.substring(0,i) + "[" + left + "," + right + "]" + newNumber.substring(i + 2);
                            reductions++;
                            break;
                        }
                        i++;
                    }
                }
            }
            finalNumber = newNumber;
        }

        while (finalNumber.contains("[")) {
            finalNumber = finalNumber.replaceAll("(\\[)([0-9+*()]+)(,)([0-9+*()]+)(])", "($2+$2+$2+$4+$4)");
        }
        int sum = 0;
        for (char c : finalNumber.toCharArray()) {
            if (c != '(' && c != ')' && c != '+') sum += c-48;
        }
        System.out.println(sum);

        int max = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                if (i != j) {
                    int t = add(input.get(i),input.get(j));
                    if (t > max) max = t;
                }
            }
        }
        System.out.println(max);
    }

    static int add(String s1, String s2) {

        String newNumber = "[" + s1 + "," + s2 +"]";
        int reductions = 1;

        while (reductions > 0) {
            reductions = 0;
            int bracketsCount = 0;
            int i = 0;
            for (char c : newNumber.toCharArray()) {
                if (c == '[') bracketsCount ++;
                else if (c == ']') bracketsCount --;

                if (bracketsCount > 4) {
                    int leftEnd = i;
                    int rightEnd = i;
                    while (newNumber.charAt(leftEnd) != ',') leftEnd ++;
                    while (newNumber.charAt(rightEnd) != ']') rightEnd ++;
                    int left = Integer.parseInt(newNumber.substring(i+1,leftEnd));
                    int right = Integer.parseInt(newNumber.substring(leftEnd+1,rightEnd));
                    int indexLeft = i + 1;
                    int indexRight = rightEnd;
                    boolean found = false;
                    while (!found && indexLeft > 0) {
                        indexLeft--;
                        if (newNumber.charAt(indexLeft) < 58 && newNumber.charAt(indexLeft) > 47) {
                            found = true;
                        }
                    }

                    while (newNumber.charAt(indexLeft) < 58 && newNumber.charAt(indexLeft) > 47) indexLeft--;
                    if (indexLeft != 0) indexLeft++;

                    int indexLeftEnd = indexLeft;
                    while (newNumber.charAt(indexLeftEnd) < 58 && newNumber.charAt(indexLeftEnd) > 47) indexLeftEnd++;

                    found = false;
                    while (!found && indexRight < newNumber.length() - 1) {
                        indexRight++;
                        if (newNumber.charAt(indexRight) < 58 && newNumber.charAt(indexRight) > 47) found = true;
                    }
                    int indexRightEnd = indexRight;
                    while (newNumber.charAt(indexRightEnd) < 58 && newNumber.charAt(indexRightEnd) > 47) indexRightEnd++;

                    String leftPart = newNumber.substring(0, indexLeft) + (indexLeft != 0 ? Integer.parseInt(newNumber.substring(indexLeft,indexLeftEnd)) + left : "")
                            + newNumber.substring(indexLeftEnd,i);
                    String rightPart = newNumber.substring(rightEnd + 1,indexRight) + (indexRight != newNumber.length() - 1 ? Integer.parseInt(newNumber.substring(indexRight,indexRightEnd)) + right : "")
                            + newNumber.substring(indexRightEnd);

                    newNumber = leftPart + 0 + rightPart;
                    reductions++;
                    break;
                }

                i++;
            }

            if (reductions == 0) {
                i = 0;
                for (char c : newNumber.toCharArray()) {
                    if ((newNumber.charAt(i) < 58 && newNumber.charAt(i) > 47) && (newNumber.charAt(i+1) < 58 && newNumber.charAt(i+1) > 47)) {
                        double value = Double.parseDouble(newNumber.substring(i,i+2));
                        int left = (int) Math.floor(value/2);
                        int right = (int) Math.ceil(value / 2);

                        newNumber = newNumber.substring(0,i) + "[" + left + "," + right + "]" + newNumber.substring(i + 2);
                        reductions++;
                        break;
                    }
                    i++;
                }
            }
        }

        while (newNumber.contains("[")) {
            newNumber = newNumber.replaceAll("(\\[)([0-9+*()]+)(,)([0-9+*()]+)(])", "($2+$2+$2+$4+$4)");
        }
        int sum = 0;
        for (char c : newNumber.toCharArray()) {
            if (c != '(' && c != ')' && c != '+') sum += c-48;
        }
        return sum;
    }
}
