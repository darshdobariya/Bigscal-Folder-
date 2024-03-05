public class Circle extends Area{
    public void CalculateArea(Double radius){
        Area a = new Area();
        a.area(3.14*radius*radius, "Circle");
    }
}
