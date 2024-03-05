public class Vehical {

    double dYear, dFuel;

    public void passData(int year, String type, String model, String fuel){
        efficiency(2024-year, type, fuel);
        dtnTravelled(type, 2024-year);
        maxSpeed(type);
    }

    public void efficiency(int year, String type, String fuel){
        if(type == "Car" && fuel == "Petrol"){
            dYear = year*0.01;
            dFuel = 0.01;
        }else if(type == "Car" && fuel == "diesel"){
            dYear = year*0.017;
            dFuel = 0.013;
        }else if(type == "Motercycle" && fuel == "Petrol"){
            dYear = year*0.0095;
            dFuel = 0.01;
        }else if(type == "Motercycle" && fuel == "diesel"){
            dYear = year*0.0105;
            dFuel = 0.013;
        }else if(type == "Truck" && fuel == "Petrol"){
            dYear = year*0.012;
            dFuel = 0.01;
        }else if(type == "Truck" && fuel == "diesel"){
            dYear = year*0.015;
            dFuel = 0.013;
        }

        System.out.println("Your " + type + " efficiency is still " + (100-(100*dYear*dFuel)) + "%");
    }

    public void dtnTravelled(String type, int year){
        if(type == "Car"){
            System.out.println("Estimated Distance Travell by a " + type + " is " + year*2000);
        }else if(type == "Truck"){
            System.out.println("Estimated Distance Travell by a " + type + " is " + year*15000);
        }else{
            System.out.println("Estimated Distance Travell by a " + type + " is " + year*7000);
        }
    }

    public void maxSpeed(String type){
        if(type == "Car"){
            System.out.println("Max Speed of " + type + " is 240");
        }else  if(type == "Truck"){
            System.out.println("Max Speed of " + type + " is 120");
        }else {
            System.out.println("Max Speed of " + type + " is 100");
        }
    }
}
