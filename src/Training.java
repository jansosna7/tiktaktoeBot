import java.util.ArrayList;
import java.util.List;

public class Training {

    private Bot best;
    private Bot[] bots;
    
    
    private void game(int a, int b){
        Board board = new Board();
        
    }

    
    public void Run(int maxLayer, int population, int generations){
        bots = new Bot[population];
        List<Integer> layers = new ArrayList<Integer>(); //generate more sofiticated layers
        for (int l = 0; l < maxLayer; l++) {
            //create bots
            for (int b = 0; b < population; b++) {
                bots[b] = new Bot((l+1)*90,layers,(l+1)*9); //be careful n
            }
            //let them play (2 points for win, 1 for draw, 0 for lose)
            for (int i = 0; i < population; i++) {
                for (int j = 0; j < population; j++) {
                    if(i != j){
                        game(i,j);
                    }
                }
            }
            // TODO: 07.11.2022 some basic startegics 
            //evolve
            
            
            layers.add(9);
        }
    }
}
