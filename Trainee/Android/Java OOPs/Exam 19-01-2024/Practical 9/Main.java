public class Main{
    public static void main(String args[]){
        Shape circle = new Circle(10);
        Shape rectangle = new Rectangle(10, 20);
        Shape square = new Square(10);

        System.out.println("================  Circle ===============");
        System.out.println("Area : " + circle.getArea());
        System.out.println("Parimeter : " + circle.getParameter());

        System.out.println("================  Rectangle ===============");
        System.out.println("Area : " + rectangle.getArea());
        System.out.println("Parimeter : " + rectangle.getParameter());

        System.out.println("================  Square ===============");
        System.out.println("Area : " + square.getArea());
        System.out.println("Parimeter : " + square.getParameter());      
    }
}