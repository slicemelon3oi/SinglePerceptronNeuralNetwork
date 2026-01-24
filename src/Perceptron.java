public class Perceptron {
    float[] weights = new float[3];
    float LR = 0.5f;

    public float[] getWeights() {
        return weights;
    }

    public Perceptron() {
        for (float i : weights) {
            i = (float) (Math.random()*2-1);
        }
    }

    public void guess(int[] pointData) {
        float sum = 0;
        for (int i=0 ; i<weights.length ; i++) {
            sum += weights[i]* pointData[i];
        }
        pointData[3] = sum > 0 ? 1 : -1;
    }

    public void train(int[] pointData) {
        int guess = pointData[3];
        int answer = pointData[4];
        int error = answer - guess;
        for (int i=0 ; i<weights.length ; i++) {
            weights[i] += error * pointData[i] * LR;
        }
    }
}
