public class Demo{
    private String name;
    private String mail;
    private int age;
    private int phone;

    public Demo(String name, String mail, int age, int phone){
        this.name = name;
        this.mail = mail;
        this.age = age;
        this.phone = phone;
    }
   
    public void getInfo(){
        System.out.println("Name : " + this.name);
        System.out.println("Mail : " + this.mail);
        System.out.println("Age : " + this.age);
        System.out.println("Phone : " + this.phone);
    }

    public String getName(){
        return name;
    }

    public String getMail(){
        return mail;
    }

    public int getAge(){
        return age;
    }

    public int getPhone(){
        return phone;
    }
}