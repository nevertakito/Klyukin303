import javax.swing.*;

public class Main {
    public static volatile boolean flag = true;
    public static void main (String [] args) throws InterruptedException{
        Chronometr timer=new Chronometr();
        Messenger t1=new Messenger(2, timer);
        Messenger t2=new Messenger(5, timer);
        t2.start();
        t1.start();
        timer.start();
    }
}
class Chronometr extends Thread {
    public int time=0;

    public void run() {
        while (true){
            countTime();
        }
    }
    public void countTime () {
        synchronized(this) {
            time+=1;
            System.out.println(time);
            Main.flag = false;
            this.notify();
        }
        try{Thread.sleep(1000);}
        catch (InterruptedException ignored) {};
    }
}
class Messenger extends Thread{
    private final int time;
    public final Chronometr ch;
    Messenger(int time, Chronometr ch) {
        this.time=time;
        this.ch=ch;
    }
    public void waitForTime() throws InterruptedException {
        while(true){
            synchronized (ch) {
                while (Main.flag) {
                    ch.wait();
                }
                if(ch.time%this.time==0)
                {
                    System.out.println("Поток с интервалом " + this.time);
                }
                Main.flag=true;
            }
        }
    }
    public void run()  {
        try {
            waitForTime();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}