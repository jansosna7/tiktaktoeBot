import java.io.*;

public class Main {
    public static void main(String[] args) {
        /*Board b1 = new Board();
        b1.place(0,1);
        b1.place(4,1);
        b1.place(8,1);
        System.out.println(b1);
        if(b1.checkWin(1)){
            System.out.println("1 won");
        }
        HumanGame humanGame = new HumanGame();
        humanGame.play();

        Bot bot1 = new Bot(90, null,9);
*/
        Training training1 = new Training();
        training1.Run(1,100,100,1);
        Bot best = training1.getBest();
        HumanVsBot hvb = new HumanVsBot(best);
        hvb.play(1);
        hvb.play(-1);
        if(true){
            return;
        }
        try {

            FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            Bot bestRead = (Bot) oi.readObject();


            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
