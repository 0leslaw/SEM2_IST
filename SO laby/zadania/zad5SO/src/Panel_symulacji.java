

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

public class Panel_symulacji extends JPanel implements ActionListener {
    private static final DecimalFormat df = new DecimalFormat("0.000");
    private static int ROZM_PLYTKI;
    private static int WYSOKOSC;
    private static int SZEROKOSC;
    private static int DELAY;
    private static String nazwa_pliku;
    private int sterownik_histografu = 0;
    private Timer timer;
    private boolean running = false;
    Random random = new Random();
    int ktory_kwant = 1;
    private Color[] kolory;
    private ArrayList<Zbior> zbioryWartosci;


    public Panel_symulacji(ArrayList<Zbior> zbioryWartosci) throws InterruptedException, IOException, ClassNotFoundException {
        this.zbioryWartosci = zbioryWartosci;
        ROZM_PLYTKI = 2;
        SZEROKOSC = ROZM_PLYTKI * 1400;
        WYSOKOSC = ROZM_PLYTKI * 250;


        this.setBounds(15, 35, SZEROKOSC, WYSOKOSC);
        this.setBackground(Color.white);
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(new mojKeyAdpter());
        kolory = new Color[]{Color.blue, Color.red, Color.CYAN, Color.MAGENTA};



        startSim();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {

        g.setFont(new Font("SansSerif", 0, 20));


//        g.setColor(kolory[0]);
//        g.drawString("Zraz ", 0, 15);
//        g.setColor(kolory[1]);
//        g.drawString("Obciazony x próbuje rozdać ", 0, 10 * ROZM_PLYTKI + 55);
//        g.setColor(kolory[2]);
//        g.drawString("Zraz, tylko każdy y którego obciążenie jest małe próbuje uwolnić jakiegoś x,którego jest duże : ", 0, 10 * ROZM_PLYTKI + 115);

        int kolorek = 0;
        int przesY = 15;
        for(int j = 0; j< zbioryWartosci.size();j++){
            g.setColor(kolory[j]);
            for (int i = 1; i< zbioryWartosci.get(j).getZbiorSrednichWCzasie().size(); i++)
                g.drawLine((i-1)*20,(450 - zbioryWartosci.get(j).getZbiorSrednichWCzasie().get(i-1)/3),i*20,(450 - zbioryWartosci.get(j).getZbiorSrednichWCzasie().get(i)/3));
            }
    }
    public void startSim() {
        timer = new Timer(DELAY, this);
        running = true;
        timer.start();
    }

    public void reset() throws IOException, InterruptedException {
        if (!running) {

            timer.start();
            running = true;

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {

        }
        repaint();

    }

    public class mojKeyAdpter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

        }

    }

}
