public class Calculation {
    public void Text(String name){
        System.out.print("Area of " + name + " is: ");
    }

    public void Rectangle(Double height, Double width){
        Text("Rectangle");
        System.out.println(height*width);
    }

    public void Circle(Double radius){
        Text("Circle");
        System.out.println(radius*3.14*radius);
    }

    public void Triangle(Double height, Double width){
        Text("Triangle");
        System.out.println(0.5*height*width);
    }
}
