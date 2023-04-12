package com.company;

import com.company.exceptions.*;

public class TwoWayLinkedListQueue<T> implements IQueue<T> {
    private Node first_node = null;
    private Node last_node = null;
    private int size = 0;
    private int capacity;

    public class Node{
        private Node next_node = null;
        private Node previous_node = null;
        private T value;

        public Node(T value){
            this.value=value;
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
    }
    public TwoWayLinkedListQueue(int capacity) {
        // TODO
        this.capacity = capacity;
    }

    @Override
    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    @Override
    public boolean isFull() {
        // TODO
        return size == capacity;
    }

    @Override
    public void enqueue(T value) throws FullQueueException {
        // TODO
        if(size == capacity)
            throw new FullQueueException();
        Node temp = new Node(value);
        if(size == 0)
            first_node = temp;
        else {
            last_node.setNext_node(temp);
            temp.setPrevious_node(last_node);
        }
        last_node = temp;
        size++;
    }

    @Override
    public T first() throws EmptyQueueException {
        // TODO
        if(size == 0)
            throw new EmptyQueueException();
        return first_node.getValue();
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        // TODO
        if(size == 0)
            throw new EmptyQueueException();
        T temp = first_node.getValue();
        if(first_node.getNext_node() == null) {
            first_node = null;
            last_node = null;
        }else {
            first_node = first_node.getNext_node();
            first_node.setPrevious_node(null);
        }
        size--;
        return temp;
    }

    @Override
    public int size() {
        // TODO
        return size;
    }
}
