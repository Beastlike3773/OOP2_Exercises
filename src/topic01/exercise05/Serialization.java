package topic01.exercise05;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialization {

    private static List<Product> productList;
    private static String filename = ".\\src\\topic01\\exercise05\\ProductData.data";

    public static void main(String[] args) throws IOException {
        productList = new ArrayList<>();

        // Create products
        for(int i = 1; i <= 10; i++){
            productList.add(new Product("Product" + i, i, i * 3));
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            for(Product p : productList){
                oos.writeObject(p);
            }
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            boolean hasObject = true;
            while(hasObject){
                Product p = (Product) ois.readObject();
                System.out.println(p);
            }
        } catch(ClassNotFoundException e){
            System.err.println("unexpected object type");
        } catch(EOFException e){
            System.out.println("All object have been read");
        }
    }
}
