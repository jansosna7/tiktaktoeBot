public class Main {
    public static void main(String[] args) {
        Board b1 = new Board();
        b1.place(0,1);
        b1.place(4,1);
        b1.place(8,1);
        System.out.println(b1);
        if(b1.checkWin(1)){
            System.out.println("1 won");
        }
    }
}
