public class Producer extends Thread{
    Main main;
    public Producer(Main main){
        this.main = main;
    }
    public void run(){
        for(int i =0; i<20; i++) {
            try {
                main.addBread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}