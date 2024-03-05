public class BankB extends Bank{
    public static void main(String args[]){
        Bank bank = new BankB();
        bank.deposite(120);
        bank.getBalance();

        bank.deposite(500);
        bank.getBalance();
    }

    public void getBalance(){
        System.out.println("BankB has " + bankB + "$");
    }

    public void deposite(int balance){
        bankB = bankB + balance;
        System.out.println("Deposite in BankB " + balance + "$");
    }
}