abstract class Bank{
    protected int bankA = 110;
    protected int bankB = 200;
    protected int bankC = 20;
    
    abstract void getBalance();
    abstract void deposite(int balance);
}