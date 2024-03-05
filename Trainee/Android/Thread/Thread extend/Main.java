public class Main{
    public static void main(String[] args){
        System.out.println("Hello");

        Thread1 t1 = new Thread1("Darsh");
        t1.start();

        Thread2 t2 = new Thread2("Arav");
        t2.start();

        Thread1 t3 = new Thread1("Yash");
        Thread2 t4 = new Thread2("Ayush");
        Thread2 t5 = new Thread2("Nweyiii");

        t3.setPriority(Thread.MIN_PRIORITY);
        t4.setPriority(Thread.MAX_PRIORITY);
        t5.setPriority(6);

        t3.start();
            
        try{
            t3.join();
        }catch(Exception e){
            System.out.println("==" + e);
        }finally{
            System.out.println("We Did It.");
        }

        t5.setDaemon(true);
        t4.start();
        t5.start();
    }
}