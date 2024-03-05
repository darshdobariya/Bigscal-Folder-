public class Circle implements Shape{
    protected int radius;

    Circle(int radius){
        this.radius = radius;
    }

    public double getArea(){
        return radius*radius*3.14;
    }

    public double getParameter(){
        return 2*3.14*radius;
    }
}