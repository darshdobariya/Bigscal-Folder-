public class Demo {
    private String lptName;
    private String lptVersion;
    private int lptRam;
    private int lptStorage;
    private int lptBattery;
    private String price;

    public void setLptName(String lptName){
        this.lptName = lptName;
    }

    public void setLptVersion(String lptVersion){
        this.lptVersion = lptVersion;
    }
    
    public void setLptRam(int lptRam){
        this.lptRam = lptRam;
    }

    public void setLptStorage(int lptStorage){
        this.lptStorage = lptStorage;
    }
    
    public void setLptBattery(int lptBattery){
        this.lptBattery = lptBattery;
    }

    public void setLptPrice(String price){
        this.price = price;
    }

    public String getLptName(){
        return lptName;
    }

    public String getLptVersion(){
        return lptVersion;
    }

    public int getLptRam(){
        return lptRam;
    }

    public int getLptStorage(){
        return lptStorage;
    }

    public int getLptBattery(){
        return lptBattery;
    }

    public String getLptPrice(){
        return price;
    }
}
