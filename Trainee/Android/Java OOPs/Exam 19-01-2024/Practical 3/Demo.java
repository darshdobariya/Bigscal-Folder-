public class Demo{
    private String cmpName;
    private String modalName;
    private int year;
    private int mileage;

    public Demo(String cmpName, String modalName, int year, int mileage){
        this.cmpName = cmpName;
        this.modalName = modalName;
        this.year = year;
        this.mileage = mileage;
    }

    public void setCmpName(String cmpName){
        this.cmpName = cmpName;
    }

    public String getCmpName(){
        return this.cmpName;
    }

    public void setModalName(String modalName){
        this.cmpName = modalName;
    }

    public String getModalName(){
        return this.modalName;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getYear(){
        return this.year;
    }

    public int getMileage(){
        return this.mileage;
    }
}