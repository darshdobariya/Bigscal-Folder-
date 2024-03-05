public class Demo{
    private String name;
    private int math;
    private int chemestry;
    private int physics;
    private int computer;
    private double result;

    public Demo(String name, int math){
        this.name = name;
        this.math = math;
    }

    public void nnn(){
        System.out.println("Hello");
    }

    public Demo(String name, int math, int chemestry, int physics, int computer){
        this(name, math);
        this.nnn();
        this.chemestry = chemestry;
        this.physics = physics;
        this.computer = computer;
    }

    public void getInfo(){
        System.out.println("Name: " + this.name);
        System.out.println("Mathematics: " + this.math);
        System.out.println("Chemistry: " + this.chemestry);
        System.out.println("Physics: " + this.physics);
        System.out.println("Computer: " + this.computer);

        this.result = ((this.math+this.chemestry+this.physics+this.computer)/4);
        System.out.println("Result: " + this.result + "\n");    
    }
}