package com.company;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class Tests {
    private final String newLine = System.lineSeparator();

    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    @org.junit.jupiter.api.BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @org.junit.jupiter.api.AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @org.junit.jupiter.api.Test
    void drawRectangle11() {
        Drawer.drawRectangle(1, 1);
        assertOutput("#" + newLine);
    }

    @org.junit.jupiter.api.Test
    void drawRectangle21() {
        Drawer.drawRectangle(2, 1);
        assertOutput("##" + newLine);
    }

    @org.junit.jupiter.api.Test
    void drawRectangle12() {
        Drawer.drawRectangle(1, 2);
        assertOutput(
                "#" + newLine +
                             "#" + newLine);
    }

    @org.junit.jupiter.api.Test
    void drawRectangle63() {
        Drawer.drawRectangle(6, 3);
        assertOutput(
                "######" + newLine +
                             "#    #" + newLine +
                             "######" + newLine);
    }

    @org.junit.jupiter.api.Test
    void drawRectangleFail03() {
        Drawer.drawRectangle(0, 3);
        assertOutput("fail" + newLine);
    }

    @org.junit.jupiter.api.Test
    void drawRectangleFail20() {
        Drawer.drawRectangle(2, 0);
        assertOutput("fail" + newLine);
    }

    @org.junit.jupiter.api.Test
    void drawRectangleFail14() {
        Drawer.drawRectangle(-1, 4);
        assertOutput("fail" + newLine);
    }

    @org.junit.jupiter.api.Test
    void drawRectangleFail51() {
        Drawer.drawRectangle(5, -1);
        assertOutput("fail" + newLine);
    }

    void assertOutput(String expectedValue) {
        assertEquals(expectedValue, outContent.toString());
    }
}