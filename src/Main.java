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
        System.out.print("b = ");
        int b = sc.nextInt();
        for (int i=0 ; i<no_of_points ; i++) {
            int x = (int) (Math.random()*1024-512);
            int y = (int) (Math.random()*1024-512);
            int ans = (float) y > f(m,x,b) ? -1 : 1;
            points[i] = new Point(x,y,ans);
        }
        System.out.println();
        boolean check = true;
        int misclassifiedPoints;
        while (check) {
            misclassifiedPoints = 0;
            for (Point pt : points) {
                int[] pointData = pt.pointData;
                p.guess(pointData);
                image.drawPoint(pointData);
                if (pointData[3] != pointData[4]) {
                    misclassifiedPoints++;
                }
                p.train(pointData);
            }
            image.createFile();
            System.out.println("Misclassified Points: " + misclassifiedPoints);
            System.out.println("File Number: " + (Image.fileNo-1));
            if (misclassifiedPoints == 0) {
                check = false;
            } else if (misclassifiedPoints > 6000) {
                p.LR = 0.0002f;
            } else if (misclassifiedPoints > 10) {
                p.LR = 0.0000002f;
            } else {
                p.LR = 0.0000000002f;
            }
            misclassifiedPoints = 0;
            image.cleanPage();
//            try {
//                Thread.sleep(30);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}