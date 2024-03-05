public class BankC extends Bank{
    public static void main(String args[]){
        Bank bank = new BankC();
        bank.deposite(120);
        bank.getBalance();

        bank.deposite(500);
        bank.getBalance();
    }

    public void getBalance(){
        System.out.println("BankC has " + bankC + "$");
    }

    public void deposite(int balance){
        bankC = bankC + balance;
        System.out.println("Deposite in BankC " + balance + "$");
    }
}