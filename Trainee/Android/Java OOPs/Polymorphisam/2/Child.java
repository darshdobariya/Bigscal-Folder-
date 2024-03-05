public class Child {
     public void areas(int radius){
        areas();
        System.out.println(3.14 * radius);
    }

    public void areas(){
        System.out.print("Area: ");
    }

    public void areas(int width, int height){
        areas();
        System.out.println(width*height*2);
    }

    public void areas(double width, double height){
        areas();
        System.out.println(width*height*0.5);
    }
}
