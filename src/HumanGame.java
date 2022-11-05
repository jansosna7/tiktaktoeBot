import java.util.Scanner;

public class HumanGame {
    private Board board;
    public HumanGame(){
        board = new Board();
    }

    /**
     * @return 0 when unfinished, 1 when 1 won and -1 when -1 won
     */
    public int play(){
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream

        int player = 1;
        String p = "x";

        int tile;

        while(!board.checkWin(1) || !board.checkWin(-1)){
            System.out.println("to exit type -1,else type which tile to move\nnow " + p + " is moving");
            tile = sc.nextInt();
            if(tile <= -1) {
                return 0;
            }
            if(tile <= 8 && board.isEmpty(tile)){
                board.place(tile,player);
                if(board.checkWin(player)){
                    System.out.println("congrats, you won");
                    return player;
                }
                player *= -1;
                if(player == 1){
                    p = "x";
                }
                else{
                    p = "o";
                }
            }
            else{
                System.out.println("tile taken, try again");
            }
        }
        return 0;
    }
}
