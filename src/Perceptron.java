import java.util.List;
import java.util.Random;

public class Perceptron {

    private double[] weights;
    private double rate;
    private double bias;

    public Perceptron(double rate) {
        this.rate = rate;
        this.bias = Math.random();
    }

    public void train(List<Node> trainingData, int epochs){
        weights = new double[trainingData.get(0).getVector().size()];
        Random random = new Random();
        for (int i = 0; i < weights.length; i++) {
            weights[i] = random.nextDouble();
        }

        for (int i = 0; i < epochs; i++) {
            for (Node node : trainingData)  {
                List<Double> inputs = node.getVector();
                int target = node.getLabel();
                //potrzebuje double[]
                double[] inputsArray = inputs.stream().mapToDouble(Double::doubleValue).toArray();
                //teraz działa
                int output = classify(inputsArray);
                int difference = target - output;

                //wagi
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += rate * difference * inputs.get(j);
                }
                //
                bias += rate * difference;
            }
        }
    }

    public int classify(double[] inputs){
        double sum = bias;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * inputs[i];
        }
        return sum >= 0 ? 1 : 0;
    }

}
