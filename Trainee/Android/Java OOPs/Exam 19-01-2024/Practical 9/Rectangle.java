public class Rectangle implements Shape{
    protected int width;
    protected int height;

    Rectangle(int width, int height){
        this.width = width;
        this.height = height;
    }

    public double getArea(){
        return width*height;
    }

    public double getParameter(){
        return 2*(height+width);
    }
}