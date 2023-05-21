public class main {
    public static void main(String[] args) throws ItemOutOfRangeException {
        int size = 16;
        DisjointSetForest structure = new DisjointSetForest(16);
        for (var i = 0; i < size; i += 2) {
            structure.union(i, i + 1);
        }

        for (var i = 0; i < size; i += 4) {
            structure.union(i, i + 2);
        }

        for (var i = 0; i < size; i += 8) {
            structure.union(i, i + 4);
        }
        int i =3;
    }
}
