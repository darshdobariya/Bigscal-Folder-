import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static String filePath = "E:\\BigScale\\Trainee\\Android\\File Handaling\\File Handling TXT\\demoText.txt";
    public static void main(String[] args) {

//         Create a File in Device named demoText.
//        try {
//            File Obj = new File(filePath);
//            if (Obj.createNewFile()) {
//                System.out.println("File created: " + Obj.getName());
//            }
//            else {
//                System.out.println("File already exists.");
//            }
//        }
//        catch (IOException e) {
//            System.out.println("An error has occurred.");
//            e.printStackTrace();
//        }

//        Write Something in Text File
//        try{
//            FileWriter fileWriter = new FileWriter(filePath);
//            fileWriter.write("Hello, Welcome to a demo session in Maruti Diam.\n" +
//                    "\n" +
//                    "========= Fun for Tonight =========\n" +
//                    "\n" +
//                    "time : 8:00 AM\n" +
//                    "place: Surat\n" +
//                    "\n" +
//                    "Hope you will enjoy this night with us. It's Me.");
//            fileWriter.close();
//            System.out.println("File written Successfully");
//        }catch (IOException e){
//            System.out.println("File not found");
//            e.printStackTrace();
//        }

//         Read data from file
//        try{
//            File file = new File(filePath);
//            Scanner read = new Scanner(file);
//
//            while (read.hasNextLine()){
//                String data = read.nextLine();
//                System.out.println(data);
//            }
//        }catch(FileNotFoundException e){
//            System.out.println("File not found");
//            e.printStackTrace();
//        }

        //Delete File
//        File file = new File(filePath);
//        if(file.delete()){
//            System.out.println("File deleted done ...");
//        } else System.out.println("File deleted Failed ... Try Again ");


        // using we are here LinkList, List, Vector, PriorityQueue, Deque, Set, HashSet, LinkedHashSet, ShortedSet, TreeSet and Queue.
        // Like List<String> list = new List<String>();
//        Stack<String> stack = new Stack<String>();
//        stack.push("Ayush");
//        stack.push("Garvit");
//        stack.push("Amit");
//        stack.push("Ashish");
//        stack.push("Garima");
//        stack.remove(2);
//
//        Collections.sort(stack);
//        Collections.reverse(stack);
//        Collections.shuffle(stack);
//
//        Iterator<String> itr=stack.iterator();
//        while(itr.hasNext()){
//            System.out.println(itr.next());
//        }


        Demo object = new Demo(1, "geeksforgeeks");

        // Serialization
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(object);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        Demo object1 = null;

        // Deserialization
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            object1 = (Demo)in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            System.out.println("a = " + object1.a);
            System.out.println("b = " + object1.b);
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

    }
}