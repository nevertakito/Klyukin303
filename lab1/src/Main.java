public class Main {
    public static volatile boolean flag = false;
    private int bread = 30;
    public synchronized void addBread() throws InterruptedException {
        while (bread>40){
            wait();
        }
        bread+=10;
        System.out.println("Хлеб добавлен производителем " + bread );
        notify();
    }
    public synchronized void takeBread() throws InterruptedException {
        while (bread<10){
            wait();
        }
        bread-=10;
        System.out.println("Хлеб куплен потребителем "+ bread);
        notify();
    }
    public static void main(String[] args) throws InterruptedException {
        Potok p = new Potok();
        Thread IntP = new Thread(new IntPotok());
        p.setPriority(Thread.MAX_PRIORITY);
        p.start();
        p.join();
        IntP.start();
        IntP.join();

        System.out.println();
        System.out.println();


        Main main = new Main();
        Producer prod = new Producer(main);
        Consumer cus = new Consumer(main);
        prod.start();
        cus.start();
    }
}