public class IntPotok implements Runnable{
    @Override
    public void run() {

        for(int i=0; i<=100; i+=10){
            System.out.println(i + " Runnable");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}