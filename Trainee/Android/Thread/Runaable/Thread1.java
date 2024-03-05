public class Thread1 implements Runnable{
    public void run(){
        int i = 0;
        while(i<500){
            System.out.println("It's thread 1 running...");
            i++;
        }
    }
}