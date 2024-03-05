public class Triangle extends Area{
    public void CalculateArea(Double height, Double width){
        Area a = new Area();
        a.area(0.5*height*width, "Triangle");
    }
}
