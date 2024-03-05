public class Main extends Child{
    public static void main(String args[]){
        Child c = new Child();

        c.areas(2);
        c.areas(5, 5);
        c.areas(5.0, 5.0);
    }
}