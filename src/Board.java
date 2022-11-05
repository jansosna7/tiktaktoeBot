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

    public boolean isEmpty(int tile){
        return (board[tile] == 0);
    }

    public void place (int tile, int player){
        board[tile] = player;
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
}
