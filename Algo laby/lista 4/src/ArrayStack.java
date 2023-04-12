package com.company;

import com.company.exceptions.FullStackException;
import java.util.EmptyStackException;

public class ArrayStack<T> implements IStack<T>{
    private final int CAPACITY;
    T array[];
    int top_index;

    public ArrayStack(int capacity) {
        array=(T[])(new Object[capacity]);
        top_index =0;
        this.CAPACITY = capacity;
    }

    @Override
    public boolean isEmpty() {
        return top_index ==0;
    }

    @Override
    public boolean isFull() {
        return top_index ==array.length;
    }

    @Override
    public T top() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return array[top_index -1];
    }

    @Override
    public T pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return array[--top_index];
    }

    @Override
    public void push(T value) throws FullStackException {
        if(isFull())
            throw new FullStackException();
        array[top_index++]=value;
    }

    @Override
    public int size() {
        return top_index;
    }
//    private Node first_node = null;
//    private Node last_node = null;
//    private int size = 0;
//    private int capacity;
//    public class Node{
//        private Node previous_node = null;
//        private T value;
//
//        public Node(T value){
//            this.value=value;
//        }
//
//        public Node getPrevious_node() {
//            return previous_node;
//        }
//
//        public void setPrevious_node(Node previous_node) {
//            this.previous_node = previous_node;
//        }
//
//        public T getValue() {
//            return value;
//        }
//
//        public void setValue(T value) {
//            this.value = value;
//        }
//    }
//    public ArrayStack(int capacity) {
//        // TODO
//        this.capacity = capacity;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        // TODO
//        return size == 0;
//    }
//
//    @Override
//    public boolean isFull() {
//        // TODO
//        return size == capacity;
//    }
//
//    @Override
//    public T top() throws EmptyStackException {
//        // TODO
//        if(size == 0)
//            throw new EmptyStackException();
//        return last_node.value;
//    }
//
//    @Override
//    public T pop() throws EmptyStackException {
//        // TODO
//        if(size == 0)
//            throw new EmptyStackException();
//        T temp = last_node.value;
//        if(size == 1){
//            last_node = null;
//            first_node = null;
//        }else
//        last_node = last_node.getPrevious_node();
//        size--;
//        return temp;
//    }
//
//    @Override
//    public void push(T value) throws FullStackException {
//        // TODO
//        if(size == capacity)
//            throw new FullStackException();
//        Node temp = new Node(value);
//        if(size == 0)
//            first_node = temp;
//        else
//            temp.setPrevious_node(last_node);
//        last_node = temp;
//        size++;
//    }
//
//    @Override
//    public int size() {
//        // TODO
//        return size;
//    }
}
