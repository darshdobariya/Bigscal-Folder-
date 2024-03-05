public class Square implements Shape{
    protected int height;

    Square(int height){
        this.height = height;
    }

    public double getArea(){
        return height*height;
    }

    public double getParameter(){
        return 4*height;
    }
}