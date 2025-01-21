package day8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BoyA implements Runnable {
    @Override
    public void run() {
        System.out.println("Boy thread has started");
        for (int i = 1; i <= 50; i++) {
            System.out.println("Boy: "+i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Boy thread has completed");
    }
}

class GirlA implements Runnable {
    @Override
    public void run() {
        System.out.println("Girl thread has started");
        for (int i = 1; i <= 50; i++) {
            System.out.println("Girl: "+i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Girl thread has completed");
    }
}

class Teacher implements Runnable {

    @Override
    public void run() {
        System.out.println("Teacher thread has started");
        for (int i = 1; i <= 50; i++) {
            System.out.println("Teacher: "+i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Teacher thread has completed");
    }
}

class Task implements Runnable {
    int num;

    public Task(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("Task number: "+num+"has started");
        for(int i = num; i < num*5; i++) {
            System.out.print("  "+num);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Task number: "+num+"has completed");
    }
}

public class ExecutorFrameWorkDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new BoyA());
        executor.execute(new Thread(new GirlA()));
        executor.shutdown();
        for(int i = 0; i < 5; i++){
            System.out.println("Main values: "+i);
        }

        System.out.println("----------------FIXED THREAD EXECUTOR-------------------------");
        ExecutorService executor2 = Executors.newFixedThreadPool(2);
        executor2.execute(new GirlA());
        executor2.execute(new BoyA());
        executor2.execute(new Teacher());
        executor2.shutdown();

        ExecutorService executor3 = Executors.newFixedThreadPool(5);
        for(int i = 1; i <= 10; i++) {
            executor3.execute(new Task(i));
        }
        executor.shutdown();
    }
}
