package day;

import day1.Animal;

public class PrintAny<T extends Animal> {

    T word;

    public PrintAny(T word){
        this.word = word;
    }

    public void display(){
//        System.out.println(word.sound());
    }
}
