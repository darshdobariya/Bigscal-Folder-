public class Rectangle{
    private int length;
    private int width;

    public int area(int length, int width){
        return length * width;
    }

    public int parameter(int length, int width){
        return length + width;
    }

    Rectangle(int length, int width){
        this.length = length;
        this.width = width;

        System.out.println("Area : " + area(this.length, this.width) + " \n" + "Param : " + parameter(this.length, this.width));
    }
}