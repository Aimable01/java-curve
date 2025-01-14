package day7;

public class Banking {
    public static void main(String[] args) {
        Acc account = new Acc();

        Runnable client1 = () -> account.deposit(1000);
        Runnable client2 = () -> account.withdraw(500);
        Runnable client3 = () -> account.deposit(1000);

        Thread t1 = new Thread(client1);
        Thread t2 = new Thread(client2);
        Thread t3 = new Thread(client3);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("The final balance is: "+ account.balance);
    }
}
