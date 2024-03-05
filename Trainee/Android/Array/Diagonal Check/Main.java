public class Main{
    public static void main(String args[]){
        int matrix[][] = {{10, 0, 10, 10}, {10, 10, 10, 10}, {10, 0, 10, 10}, {10, 10, 10, 10}};
        boolean status = true;
        int point = 0;

        for(int i = 1; i <= 4; i++){
            for(int j = 1; j <= 4; j++){
                if(i == 1 && j ==1){
                    point = matrix[i-1][j-1];
                    continue;
                }

                if(j == 1 || j == 4 || i == j){
                    if(point != matrix[i-1][j-1]){
                        status = false;
                    }
                }
            }
            System.out.println();
        }

        if(status == true){
            System.out.println("This is diagonal Matrix");
        }else System.out.println("This is not diagonal Matrix");
    }
}