package day7;

import org.w3c.dom.css.Counter;

public class main {
    public static void main(String[] args) {

        counter c = new counter();

//        Runnable boy = () -> {
//            for(int i = 0; i < 100; i++){
//                System.out.println("Boy");
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        };

//        Runnable girl = () -> {
//            for(int i = 0; i < 100; i++){
//                System.out.println("Girl");
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        };

        //---the counter of the message
        Runnable boy = () -> {
            for(int i = 0; i < 1000; i++){
               c.increment();
            }
        };

        Runnable girl = () -> {
            for(int i = 0; i < 1000; i++){
                c.increment();
            }
        };


        Thread t1 = new Thread(boy);
        Thread t2 = new Thread(girl);
        t1.start();
        t2.start();

        // -- value of counter
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("The current value of the counter is: " + c.counter);
    }
}
