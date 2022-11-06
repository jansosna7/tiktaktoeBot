import java.sql.Array;
import java.util.List;
import java.util.Random;

import static java.lang.Math.exp;
import static java.sql.Types.TIME;

public class Bot {
    private int n;
    private double weights[]; //for each neuron, list of weights + bias
    private List<Integer> layers;
    private int numberOfNeurons = 0;
    private double[] values;
    private Board board;

    public Bot(int n, List<Integer> layers, int numberOfNeurons){
        this.n = n;
        weights = new double[n];
        Random rand = new Random(TIME);
        int bound = 10;
        for (int i =0; i<n; i++){
            weights[i] = rand.nextInt(2*bound)-bound;
        }
        this.layers = layers;
        this.numberOfNeurons = numberOfNeurons;
        this.values = new double[numberOfNeurons];
    }
    public Bot(int n, double[] weights, List<Integer> layers){
        this.n = n;
        this.weights = weights;
        this.layers = layers;
        this.numberOfNeurons = numberOfNeurons;
        this.values = new double[numberOfNeurons];
    }

    private double function(double sum){
        return 1/(1+exp(-sum));
    }

    public double[] calculate(){
        int neuron = 0;
        int weight = 0;
        double sum;

        //first layer takes from board
        for(int i = 0; i< layers.get(0); i++){
            sum = 0;
            for(int j = 0; j < 9; j++){
                sum += board.getTile(j)*weights[weight];
                weight++;
            }
            values[neuron] = function(sum+weights[weight]);
            weight++;
            neuron++;
        }
        int prevLayerStart = 0;
        int prevLayerEnd = 9;
        //for every other layer
        for(int layer = 1; layer < layers.size(); layer++){
            for(int i = 0; i< layers.get(layer); i++){
                sum = 0;
                for(int j = prevLayerStart; j < prevLayerEnd; j++){
                    sum += values[j]*weights[weight];
                    weight++;
                }
                values[neuron] = function(sum+weights[weight]);
                weight++;
                neuron++;
            }
            prevLayerStart += layers.get(layer);
            prevLayerEnd += layers.get(layer);
        }
        double[] result = new double[9];
        for (int i = 0; i < 9; i++) {
            result[i] = values[numberOfNeurons-(9-i)];
        }
        return result;
    }

    public int giveNextMove(Board board, int player){
        this.board = board;
        if(player == -1) {
            this.board.flip();
        }
    }

}
