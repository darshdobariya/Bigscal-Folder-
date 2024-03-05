public class Thread1 extends Thread{
    public void run(){
        int i = 0;
        while(i<50){
            System.out.println("It's thread 1 running..." + Thread1.currentThread().getName() + "==="+ Thread1.currentThread().isDaemon());
            i++;

            try{
                Thread.sleep(100);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }

    public Thread1(String name){
        System.out.println("Thread1 name is : " + name);
    }
}