public class BankA extends Bank{
    public static void main(String args[]){
        Bank bank = new BankA();
        bank.deposite(120);
        bank.getBalance();

        bank.deposite(500);
        bank.getBalance();
    }

    public void getBalance(){
        System.out.println("BankA has " + bankA + "$");
    }

    public void deposite(int balance){
        bankA = bankA + balance;
        System.out.println("Deposite in BankA " + balance + "$");
    }
}