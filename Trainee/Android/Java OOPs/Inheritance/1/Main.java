public class Main extends Deer{
    public static void main(String args[]){
        
        Deer d = new Deer();
        d.BasicInfo("Vegetarian", 4, 2, 1, "Deer");
        d.walk();
        d.eat("Grass");

        System.out.println();

        Lion l = new Lion();
        l.roar();
        l.walk();
        l.eat("Animal");
        l.BasicInfo("Non Vegitarian", 4, 2, 1, "Lion");

    }
}
