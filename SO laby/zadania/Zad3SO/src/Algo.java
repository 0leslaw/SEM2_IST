import java.util.ArrayList;
import java.util.Random;

public class Algo {
    public static void FIFO(ArrayList<Integer> pagelist, int RAM_length){
        int faults=0;
        boolean isfault = true;
        ArrayList<Integer> RAM = new ArrayList<>();
        for(Integer page:pagelist){
            isfault = true;
            for (int i=0;i<RAM.size();i++){
                if(page == RAM.get(i)){
                    isfault = false;
                }
            }
            if(isfault) {
                faults++;
                if(RAM.size()==RAM_length){
                    RAM.remove(0);
                    RAM.add(page);
                }else RAM.add(page);
            }
        }
        System.out.println("Liczba błędów FIFO: "+ faults);
    }
    public static void OPT(ArrayList<Integer> pagelist,int RAM_length){
        boolean isfault = true;
        int faults=0;
        ArrayList<Integer> RAM = new ArrayList<>();
        for(int j=0;j< pagelist.size();j++){
            isfault = true;
            for (int i=0;i<RAM.size();i++){
                if(pagelist.get(j) == RAM.get(i)){
                    isfault = false;
                }
            }
            if(isfault) {
                faults++;
                if(RAM.size()==RAM_length && j != pagelist.size()-1){
                    ArrayList<Integer> temp = new ArrayList<>();
                    for(Integer ram:RAM)
                        temp.add(ram);
                    for (int k = j+1;k<pagelist.size();k++){
                        for(int z = 0;z< temp.size();z++)
                            if(temp.get(z) == pagelist.get(k)) {
                                temp.remove(z);
                                break;
                            }
                        if(temp.size() == 1)
                            break;
                    }
                    RAM.remove(temp.get(0));
                    RAM.add(pagelist.get(j));
                }else RAM.add(pagelist.get(j));
            }
        }
        System.out.println("Liczba błędów OPT: "+ faults);
    }
    public static void LRU(ArrayList<Integer> pagelist,int RAM_length){
        boolean isfault = true;
        int faults=0;
        ArrayList<Integer> RAM = new ArrayList<>();
        for(int j=0;j< pagelist.size();j++){
            isfault = true;
            for (int i=0;i<RAM.size();i++){
                if(pagelist.get(j) == RAM.get(i)){
                    isfault = false;
                }
            }
            if(isfault) {
                faults++;
                if(RAM.size()==RAM_length && j != pagelist.size()-1){
                    ArrayList<Integer> temp = new ArrayList<>();
                    for(Integer ram:RAM)
                        temp.add(ram);
                    for (int k = j-1;k>-1;k--){
                        for(int z = 0;z< temp.size();z++)
                            if(temp.get(z) == pagelist.get(k)) {
                                temp.remove(z);
                                break;
                            }
                        if(temp.size() == 1)
                            break;
                    }
                    RAM.set(RAM.lastIndexOf(temp.get(0)),pagelist.get(j));
                }else RAM.add(pagelist.get(j));
            }
        }
        System.out.println("Liczba błędów LRU: "+ faults);
    }
    public static void APP(ArrayList<Integer> pagelist,int RAM_length){
        int faults=0;
        int[] RAM = new int[RAM_length];
    }
    public static void RAND(ArrayList<Integer> pagelist,int RAM_length){
        int faults=0;
        boolean isfault = true;
        ArrayList<Integer> RAM = new ArrayList<>();
        for(Integer page:pagelist){
            isfault = true;
            for (int i=0;i<RAM.size();i++){
                if(page == RAM.get(i)){
                    isfault = false;
                }
            }
            if(isfault) {
                faults++;
                if(RAM.size()==RAM_length){
                    Random random = new Random();
                    RAM.set(random.nextInt(RAM_length),page);

                }else RAM.add(page);
            }
        }
        System.out.println("Liczba błędów RAND: "+ faults);
    }
}
