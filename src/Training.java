import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.sql.Types.TIME;


public class Training {

    private Bot best;
    private int worstId;
    private int bestId;
    private Bot[] bots;
    private int winScore = 2;
    private int population;
    private int n;
    private int numberOfNeurons;
    private List<Integer> layers;
    private Random rand = new Random(TIME);


    private void game(int a, int b){
        bots[a].games ++;
        bots[b].games ++;
        Board board = new Board();
        int player = 1;
        int move;
        for (int i = 0; i < 9; i++) {
            move = bots[a].giveNextMove(board,player);
            if(move == -1){ //draw
                bots[a].score ++;
                bots[b].score ++;
                return;
            }
            if(move == -2 || move == -3){//
                return;
            }
            board.place(move,player);
            if(board.checkWin(player)){
                if(player == 1){
                    bots[a].wins ++;
                    bots[a].score += winScore;
                }
                else{
                    bots[b].wins ++;
                    bots[b].score += winScore;
                }
            }
            player*=-1;
        }
    }

    private double[] merge(Bot a, Bot b, double diff){
        double [] wa = a.getWeights();
        double [] wb = b.getWeights();
        double [] result = new double[wa.length];
        int bound = 100;
        for (int i = 0; i < wa.length; i++) {
            result[i] = (wa[i] + wb[i]) / 2 + (rand.nextInt(2*bound)-bound)*diff;
        }
        return result;
    }

    private void avgBestAll(){
        for (int i = 0; i < population; i++) {
            bots[i] = new Bot(n,merge(best,bots[i],0.0001),layers,numberOfNeurons);
        }
        bots[bestId] = best;
        bots[worstId] = new Bot(n,layers,numberOfNeurons);
    }

    
    public void Run(int maxLayer, int population, int generations, int algorithm){
        this.population = population;
        bots = new Bot[population];
        List<Integer> layers = new ArrayList<Integer>(); //generate more sofiticated layers
        for (int l = 0; l < maxLayer; l++) {
            //create bots
            for (int b = 0; b < population; b++) {
                this.n = (l+1)*90;
                this.layers = layers;
                this.numberOfNeurons = (l+1)*9;
                bots[b] = new Bot((l+1)*90,layers,(l+1)*9); //be careful n
            }
            for(int g = 0; g < generations; g++) {
                //let them play
                for (int i = 0; i < population; i++) {
                    for (int j = 0; j < population; j++) {
                        if (i != j) {
                            game(i, j);
                        }
                    }
                }
                // TODO: 07.11.2022 some basic startegics
                //evolve
                best = bots[0];
                best.winRate = (double) best.wins / best.games;
                for (Bot bot : bots
                ) {
                    bot.winRate = (double) bot.wins / bot.games;
                    if (bot.score > best.score) {
                        best = bot;
                    }
                    if (bot.score == best.score) {
                        if (bot.winRate > best.winRate) {
                            best = bot;
                        }
                    }
                }
                worstId = 0;
                bestId = 0;
                for (int i = 0; i < population; i++) {
                    if(bots[i].winRate < bots[worstId].winRate){
                        worstId = i;
                    }
                    if(bots[i] == best){
                        bestId = i;
                    }
                }
                if(algorithm == 1) {
                    avgBestAll();
                }
            }
            layers.add(9);
        }
    }

    public void getBest(){
        System.out.println(best.games + " " + best.wins + " " + best.winRate + " " + best.score);
    }

}
