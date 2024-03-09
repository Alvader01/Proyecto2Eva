package Model;

import java.io.*;

public class Serializator {
    /**
     *
     * @param obj
     * @param filename
     * @return
     * @param <T>
     */
    public static<T> boolean serializable(T obj,  String filename){
        boolean result= false;
        try(ObjectOutputStream oos =new ObjectOutputStream(
                new FileOutputStream(filename)
        )) {
            oos.writeObject(obj);
            result = true;
        }catch (IOException e) {
            //throw new RuntimeException(e);
        }
        return result;
    }
    public static<T> T desearize(String filename){
        T result=null;
        try(ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename)
        )){
            result=(T)ois.readObject();
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
}
