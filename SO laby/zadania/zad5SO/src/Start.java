import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Start extends JFrame {
    private Panel_symulacji panel_sym;

//        private ramkaUstawien ramkaUstawien = new ramkaUstawien(this);

    public Start(ArrayList<Zbior> zbioryWartosci,int MAX_POJ) {
        try {
            panel_sym = new Panel_symulacji(zbioryWartosci,MAX_POJ);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.setTitle("symulacja");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        Point punkt = new Point(50, 20);
        this.setLocation(punkt);
        this.setVisible(true);
        this.setSize(1500, 700);
        this.getContentPane().setBackground(Color.black);
        this.setLayout(null);
//        this.add(ramkaUstawien);
        this.add(panel_sym);


    }
}

