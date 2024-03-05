public class Main{
    public static void main(String args[]){
        /* create a new file in java */
        try{
            File file = new File("demo.txt");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}