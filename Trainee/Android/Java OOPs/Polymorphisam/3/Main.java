public class Main extends Child{
    public static void main(String args[]){
        Child c = new Child();

        c.area();
        c.area(2);

        c.area(2, 2);
        c.area(2.0, 2);
    }
}