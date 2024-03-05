public class Main3 {
    public static void main(String srgs[]){
        String[] array = {"abc", "ayz", "abm", "mvl"};
        String input = "ab";
        int count = 0;

        for(int i = 0; i<4; i++){
            count = 0;
            for(int j = 0; j<=input.length()-1; j++){
                if(input.charAt(j) == array[i].charAt(j)){
                    count += 1;
                }
            }

            if(count == 2){
                System.out.println(array[i]);
                count = 0;
            }
        }
    }
}
