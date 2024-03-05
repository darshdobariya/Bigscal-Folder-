public class Thread2 implements Runnable{
    public void run(){
        int i = 0;
        while(i<500){
            System.out.println("It's thread 2 running...");
            i++;
        }
    }
}