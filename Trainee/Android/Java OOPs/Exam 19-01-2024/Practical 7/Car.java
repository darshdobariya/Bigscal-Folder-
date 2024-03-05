public class Car implements Vehical{
    
    protected int speed = 90;

    public void speedUp(){
        System.out.println("Car Speed increased up to " + ++speed);
    }
}