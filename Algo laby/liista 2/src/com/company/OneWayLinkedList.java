package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<T> implements IList<T> {
    private Node first_node;
    private Node last_node;
    private int size;

    public OneWayLinkedList() {
        this.last_node = null;this.size = 0;this.first_node = null;
    }

    public class Node {
        private T contained_obj;
        Node next_node;
        public Node(T o) {
            this.contained_obj = o;
            this.next_node = null;
        }

        public T getContained_obj() {
            return contained_obj;
        }

        public void setContained_obj(T contained_obj) {
            this.contained_obj = contained_obj;
        }

        public Node getNext_node() {
            return next_node;
        }
        public void setNext_node(Node next_node) {
            this.next_node = next_node;
        }

        @Override
        public String toString() {
        return "Node{"  + contained_obj +
                    '}';
        }
    }
    @Override
    public void add(T value) {
        // TODO
        Node new_node = new Node(value);
        if(last_node != null){
            last_node.setNext_node(new_node);
        }else first_node = new_node;
        size++;
        last_node = new_node;
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException {
        if(index<0 || index>size){
            throw new NoSuchElementException();
        }else if(index != size){
            Node new_node = new Node(value);
            if(index == 0) {
                new_node.setNext_node(first_node);
                first_node = new_node;
            }else{
                Node node_before = first_node;
                Node node_after;
                for(int i = 0;i<index-1;i++){
                    node_before = node_before.getNext_node();
                }
                node_after = node_before.getNext_node();
                node_before.setNext_node(new_node);
                new_node.setNext_node(node_after);
            }
            size++;
        }else add(value);
        // TODO
    }

    @Override
    public void clear() {
        // TODO
        //czy tutaj odlączać każdego node a po kolei?
        first_node = null;
        last_node = null;
        size = 0;
    }

    @Override
    public boolean contains(T value) {
        // TODO
        Iterator<T> iterator = this.iterator();
        T temp = null;
        while (iterator.hasNext()){
            temp = iterator.next();
            if(temp == value)
                return true;
        }return false;
    }

    @Override
    public T get(int index) throws NoSuchElementException {
        Iterator<T> iterator = iterator();
        T returned_val=null;
        while (index>=0){
            returned_val = iterator.next();
            index--;
        }
        return returned_val;
        // TODO
    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {

        // TODO
        if(index<0 || index>=size){
            throw new NoSuchElementException();
        }else {
            Node to_be_changed = first_node;
            for (int i = 0; i < index; i++)
                to_be_changed = to_be_changed.getNext_node();

            to_be_changed.setContained_obj(value);
        }
    }

    @Override
    public int indexOf(T value) {
        // TODO
        Iterator<T> iterator = this.iterator();
        T temp = null;
        int returned_index = -1;
        while (iterator.hasNext()){
            temp = iterator.next();
            returned_index++;
            if(temp == value)
                return returned_index;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size<1;
        // TODO
    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        // TODO
        Node to_be_removed = first_node;
        if(index<0 || index>=size){
            throw new NoSuchElementException();
        }else if(index == 0){
            first_node = first_node.getNext_node();
        }
        else {
            Node node_before = first_node;
            Node node_after = null;
            for (int i = 0; i < index-1; i++)
                node_before = node_before.getNext_node();

            to_be_removed = node_before.getNext_node();
            node_after = to_be_removed.getNext_node();

            node_before.setNext_node(node_after);
        }
        return to_be_removed.getContained_obj();
    }

    @Override
    public boolean remove(T value) {
        Node before_to_be_removed = first_node;
        if(before_to_be_removed.getContained_obj() == value){
            first_node = first_node.getNext_node();
            return true;
        }
        for(int i = 0;i<size-1;i++) {
            if(before_to_be_removed.getNext_node().getContained_obj() == value) {
                before_to_be_removed.setNext_node(before_to_be_removed.getNext_node().getNext_node());
                return true;
            }
            before_to_be_removed = before_to_be_removed.getNext_node();
        }
        return false;
        // TODO
    }

    @Override
    public int size() {
        return size;
        // TODO
    }

    @Override
    public Iterator<T> iterator() {
        return new OneWayLinkedListIterator();
    }

    private class OneWayLinkedListIterator implements Iterator<T> {
        Node returned_node = null;
        private int position = 0;
        @Override
        public boolean hasNext() {
            // TODO
            return position<size;
        }

        @Override
        public T next() {
            // TODO
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            else
            {
                if (returned_node == null)
                {
                    returned_node = first_node;
                    position++;
                }
                else
                {
                    returned_node = returned_node.getNext_node();
                    position++;
                }
                return returned_node.getContained_obj();
            }
        }
    }

    @Override
    public String toString() {
        return "OneWayLinkedList{" +
                "first_node=" + first_node +
                ", last_node=" + last_node +
                ", size=" + size +
                '}';
    }
}
