import java.util.Scanner;

public class HumanVsBot {
    private Bot bot;
    private Board board = new Board();

    public HumanVsBot(Bot b){
        bot = b;
    }

    public void play(int humanPlayer) {
        int player = 1;
        int move;
        String p = "x";
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream

        for (int i = 0; i < 9; i++) {
            if (player == humanPlayer) {
                System.out.println("to exit type -1,else type which tile to move\nnow " + p + " is moving");
                move = sc.nextInt();
            } else {
                move = bot.giveNextMove(board, player);
            }
            if (move == -1) { //draw
                return;
            }
            if (move == -2 || move == -3) {//
                return;
            }
            board.place(move, player);
            System.out.println(board);
            if (board.checkWin(player)) {
                System.out.println(p = " won");
                return;
            }
            player *= -1;
            if(player == 1){
                p = "x";
            }
            else{
                p = "o";
            }
        }
    }
}
