public class Truck extends Vehical{
    public void truckInputData(String type, String model, int year, String fuel){
        Demo d = new Demo(type, model, year, fuel);

        System.out.println("\n ----------------------" + model + "---------------------------- \n");

        passData(d.getVYear(), d.getVType(), d.getVModel(), d.getVFuel());
    }
}
