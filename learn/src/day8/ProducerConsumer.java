package day8;

class Counter{
    int counter;
    boolean valueSet = false;

    public synchronized void put(int num){
        while(valueSet){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Put : " + num);
        counter = num;
        valueSet = true;
        notify();
    }

    public synchronized void get(){
        while(!valueSet){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Get : " + counter);
        valueSet = false;
        notify();
    }
}

class Producer implements Runnable{

    Counter counter;

    Producer(Counter counter){
        this.counter = counter;
        Thread t = new Thread(this, "Producer");
        t.start();
    }

    @Override
    public void run() {

        int i = 0;
        while(true){
            counter.put(i++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class Consumer implements Runnable{

    Counter counter;
    Consumer(Counter counter){
        this.counter = counter;
        Thread t = new Thread(this, "Consumer");
        t.start();
    }

    @Override
    public void run() {
        while(true){
            counter.get();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        Counter counter = new Counter();
        new Producer(counter);
        new Consumer(counter);
    }
}
