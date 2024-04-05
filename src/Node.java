import java.util.List;

public class Node {
    private List<Double> vector;
    private int label;

    public Node(List<Double> vector, int label) {
        this.vector = vector;
        this.label = label;
    }

    public List<Double> getVector() {
        return vector;
    }

    public int getLabel() {
        return label;
    }
}
