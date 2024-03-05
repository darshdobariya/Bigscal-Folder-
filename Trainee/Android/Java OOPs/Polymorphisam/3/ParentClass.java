public class ParentClass {
    public void area(int width, int height){
        System.out.println("Area: " + width*height);
    }

    public void area(double width, int height){
        System.out.println("Area: " + height*width*0.5);
    }
}
