public class HRManager extends Employee{
    public static void main(String args[]){
        work("none");
        addEmployee("Arav", "Python Dev.", "4569871236", 40000, 25, 2);
    }

    public static void work(String work){
        System.out.println("You are working as HR...");
    }
    
    public static void addEmployee(String name, String role, String mobile, int salary, int age, int expYear){
        getSalary(salary);
    }
}