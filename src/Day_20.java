import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day_20 {

    public static void main(String[] args) throws IOException {

        String[] algorithm = Files.readAllLines(Paths.get("res/d20.txt")).get(0).split("");

        ArrayList<ArrayList<String>> image = Files.readAllLines(Paths.get("res/d20.txt")).stream().map(s -> new ArrayList<>(Arrays.asList(s.split("")))).collect(Collectors.toCollection(ArrayList::new));

        image.remove(0);
        image.remove(0);
        String padding = ".";
        addPadding(image,padding);

        int[][] surroundings = new int[][] {{-1,-1},{0,-1},{1,-1},{-1,0},{0,0},{1,0},{-1,1},{0,1},{1,1}};

        for (int i = 0; i < 50; i++) {
            ArrayList<ArrayList<String>> newImage = image.stream().map(ArrayList::new).collect(Collectors.toCollection(ArrayList::new));;
            for (int y = 0; y < image.size(); y++) {
                for (int x = 0; x < image.get(y).size(); x++) {
                    StringBuilder value = new StringBuilder();
                    for (int[] s : surroundings) {
                        int posX = x + s[0];
                        int posY = y + s[1];
                        if (posX < 0 || posY < 0 || posX >= image.size() || posY >= image.size()) value.append(padding.equals(".") ? 0 : 1);
                        else value.append(image.get(posY).get(posX).equals(".") ? 0 : 1);
                    }
                    newImage.get(y).set(x, algorithm[Integer.parseInt(String.valueOf(value),2)]);
                }
            }
            image = newImage;
            String paddingValue = padding.equals(".") ? "0" : "1";
            padding = algorithm[Integer.parseInt(paddingValue+paddingValue+paddingValue+paddingValue+paddingValue+paddingValue+paddingValue+paddingValue+paddingValue,2)];
            addPadding(image,padding);
        }

        System.out.println(image.stream().reduce(0,(s,e) -> s += e.stream().reduce(0, (t,f) -> t += f.equals("#") ? 1 : 0,Integer::sum),Integer::sum));

    }

    static void addPadding(ArrayList<ArrayList<String>> image, String padding) {
        String[] p = new String[image.size() + 2];
        Arrays.fill(p,padding);
        for (ArrayList<String> l : image) {

            l.add(0,padding);
            l.add(l.size(), padding);
        }
        image.add(0,new ArrayList<>(Arrays.asList(p.clone())));
        image.add(image.size(),new ArrayList<>(Arrays.asList(p.clone())));
    }
}
