public class Main2 {
    public static void main(String args[]){
        int number = 28;
        int sum=0;

        for(int i = 1; i<number; i++){
            if(number%i == 0){
                sum += i;
            }
        }

        if(sum == number){
            System.out.println(number + " is a Perfact number." + sum);
        }else System.out.println(number + " is not a Perfact number." + sum);
    }
}
