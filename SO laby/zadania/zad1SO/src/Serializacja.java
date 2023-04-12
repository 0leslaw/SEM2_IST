import java.io.*;
import java.util.ArrayList;

public class Serializacja {
    public static <T> ArrayList<T> odczytDowolnejArrayListy(String nazwapliku) throws IOException, ClassNotFoundException {
        ObjectInputStream pl2=null;
        ArrayList<T> lista_T = null;
        try{
            pl2=new ObjectInputStream(new FileInputStream(nazwapliku));
            lista_T=(ArrayList<T>) pl2.readObject();

        } catch (EOFException ex) {
//            System.out.println("Koniec pliku");
        }

        finally{
            if(pl2!=null)
                pl2.close();
        }
        return lista_T;
    }

    public static <T> void zapisDowolnejArrayListy(ArrayList<T> lista_T, String nazwapliku)throws IOException {
        ObjectOutputStream pl=null;
        try{
            pl=new ObjectOutputStream(new FileOutputStream(nazwapliku));
            pl.writeObject(lista_T);
            pl.flush();
        }
        finally{
            if(pl!=null)
                pl.close();
        }
    }
}
