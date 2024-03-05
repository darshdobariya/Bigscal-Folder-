public class Thread2 extends Thread {
    public void run(){
       try{
            Thread.sleep(1000);
            System.out.println("============= Its Thread 2 ============= ");
       }catch (Exception e){
            System.out.println(e);
       }
    }
}
