import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<Node> getList(String file){
        List<Node> nodeList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file)))  {
            String line;
            while ((line = bufferedReader.readLine()) != null)  {
                String[] tmp = line.split(",");
                List<Double> values = new ArrayList<>();
                for (int i = 0; i < tmp.length - 1; i++)
                    values.add(Double.parseDouble(tmp[i]));
                int label = getLabel(tmp[tmp.length - 1]);
                nodeList.add(new Node(values, label));
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return nodeList;
    }

    private static int getLabel(String label)   {
        if (label.equals("Iris-versicolor")){
            return 0;
        }else if (label.equals("Iris-virginica"))   {
            return 1;
        }else
            return -1;
    }
}
