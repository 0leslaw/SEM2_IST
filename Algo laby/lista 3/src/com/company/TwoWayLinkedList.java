package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<T> {
    private Node sentinel;
    private Node last_node;
    private int size = 0;

    public TwoWayLinkedList() {
        sentinel = new Node(null);
        last_node = sentinel;
    }

    public class Node{
        private Node previous_node=null;
        private Node next_node=null;
        private T value;

        public Node(T value) {
            this.value = value;
        }

        public Node getPrevious_node() {
            return previous_node;
        }

        public void setPrevious_node(Node previous_node) {
            this.previous_node = previous_node;
        }

        public Node getNext_node() {
            return next_node;
        }

        public void setNext_node(Node next_node) {
            this.next_node = next_node;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "previous_node=" + previous_node +
                    ", next_node=" + next_node +
                    ", value=" + value +
                    '}';
        }
    }

    public void add(T value) {
        // TODO
        Node new_node = new Node(value);
        last_node.setNext_node(new_node);
        new_node.setPrevious_node(last_node);
        last_node = last_node.getNext_node();
        size++;
    }

    public void addAt(int index, T value) throws NoSuchElementException {
        // TODO
        if(index>size)
            throw new NoSuchElementException();

        Node node_before;
        Node new_node = new Node(value);
        node_before = findAtIndex(index-1);

        if(node_before.getNext_node() != null) {
            Node moved_node;
            moved_node = node_before.getNext_node();
            new_node.setNext_node(moved_node);
            moved_node.setPrevious_node(new_node);
        }
        new_node.setPrevious_node(node_before);
        node_before.setNext_node(new_node);
        size++;
    }

    public void clear() {
        sentinel.setNext_node(null);
        size = 0;
        last_node = null;
        // TODO
    }

    public boolean contains(T value) {
        // TODO
        Iterator<T> iterator = this.iterator();
        T temp = null;
        while (iterator.hasNext()){
            temp = iterator.next();
            if(value == temp)
                return true;
        }
        return false;
    }

    public T get(int index) throws NoSuchElementException {
        // TODO

        //jesli index > pol size to mozna szukac od konca
        Iterator<T> iterator = this.iterator();
        T temp = null;
        while (index>-1){
            temp = iterator.next();
            index--;
        }
        return temp;
    }

    public void set(int index, T value) throws NoSuchElementException {
        // TODO
        if(index<0||index>size-1)
            throw new NoSuchElementException();
        Node to_be_set = findAtIndex(index);
        to_be_set.setValue(value);
    }

    public int indexOf(T value) {
        // TODO
        int index = -1;
        Iterator<T> iterator = this.iterator();
        T temp = null;
        while (iterator.hasNext()){
            temp = iterator.next();
            index++;
            if(temp == value) {
                return index;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    public T removeAt(int index) throws NoSuchElementException {
        // TODO
        if(index<0||index>size-1)
            throw new NoSuchElementException();
        Node before = findAtIndex(index-1);
        Node to_be_removed = before.getNext_node();

        if(to_be_removed.getNext_node() != null) {
            Node after = to_be_removed.getNext_node();
            before.setNext_node(after);
            after.setPrevious_node(before);
        }else {before.setNext_node(null); last_node = before;}
        size--;
        return to_be_removed.getValue();
    }

    public boolean remove(T value) {
        // TODO
        Node to_be_removed=sentinel;
        Node before;
        Node after;
        for(int i=0;i<size;i++){
            to_be_removed = to_be_removed.getNext_node();
            if(to_be_removed.getValue() == value){
                before = to_be_removed.getPrevious_node();
                if(to_be_removed.getNext_node() != null){
                    after = to_be_removed.getNext_node();
                    before.setNext_node(after);
                    after.setPrevious_node(before);
                }else before.setNext_node(null);
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        // TODO
        return size;
    }
    public Node findAtIndex(int index){
        // minus one returns sentinel
        if(index>size-1 || index<-1)
            throw new NoSuchElementException();
        Node temp = sentinel;
        for(int i=0;i<index+1;i++){
            temp = temp.getNext_node();
        }
        return temp;
    }

    public Iterator<T> iterator() {
        return new TwoWayLinkedListIterator();
    }

    private class TwoWayLinkedListIterator implements Iterator<T> {
        private Node temp_node = sentinel;
        @Override
        public boolean hasNext() {
            // TODO
            return temp_node.getNext_node() != null;
        }

        @Override
        public T next() {
            // TODO
            if(hasNext())
                temp_node = temp_node.getNext_node();
            else {
                temp_node = null;
                throw new NoSuchElementException();
            }
            return temp_node.getValue();
        }
    }

    public Node getSentinel() {
        return sentinel;
    }

    public void setSentinel(Node sentinel) {
        this.sentinel = sentinel;
    }

    public Node getLast_node() {
        return last_node;
    }

    public void setLast_node(Node last_node) {
        this.last_node = last_node;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void insert(
            TwoWayLinkedList<T> anotherList,
            int beforeIndex) throws NoSuchElementException {
        // TODO
        if(beforeIndex<0||beforeIndex>this.size-2)
            throw new NoSuchElementException();
        Node before = findAtIndex(beforeIndex-1);
        Node after = before.getNext_node();
        before.setNext_node(anotherList.findAtIndex(0));
        anotherList.findAtIndex(0).setPrevious_node(before);
        after.setPrevious_node(anotherList.getLast_node());
        anotherList.getLast_node().setNext_node(after);
        size += anotherList.size();

    }

    public void insert(
            TwoWayLinkedList<T> anotherList,
            T beforeElement) throws NoSuchElementException {
        // TODO
        if(indexOf(beforeElement) == -1)
            throw new NoSuchElementException();
        Node before = findAtIndex(indexOf(beforeElement)-1);
        Node after = before.getNext_node();
        before.setNext_node(anotherList.findAtIndex(0));
        anotherList.findAtIndex(0).setPrevious_node(before);
        after.setPrevious_node(anotherList.getLast_node());
        anotherList.getLast_node().setNext_node(after);
        size += anotherList.size();
    }
}
