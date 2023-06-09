package com.company;

public class Drawer {
    public static void drawTriangle(int size) {
        if(size>0) {
            String temp = "";
            for (int i = 1; i < size + 1; i++) {
                temp = temp + "#";
                System.out.println(temp);
            }
        }else System.out.println("fail");
    }

    public static void drawSquare(int size) {
        if(size ==1)
            System.out.println("#");
        else if(size>0) {
            String middle_lines = "#";
            middle_lines = middle_lines + returnCharSeq(size - 2, " ") + "#";

            drawCharSeq(size, "#");

            for (int i = 0; i < size - 2; i++)
                System.out.println(middle_lines);

            drawCharSeq(size, "#");
        }else System.out.println("fail");
    }

    public static void drawPyramid(int size) {
        if(size>0)
        drawPyramidWithOffset(size,0);
        else System.out.println("fail");
    }

    public static void drawPyramidWithOffset(int size,int offset) {
        String temp = "";
        for(int i=1; i<size+1; i++){
            temp = temp + returnCharSeq(size-i+offset," ");
            temp = temp + returnCharSeq((1+2*(i-1)),"#");
            System.out.println(temp);
            temp = "";
        }
    }


    public static void drawChristmasTree(int size) {
        if(size>0)
        for(int i = 0; i<size ; i++)
            drawPyramidWithOffset(i+1,size-i-1);

        else System.out.println("fail");
    }

    public static void drawCharSeq(int size,String character){
        String temp = character;
        for(int i = 1 ; i < size ; i++){
            temp = temp + character;
        }
        System.out.println(temp);
    }

    public static String returnCharSeq(int size,String character){
        String temp = "";
        for(int i = 1 ; i < size+1 ; i++){
            temp = temp + character;
        }
        return temp;
    }
}
