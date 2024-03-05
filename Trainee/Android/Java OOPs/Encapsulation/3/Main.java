public class Main{
    public static void main(String args[]){
        Demo d = new Demo();
        d.setLptName("Lenovo");
        d.setLptVersion("Slim 3");
        d.setLptRam(8);
        d.setLptStorage(1000);
        d.setLptBattery(8000);
        d.setLptPrice("50000");

        System.out.print("You have a " + d.getLptName() + " " + d.getLptVersion() + " Laptop with " + d.getLptRam() + " GB RAM with " );
        System.out.println(d.getLptBattery() + " Mah Battery, " + d.getLptStorage() + " GB Storage. Just rate in " + d.getLptPrice() + " Only." );
    }
}