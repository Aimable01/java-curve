package misc;

public class PrintAny<T> {

    T word;

    public PrintAny(T word){
        this.word = word;
    }

    public void display(){
        System.out.println("The word is: " + word);
    }
}
