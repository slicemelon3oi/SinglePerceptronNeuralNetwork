import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Image {
    BufferedImage Out;
    Graphics2D OutG;
    File folder;
    File OutF;
    static int fileNo = 0;

    public Image() {
        folder = new File("./src/Output");
        if (folder.exists()) {
            File[] children = folder.listFiles();
            if (children != null) {
                for (File child : children) {
                    child.delete();
                }
            }
            if (folder.delete()) {
                System.out.println("Deleted previous Output Folder.");
            }
        }
        if (folder.mkdirs()) {
            System.out.println("Created new Output Folder.");
        }
        Out = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
        cleanPage();
        createFile();
    }

    public int[] map(int[] pointData) {
        int x = pointData[0];
        int y = pointData[1];
        int x1,y1;
        x1 = 512 + x;
        y1 = 512 - y;
        return new int[] {x1,y1};
    }

    public void cleanPage() {
        OutG = Out.createGraphics();
        OutG.setColor(Color.white);
        OutG.fillRect(0,0,1024,1024);
        OutG.dispose();
    }

    public void createFile() {
        OutF = new File("src/Output/out"+fileNo+".png");
        fileNo++;
        try {
            ImageIO.write(Out,"png",OutF);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void drawPoint(int[] pointData) {
        OutG = Out.createGraphics();
        int[] mappedPoints = map(pointData);
        int x = mappedPoints[0];
        int y = mappedPoints[1];
        int guess = pointData[3];
        if (guess > 0) {
            OutG.setColor(Color.blue);
        } else {
            OutG.setColor(Color.red);
        }
        OutG.fillOval(x,y,5,5);
        OutG.dispose();
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        OutG = Out.createGraphics();
        OutG.setColor(Color.black);
        OutG.drawLine(x1,y1,x2,y2);
        OutG.dispose();
    }
}
