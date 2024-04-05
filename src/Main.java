import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3)   {
            System.out.println("<alpha> <train> <test>");
            return;
        }

        double alpha = Double.parseDouble(args[0]);
        String trainFile = args[1];
        String testFile = args[2];
        int epochs = 100;

        List<Node> trainingData = FileManager.getList(trainFile);
        List<Node> testData = FileManager.getList(testFile);

        Perceptron perceptron = new Perceptron(alpha);
        perceptron.train(trainingData,epochs);

        int correctCount = 0;
        int[] speciesCorrectCount = new int[2];
        int[] speciesTotalCount = new int[2];

        for (Node node : testData) {
            List<Double> inputs = node.getVector();
            int target = node.getLabel();
            double[] inputsArray = inputs.stream().mapToDouble(Double::doubleValue).toArray();
            int output = perceptron.classify(inputsArray);

            if (output == target) {
                correctCount++;
                speciesCorrectCount[target]++;
            }
            speciesTotalCount[target]++;
        }

        double accuracy = (double) correctCount / testData.size() * 100;
        System.out.println("Procent poprawnie rozpoznanych kwiatów: " + accuracy + "%");

        for (int i = 0; i < 2; i++) {
            double speciesAccuracy = (double) speciesCorrectCount[i] / speciesTotalCount[i] * 100;
            System.out.println("Procent poprawnie rozpoznanych kwiatów dla flagi " + i + ": " + speciesAccuracy + "%");
        }
    }
}