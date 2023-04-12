package com.company;

public class Drawer {
    public static void drawRectangle(int width, int height) {
        // TODO
        String top_bottom = "";
        String middle = "#";

        if (width>0 && height>0) {
            for (int i = 0; i < width; i++)
                top_bottom += "#";
            for (int i = 0; i < width - 2; i++)
                middle += " ";
            middle += "#";

            System.out.println(top_bottom);
            for (int i = 0; i < height - 2; i++)
                System.out.println(middle);
            if(height>1)
            System.out.println(top_bottom);
        }else System.out.println("fail");
    }
}
