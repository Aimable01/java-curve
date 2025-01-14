package day7;

public class Acc {
    int balance;

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        if(amount < balance) {
            balance -= amount;
        }else{
            System.out.println("Insufficient Balance");
        }
    }
}
