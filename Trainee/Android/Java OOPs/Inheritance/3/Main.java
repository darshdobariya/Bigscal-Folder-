public class Main extends Calculation{
    public static void main(String args[]){
        Calculation c = new Calculation();
        c.Rectangle(3.0, 5.0);
        c.Circle(2.0);
        c.Triangle(4.0, 9.0);

        c.Rectangle(7.0, 7.0);
        c.Circle(0.04);
    }
}