public class Demo {
    private String vType;
    private String vModel;
    private int vYear;
    private String vFuel;

    public Demo(String vType, String vModel, int vYear, String vFuel){
        this.vType = vType;
        this.vModel = vModel;
        this.vYear = vYear;
        this.vFuel = vFuel;
    }

    public String getVType(){
        return this.vType;
    }

    public String getVModel(){
        return this.vModel;
    }

    public int getVYear(){
        return this.vYear;
    }

    public String getVFuel(){
        return this.vFuel;
    }
}
