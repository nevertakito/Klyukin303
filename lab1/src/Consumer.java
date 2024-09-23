public class Consumer extends Thread{
    Main main;
    public Consumer (Main main){
        this.main = main;
    }
    public void run(){
        for(int i=0; i<20; i++) {
            try {
                main.takeBread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}