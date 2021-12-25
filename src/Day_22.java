import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day_22 {

    static class Cuboid {
        boolean turnOn;
        int startX;
        int endX;

        int startY;
        int endY;

        int startZ;
        int endZ;

        public Cuboid(boolean turnOn, int startX, int endX, int startY, int endY, int startZ, int endZ) {
            this.turnOn = turnOn;
            this.startX = startX;
            this.endX = endX;
            this.startY = startY;
            this.endY = endY;
            this.startZ = startZ;
            this.endZ = endZ;
        }
        public Cuboid(){

        }
    }

     static class Step extends Cuboid {
         public Step(String s) {
             this.turnOn = s.split(" ")[0].equals("on");
             String[] str = s.split("=|,|\\.\\.");
             this.startX = Integer.parseInt(str[1]);
             this.endX = Integer.parseInt(str[2]);
             this.startY = Integer.parseInt(str[4]);
             this.endY = Integer.parseInt(str[5]);
             this.startZ = Integer.parseInt(str[7]);
             this.endZ = Integer.parseInt(str[8]);
         }
    }

    public static void main(String[] args) throws IOException {

        List<Step> steps = Files.readAllLines(Paths.get("res/d22.txt")).stream().map(Step::new).collect(Collectors.toList());

        ArrayList<Cuboid> turnedOn = new ArrayList<>();
        for (Step step : steps) {
          //  if (Math.abs(step.endZ) > 50 || Math.abs(step.endX) > 50 || Math.abs(step.endY) > 50 || Math.abs(step.startX) > 50|| Math.abs(step.startY) > 50|| Math.abs(step.startZ) > 50) break;
            if (step.turnOn) {
                ArrayList<Cuboid> toAdd = new ArrayList<>();
                toAdd.add(step);
                int added = 1;

                while (added > 0) {
                    added = 0;
                    ArrayList<Cuboid> newToAdd = new ArrayList<>();

                    for (Cuboid cuboid : toAdd) {
                        boolean notFree = false;
                        for (Cuboid control: turnedOn) {
                            if (control.endX >= cuboid.startX && control.endY >= cuboid.startY && control.endZ >= cuboid.startZ && (control.startX <= cuboid.endX && control.startY <= cuboid.endY && control.startZ <= cuboid.endZ)) {
                                //bottom
                                notFree = true;
                               // added++;
                                if (control.startZ > cuboid.startZ) {
                                    newToAdd.add(new Cuboid(true, cuboid.startX,cuboid.endX, cuboid.startY, cuboid.endY, cuboid.startZ, control.startZ - 1));
                                    added ++;
                                }
                               // top
                                if (control.endZ < cuboid.endZ) {
                                    newToAdd.add(new Cuboid(true, cuboid.startX,cuboid.endX, cuboid.startY, cuboid.endY, control.endZ + 1, cuboid.endZ));
                                    added ++;
                                }
                              //left
                                if (control.startY > cuboid.startY) {
                                    newToAdd.add(new Cuboid(true, cuboid.startX,cuboid.endX, cuboid.startY, control.startY - 1, Math.max(cuboid.startZ, control.startZ), Math.min(cuboid.endZ, control.endZ)));
                                    added ++;
                                }
                              //right
                                if (control.endY < cuboid.endY) {
                                    newToAdd.add(new Cuboid(true, cuboid.startX,cuboid.endX, control.endY + 1, cuboid.endY, Math.max(cuboid.startZ, control.startZ), Math.min(cuboid.endZ, control.endZ)));
                                    added ++;
                                }
                                //front
                                if (control.startX > cuboid.startX) {
                                    newToAdd.add(new Cuboid(true, cuboid.startX,control.startX - 1, Math.max(cuboid.startY,control.startY), Math.min(cuboid.endY,control.endY), Math.max(cuboid.startZ, control.startZ), Math.min(cuboid.endZ,control.endZ)));
                                    added ++;
                                }
                              //back
                                if (control.endX < cuboid.endX) {
                                    newToAdd.add(new Cuboid(true, control.endX + 1,cuboid.endX, Math.max(cuboid.startY,control.startY), Math.min(cuboid.endY,control.endY), Math.max(cuboid.startZ,control.startZ), Math.min(cuboid.endZ,control.endZ)));
                                    added ++;
                                }
                                break;
                            }
                        }
                        if (!notFree) newToAdd.add(cuboid);
                    }
                    toAdd = newToAdd;
                }
                turnedOn.addAll(toAdd);
            } else {
                ArrayList<Cuboid> newTurnedOn = new ArrayList<>();
                for (Cuboid control : turnedOn) {
                    if (step.endX >= control.startX && step.endY >= control.startY && step.endZ >= control.startZ && (step.startX <= control.endX && step.startY <= control.endY && step.startZ <= control.endZ)) {
                        ArrayList<Cuboid> toAdd = new ArrayList<>();
                        toAdd.add(control);
                        int added = 1;
                        while (added > 0) {
                            added = 0;
                            ArrayList<Cuboid> newToAdd = new ArrayList<>();
                            for (Cuboid cuboid : toAdd) {
                                boolean notFree = false;
                                if (step.endX >= cuboid.startX && step.endY >= cuboid.startY && step.endZ >= cuboid.startZ && (step.startX <= cuboid.endX && step.startY <= cuboid.endY && step.startZ <= cuboid.endZ)) {
                                    // bottom
                                    notFree = true;
                                    if (step.startZ > cuboid.startZ) {
                                        newToAdd.add(new Cuboid(true, cuboid.startX,cuboid.endX, cuboid.startY, cuboid.endY, cuboid.startZ, step.startZ - 1));
                                        added ++;
                                    }
                                    //top
                                    if (step.endZ < cuboid.endZ) {
                                        newToAdd.add(new Cuboid(true, cuboid.startX,cuboid.endX, cuboid.startY, cuboid.endY, step.endZ + 1, cuboid.endZ));
                                        added ++;
                                    }
                                    //left
                                    if (step.startY > cuboid.startY) {
                                        newToAdd.add(new Cuboid(true, cuboid.startX,cuboid.endX, cuboid.startY, step.startY - 1, Math.max(cuboid.startZ,step.startZ), Math.min(cuboid.endZ,step.endZ)));
                                        added ++;
                                    }
                                    //right
                                    if (step.endY < cuboid.endY) {
                                        newToAdd.add(new Cuboid(true, cuboid.startX,cuboid.endX, step.endY + 1, cuboid.endY, Math.max(cuboid.startZ,step.startZ), Math.min(cuboid.endZ,step.endZ)));
                                        added ++;
                                    }
                                    //front
                                    if (step.startX > cuboid.startX) {
                                        newToAdd.add(new Cuboid(true, cuboid.startX,step.startX - 1, Math.max(cuboid.startY,step.startY), Math.min(cuboid.endY,step.endY), Math.max(cuboid.startZ,step.startZ), Math.min(cuboid.endZ,step.endZ)));
                                        added ++;
                                    }
                                    //back
                                    if (step.endX < cuboid.endX) {
                                        newToAdd.add(new Cuboid(true, step.endX + 1,cuboid.endX, Math.max(cuboid.startY,step.startY), Math.min(cuboid.endY,step.endY), Math.max(cuboid.startZ,step.startZ), Math.min(cuboid.endZ,step.endZ)));
                                        added ++;
                                    }
                                }
                                if (!notFree) newToAdd.add(cuboid);
                            }
                            toAdd = newToAdd;
                        }
                        newTurnedOn.addAll(toAdd);
                    } else {
                        newTurnedOn.add(control);
                    }
                }
                turnedOn = newTurnedOn;
            }
        }

        long sum = 0;

        for (Cuboid c : turnedOn) {
            sum += ((long) (Math.abs(c.endX - c.startX) + 1) * (Math.abs(c.endY - c.startY) + 1) * (Math.abs(c.endZ - c.startZ) + 1));
        }
        System.out.println(sum);
    }

}
