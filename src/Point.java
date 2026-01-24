public class Point {
    public int x, y, guess, answer;
    public int bias;
    public int[] pointData = new int[5];

    public Point(int x, int y, int answer) {
        this.x = x;
        this.y = y;
        this.bias = 1;
        this.guess = 0;
        this.answer = answer;
        pointData[0] = this.x;
        pointData[1] = this.y;
        pointData[2] = this.bias;
        pointData[3] = this.guess;
        pointData[4] = this.answer;
    }
}
