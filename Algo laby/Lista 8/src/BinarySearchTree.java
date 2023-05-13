public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;
    public class Node{
        private T value;
        private Node parent;
        private Node leftChild;
        private Node rightChild;
        public Node (T value){
            this.value = value;
        }
        public String toString(){
            return value.toString();
        }
    }
    public BinarySearchTree(){
        root = null;
    }
    public void add(T value) throws DuplicateElementException {
        // TODO: Dodawanie nowej wartości do drzewa. Rzuć DuplicateElementException, jeśli element już istnieje.
        if(root == null) {
            root = new Node(value);
        }else
        addToRoot(value, root);
    }
    public void addToRoot(T value, Node root) throws DuplicateElementException {
        if(root.value.equals(value))
            throw new DuplicateElementException();

        if(root.value.compareTo(value) > 0)
        {
            if(root.leftChild != null)
                addToRoot(value, root.leftChild);
            else {
                root.leftChild = new Node(value);
                root.leftChild.parent = root;
            }
        }
        else
        {
            if (root.rightChild != null)
                addToRoot(value, root.rightChild);
            else {
                root.rightChild = new Node(value);
                root.rightChild.parent = root;
            }
        }
    }


    public boolean contains(T value) {
        // TODO: Sprawdzenie, czy drzewo zawiera podaną wartość.
        return searchMethod(root,value);
    }
    private boolean searchMethod(Node root,T value) {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą pre-order.
        if(root != null) {
            if(root.value.equals(value))
                return true;
            if(root.value.compareTo(value) >0)
                return searchMethod(root.leftChild,value);
            else
                return searchMethod(root.rightChild,value);
        }
        return false;
    }
    private Node searchForNode(Node root,T value) {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą pre-order.
        if(root != null) {
            if(root.value.equals(value))
                return root;
            if(root.value.compareTo(value) >0)
                return searchForNode(root.leftChild,value);
            else
                return searchForNode(root.rightChild,value);
        }
        return null;
    }

    public void delete(T value) {
        // TODO: Usunięcie wskazanej wartości z drzewa.
        if(root == null)
            return;

        Node toBeDeleted = searchForNode(root,value);

        //usuwany jest lisciem
        if (toBeDeleted.leftChild == null && toBeDeleted.rightChild == null) {
            if(toBeDeleted == root)
                root = null;
            else if(toBeDeleted.value.compareTo(toBeDeleted.parent.value)<0)
                toBeDeleted.parent.leftChild = null;
            else
                toBeDeleted.parent.rightChild = null;
        }

        //usuwany ma tylko lewego potomka
        else if (toBeDeleted.leftChild != null && toBeDeleted.rightChild == null){
            toBeDeleted.value = toBeDeleted.leftChild.value;
            Node temp_left = toBeDeleted.leftChild;

            toBeDeleted.leftChild = temp_left.leftChild;
            if(toBeDeleted.leftChild != null)
                toBeDeleted.leftChild.parent = toBeDeleted;

            toBeDeleted.rightChild = temp_left.rightChild;
            if(toBeDeleted.rightChild != null)
                toBeDeleted.rightChild.parent = toBeDeleted;
        }

        //usuwany ma tylko prawego potomka
        else if (toBeDeleted.leftChild == null && toBeDeleted.rightChild != null){
            toBeDeleted.value = toBeDeleted.rightChild.value;
            Node temp_right = toBeDeleted.rightChild;

            toBeDeleted.rightChild = temp_right.rightChild;
            if(toBeDeleted.rightChild != null)
                toBeDeleted.rightChild.parent = toBeDeleted;

            toBeDeleted.leftChild = temp_right.leftChild;
            if(toBeDeleted.leftChild != null)
                toBeDeleted.leftChild.parent = toBeDeleted;
        }

        //gdy ma obydwu potomkow
        else {
            Node inOrderSuccessor = toBeDeleted.rightChild;

            while (inOrderSuccessor.leftChild != null)
                inOrderSuccessor = inOrderSuccessor.leftChild;

            if(inOrderSuccessor.parent != toBeDeleted) {
                if (inOrderSuccessor.rightChild != null) {
                    inOrderSuccessor.parent.leftChild = inOrderSuccessor.rightChild;
                    inOrderSuccessor.rightChild.parent = inOrderSuccessor.parent;
                }
                else
                    inOrderSuccessor.parent.leftChild = null;
            }else{
                if (inOrderSuccessor.rightChild != null) {
                    inOrderSuccessor.parent.rightChild = inOrderSuccessor.rightChild;
                    inOrderSuccessor.rightChild.parent = inOrderSuccessor.parent;
                }
                else
                    inOrderSuccessor.parent.rightChild = null;
            }
            toBeDeleted.value = inOrderSuccessor.value;
        }

    }

    public String toStringPreOrder() {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą pre-order.
        String returned = "";
        if(root == null)
            return returned;
        return toStringPreOrderHelper(root,returned).substring(0,toStringPreOrderHelper(root,returned).length()-2);
    }
    private String toStringPreOrderHelper(Node root,String returned) {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą pre-order.
        if(root != null) {
            returned += root.toString()+", ";
            returned = toStringPreOrderHelper(root.leftChild, returned);
            returned = toStringPreOrderHelper(root.rightChild, returned);
        }
        return returned;
    }

    public String toStringInOrder() {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą in-order.
        String returned = "";
        if(root == null)
            return returned;
        return toStringInOrderHelper(root,returned).substring(0,toStringPreOrderHelper(root,returned).length()-2);

    }
    private String toStringInOrderHelper(Node root,String returned) {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą pre-order.
        if(root != null) {
            returned = toStringInOrderHelper(root.leftChild, returned);
            returned += root.toString()+", ";
            returned = toStringInOrderHelper(root.rightChild, returned);
        }
        return returned;
    }

    public String toStringPostOrder() {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą in-order.
        String returned = "";
        if(root == null)
            return returned;
        return toStringPostOrderHelper(root,returned).substring(0,toStringPreOrderHelper(root,returned).length()-2);
    }
    private String toStringPostOrderHelper(Node root,String returned) {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą pre-order.
        if(root != null) {
            returned = toStringPostOrderHelper(root.leftChild, returned);
            returned = toStringPostOrderHelper(root.rightChild, returned);
            returned += root.toString()+", ";
        }
        return returned;
    }
}
