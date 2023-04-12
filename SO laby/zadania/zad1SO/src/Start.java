import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Start extends JFrame {
    private static JTextField pole_delay = new JTextField("50",5);
    private static JTextField pole_ile_kwantow_na_jeden_proces = new JTextField("15",5);
    private JLabel podaj_Delay = new JLabel("Podaj delay");
    private JLabel podaj_Ile_k_n_j_p = new JLabel("Podaj ile kwantów przypada na jeden proces");
    private Panel_symulacji panel_sym;

    {
        try {
            panel_sym = new Panel_symulacji();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//        private ramkaUstawien ramkaUstawien = new ramkaUstawien(this);

    public Start() {
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
        pole_delay.setBounds(400,550,70,40);
        pole_delay.setFont(new Font("SansSerif",0,20));
        pole_delay.setBackground(Color.DARK_GRAY);
        pole_delay.setForeground(Color.white);
        pole_ile_kwantow_na_jeden_proces.setBounds(1100,550,70,40);
        pole_ile_kwantow_na_jeden_proces.setFont(new Font("SansSerif",0,20));
        pole_ile_kwantow_na_jeden_proces.setBackground(Color.DARK_GRAY);
        pole_ile_kwantow_na_jeden_proces.setForeground(Color.white);
        podaj_Ile_k_n_j_p.setBounds(650,550,500,40);
        podaj_Ile_k_n_j_p.setFont(new Font("SansSerif",0,20));
        podaj_Ile_k_n_j_p.setForeground(Color.white);
        podaj_Delay.setBounds(250,550,300,40);
        podaj_Delay.setFont(new Font("SansSerif",0,20));
        podaj_Delay.setForeground(Color.white);
        this.add(podaj_Delay);
        this.add(podaj_Ile_k_n_j_p);
        this.add(pole_delay);
        this.add(pole_ile_kwantow_na_jeden_proces);

    }

    public static JTextField getPole_delay() {
        return pole_delay;
    }

    public static JTextField getPole_ile_kwantow_na_jeden_proces() {
        return pole_ile_kwantow_na_jeden_proces;
    }
}