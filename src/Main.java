import java.util.Scanner;

public class Main {
    public static float f(float m, int x, int b) {
        return m*x + b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Perceptron p = new Perceptron();
        System.out.print("Number of Points = ");
        int no_of_points = sc.nextInt();
        Point[] points = new Point[no_of_points];
        Image image = new Image();
        System.out.println("1024x1024 Blank Image generated");
        System.out.print("m = ");
        float m = sc.nextFloat();
        System.out.println();
        System.out.print("b = ");
        int b = sc.nextInt();
        System.out.println();
        for (int i=0 ; i<no_of_points ; i++) {
            int x = (int) (Math.random()*1024-512);
            int y = (int) (Math.random()*1024-512);
            int ans = (float) y > f(m,x,b) ? -1 : 1;
            points[i] = new Point(x,y,ans);
        }
        System.out.println();
        while (true) {
            for (Point pt : points) {
                int[] pointData = pt.pointData;
                p.guess(pointData);
                image.drawPoint(pointData);
                if (pointData[3] != pointData[4]) {
                    System.out.println("x " + pointData[0]);
                    System.out.println("y " + pointData[1]);
                    System.out.println("bias " + pointData[2]);
                    System.out.println("guess " + pointData[3]);
                    System.out.println("answer " + pointData[4]);
                    System.out.println();
                }

                p.train(pointData);
            }
            image.createFile();
            //System.out.print("exit = 0: ");
            //int exit = sc.nextInt();
            //if (exit == 0) {break;}
            image.cleanPage();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
        }
    }
}