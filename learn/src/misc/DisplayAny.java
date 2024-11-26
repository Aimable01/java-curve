package misc;

public class DisplayAny<T,K> {

    T param1;
    K param2;

    public DisplayAny(T param1, K param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    public T displayFirst(){
        return this.param1;
    }
    public K displaySecond(){
        return this.param2;
    }

    public static void main(String[] args) {
        DisplayAny<String, Integer> person = new DisplayAny<>("KWIZERA",21);
        System.out.println(person.displayFirst());
        System.out.println(person.displaySecond());
    }
}
