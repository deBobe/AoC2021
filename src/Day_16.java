import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day_16 {

    public static void main(String[] args) throws IOException {
        String[] hexList = Files.readString(Paths.get("res/d16.txt")).split("");
        StringBuilder binList = new StringBuilder();
        for (String s : hexList) binList.append(hexToBin(s));

        System.out.println(parsePacketAt(binList,0)[0]);
        System.out.println(parsePacketAt2(binList,0)[0]);
    }

    static String hexToBin(String hex) {
        StringBuilder bin = new StringBuilder(Integer.toBinaryString(Integer.parseInt(hex, 16)));
        while (bin.length() < 4) bin.insert(0, "0");
        return bin.toString();
    }

    static int[] parsePacketAt(StringBuilder s, int pos) {
        int version = Integer.parseInt(s.substring(pos,pos + 3),2);
        int typeID = Integer.parseInt(s.substring(pos+3,pos + 6),2);
        int lengthID;
        int end = pos + 6;

        if (typeID == 4) {
            char tmp = s.charAt(end);
            while (tmp != '0') {
                end+=5;
                tmp = s.charAt(end);
            }
            end += 5;
            return new int[]{version,end};
        } else {
            lengthID = Integer.parseInt(String.valueOf(s.charAt(pos+6)),2);
            end += 1;
            if (lengthID == 0) {
                int length = Integer.parseInt(s.substring(end,end + 15),2);
                int tmpEnd = end + 15;
                int val = 0;
                end += 15 + length;
                while (tmpEnd != end) {
                    int[] newPacket = parsePacketAt(s,tmpEnd);
                    tmpEnd = newPacket[1];
                    val += newPacket[0];
                }
                return new int[]{version + val,end};
            } else {
                int numberOfPackages = Integer.parseInt(s.substring(end,end + 11),2);
                end += 11;
                int val = 0;
                for (int i = 0; i < numberOfPackages; i++) {
                    int[] newPacket = parsePacketAt(s,end);
                    end = newPacket[1];
                    val += newPacket[0];
                }
                return new int[]{version + val,end};
            }
        }
    }

    static long[] parsePacketAt2(StringBuilder s, int pos) {
        int typeID = Integer.parseInt(s.substring(pos+3,pos + 6),2);
        int lengthID;
        int end = pos + 6;

        if (typeID == 4) {
            StringBuilder sub = new StringBuilder();
            char tmp = s.charAt(end);
            while (tmp != '0') {
                sub.append(s.substring(end + 1, end + 5));
                end+=5;
                tmp = s.charAt(end);
            }
            sub.append(s.substring(end + 1, end + 5));
            end += 5;
            return new long[]{Long.parseLong(sub.toString(),2),end};
        } else {
            lengthID = Integer.parseInt(String.valueOf(s.charAt(pos+6)),2);
            end += 1;
            ArrayList<Long> values = new ArrayList<>();
            if (lengthID == 0) {
                int length = Integer.parseInt(s.substring(end,end + 15),2);
                int tmpEnd = end + 15;
                end += 15 + length;
                while (tmpEnd != end) {
                    long[] newPacket = parsePacketAt2(s,tmpEnd);
                    tmpEnd = (int) newPacket[1];
                    values.add(newPacket[0]);
                }
            } else {
                int numberOfPackages = Integer.parseInt(s.substring(end,end + 11),2);
                end += 11;
                for (int i = 0; i < numberOfPackages; i++) {
                    long[] newPacket = parsePacketAt2(s,end);
                    end = (int) newPacket[1];
                    values.add(newPacket[0]);
                }
            }
            switch (typeID) {
                case 0 -> {return new long[]{values.stream().reduce(Long::sum).orElse(0L),end};}
                case 1 -> {return new long[]{values.stream().reduce(1L,(p,e) -> p*=e),end};}
                case 2 -> {return new long[]{values.stream().reduce(Long::min).orElse(0L),end};}
                case 3 -> {return new long[]{values.stream().reduce(Long::max).orElse(0L),end};}
                case 5 -> {return new long[]{values.get(0) > values.get(1) ? 1 : 0,end};}
                case 6 -> {return new long[]{values.get(0) < values.get(1) ? 1 : 0,end};}
                case 7 -> {return new long[]{values.get(0).equals(values.get(1)) ? 1 : 0,end};}
                default -> {return new long[]{0,0};}
            }
        }
    }
}
