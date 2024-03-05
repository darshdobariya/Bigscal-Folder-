public class Thread2 extends Thread{
    public void run(){
        int i = 0;
        while(i<50){
            System.out.println("It's thread 2 running..." + Thread.currentThread().getName() + "==="+ Thread1.currentThread().isDaemon());
            i++;
        }
    }

    public Thread2(String name){
        System.out.println("Thread2 name is : " + name);
    }
}