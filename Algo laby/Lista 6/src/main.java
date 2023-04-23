public class main {
    public static void main(String[] args) {
       int[] inty = new int[] {270, 980, 654, 130, 211, 548, 990, 836, 272, 103};
       IterativeMergeSorter mergeSorter = new IterativeMergeSorter(new Checker(null));
       mergeSorter.sort(inty);
       for (Integer i : inty)
        System.out.println(i);
    }
}
