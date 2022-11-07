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
    public int wins = 0; //games won
    public int games = 0; //games played
    public double winRate = 0; //wins/games
    public int score = 0; //based on training

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
    public Bot(int n, double[] weights, List<Integer> layers, int numberOfNeurons){
        this.n = n;
        this.weights = weights;
        this.layers = layers;
        this.numberOfNeurons = numberOfNeurons;
        this.values = new double[numberOfNeurons];
    }

    private double function(double sum){
        return 1/(1+exp(-sum));
    }

    public void calculate(){
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

    }

    public int giveNextMove(Board board, int player){

        int countEmpty = 0;
        for (int i = 0; i < 9; i++) {
            if(board.isEmpty(i)){
                countEmpty++;
            }
        }
        if (countEmpty == 0){
            return -1; //no empty tiles - draw
        }

        this.board = board;
        if(player == -1) {
            this.board.flip();
        }
        calculate();
        double[] result = new double[9];
        for (int i = 0; i < 9; i++) {
            result[i] = values[numberOfNeurons-(9-i)];
        }
        double maxV;
        int maxId;
        for (int i = 0; i < 9; i++) {
            maxV = -2;
            maxId = -1;
            for (int j = 0; j < 9; j++) {
                if(result[j] > maxV){
                    maxV = result[i];
                    maxId = j;
                }
            }
            if(maxId != -1){
                if(this.board.isEmpty(maxId)){
                    return maxId;
                }
                else
                {
                    result[maxId] = -2;
                }
            }
            else{ //all results are -2
                return -2; //cant decide
            }
        }
        return -3; //didnt do enything
    }

}
