package day1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        PrintAny<Dog> dg = new PrintAny<>(new Dog());
//        dg.display();

        List<Dog> dogs = new ArrayList<Dog>();
        dogs.add(new Dog());
        List<Cat> cats = new ArrayList<Cat>();
        cats.add(new Cat());

        displayAnimal(dogs);
        displayAnimal(cats);
    }
    public static void displayAnimal(List<? extends Animal> animals){
        for(Animal animal : animals){
            System.out.println(animals);
        }
    }
}
