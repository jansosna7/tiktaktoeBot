import java.util.Arrays;

public class Board {

    /*
    0 empty
    1 'x'
    -1 'o'
     */
    private int[] board;
    /*
    0 | 1 | 2
    _________
    3 | 4 | 5
    ---------
    6 | 7 | 8
     */

    public Board() {
        board = new int[9];
        for(int i = 0; i <9; i++){
            board[i] = 0;
        }
    }

    public boolean draw(){
        for (int i = 0; i < 9; i++) {
            if(board[i] == 0){
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty(int tile){
        return (board[tile] == 0);
    }

    public void place (int tile, int player){
        board[tile] = player;
    }

    public int getTile(int tile){
        return board[tile];
    }

    public boolean checkWin(int player){
        for(int i = 0; i<3; i++){
            //rows
            if(board[3*i] == board[3*i+1] && board[3*i+1] == board[3*i+2] && board[3*i] == player){
                return true;
            }
            //columns
            if(board[i] == board[i+3] && board[i+3] == board[i+6] && board[i] == player){
                return true;
            }
        }
        //cross
        if(board[4] == player){
            if(board[0] == board[4] && board[4] == board[8]){
                return true;
            }
            if(board[2] == board[4] && board[4] == board[6]){
                return true;
            }
        }
        return false;
    }

    public String getChar(int tile){
        if(board[tile] == -1){
            return "o";
        }
        if(board[tile] == 1){
            return "x";
        }
        return " ";
    }

    public void flip(){
        for (int i = 0; i < 9; i++) {
            board[i] = board[i]*-1;
        }
    }

    @Override
    public String toString() {
        return  " --- \n" +
                "|"+getChar(0)+getChar(1)+getChar(2)+"|\n" +
                "|"+getChar(3)+getChar(4)+getChar(5)+"|\n" +
                "|"+getChar(6)+getChar(7)+getChar(8)+"|\n" +
                " --- \n";
    }
}
