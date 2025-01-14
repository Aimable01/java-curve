package day7;

public class counter{
    int counter;

    counter(){}

    public synchronized void increment(){
        counter++;
    }
}
