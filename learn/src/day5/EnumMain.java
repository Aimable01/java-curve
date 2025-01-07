package day5;

public class EnumMain {
    enum Color{
        RED, GREEN, BLUE
    }

    enum Laptop{
        HP(300),
        THINKPAD(200),
        MACBOOK;

        private int price;

        private Laptop(int price){
            this.price = price;
        }
        private Laptop(){}
        public int getPrice(){
            return price;
        }
        public void setPrice(int price){
            this.price = price;
        }
    }

    public static void main(String[] args) {
        Gender gender = Gender.MALE;
        System.out.println("The gender is: " + gender);

        Color c = Color.GREEN;
        System.out.println("The color is: " + c);

        if (c == Color.RED) {
            System.out.println("The color is RED");
        }else if (c == Color.GREEN) {
            System.out.println("The color is GREEN");
        }else if (c == Color.BLUE) {
            System.out.println("The color is BLUE");
        }else {
            System.out.println("The color is UNKNOWN");
        }

        Laptop p = Laptop.MACBOOK;
        p.setPrice(1000);
        System.out.println("Laptop price: "+p.name()+": "+p.getPrice());

        for(Laptop l : Laptop.values()){
            System.out.println("Laptop price: "+l.name()+": "+l.getPrice());
        }
    }
}
