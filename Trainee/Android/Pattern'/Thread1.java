public class Thread1 extends Thread {
    public void run(){
        int i = 1;

        while(i<3500){
            try{
                System.out.println("Its Thread 1 with count " + i);
                i++;
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}
