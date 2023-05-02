

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
    private static int PODZIELNIK_WYSOKOSCI = 3;
    private static int MNOZNIKDLUGOSCIGRAFU = 5;
    private static int MAX_POJEMNOSC;

    private static int DELAY;
    private Timer timer;
    private boolean running = false;
    Random random = new Random();
    private Color[] kolory;
    private ArrayList<Zbior> zbioryWartosci;


    public Panel_symulacji(ArrayList<Zbior> zbioryWartosci,int MAX_POJ) throws InterruptedException, IOException, ClassNotFoundException {
        this.zbioryWartosci = zbioryWartosci;
        ROZM_PLYTKI = 2;
        SZEROKOSC = ROZM_PLYTKI * 1400;
        WYSOKOSC = ROZM_PLYTKI * 250;
        MAX_POJEMNOSC = MAX_POJ;


        this.setBounds(15, 35, SZEROKOSC, WYSOKOSC);
        this.setBackground(Color.white);
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(new mojKeyAdpter());
        kolory = new Color[]{Color.blue, Color.red, Color.green.darker()};



        startSim();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

    }
    public void draw(Graphics g) {

        g.setFont(new Font("SansSerif", 0, 20));
        g.setColor(Color.darkGray);
        g.fillRect(0,0,2000,75);
        g.setColor(kolory[0]);
        g.drawString("Zraz ", 0, 15);
        g.drawString("Ilość zapytan: "+
                zbioryWartosci.get(0).getIloscZapytan()+
                "   Ilość migracji: "+zbioryWartosci.get(0).getIloscMigracji(),1000,15);

        g.setColor(kolory[1]);
        g.drawString("Obciazony x próbuje rozdać ", 0, 10 * ROZM_PLYTKI + 20);
        g.drawString("Ilość zapytan: "+
                zbioryWartosci.get(1).getIloscZapytan()+
                "   Ilość migracji: "+zbioryWartosci.get(1).getIloscMigracji(),1000,10 * ROZM_PLYTKI + 20);

        g.setColor(kolory[2]);
        g.drawString("Zraz, tylko każdy y którego obciążenie jest małe próbuje uwolnić jakiegoś x,którego jest duże ", 0, 10 * ROZM_PLYTKI + 45);
        g.drawString("Ilość zapytan: "+
                zbioryWartosci.get(2).getIloscZapytan()+
                "   Ilość migracji: "+zbioryWartosci.get(2).getIloscMigracji(),1000,10 * ROZM_PLYTKI + 45);


        int kolorek = 0;
        int przesY = 15;
        for(int j = 0; j< zbioryWartosci.size();j++){
            g.setColor(kolory[j]);
            for (int i = 1; i< zbioryWartosci.get(j).getZbiorSrednichWCzasie().size(); i++) {
                g.drawLine(50+(i - 1) * MNOZNIKDLUGOSCIGRAFU, (450 - zbioryWartosci.get(j).getZbiorSrednichWCzasie().get(i - 1) / PODZIELNIK_WYSOKOSCI), 50+i * MNOZNIKDLUGOSCIGRAFU, (450 - zbioryWartosci.get(j).getZbiorSrednichWCzasie().get(i) / PODZIELNIK_WYSOKOSCI));
                g.drawLine(50+(i - 1) * MNOZNIKDLUGOSCIGRAFU, (450 - zbioryWartosci.get(j).getZbiorSrednichOdchylenWCzasie().get(i - 1) / PODZIELNIK_WYSOKOSCI), 50+i * MNOZNIKDLUGOSCIGRAFU, (450 - zbioryWartosci.get(j).getZbiorSrednichOdchylenWCzasie().get(i) / PODZIELNIK_WYSOKOSCI));
            }
        }
        g.setColor(Color.BLACK);
        g.drawLine(50,450,1500,450);
        g.drawLine(50,450,50,450-MAX_POJEMNOSC/PODZIELNIK_WYSOKOSCI);
        g.drawString("100%",5,450-MAX_POJEMNOSC/PODZIELNIK_WYSOKOSCI);
        g.drawString("50%",5,450-(MAX_POJEMNOSC/PODZIELNIK_WYSOKOSCI)/2);
        g.drawString(zbioryWartosci.get(0).getZbiorSrednichWCzasie().size()*15+"            jednostek czasu" ,50+zbioryWartosci.get(0).getZbiorSrednichWCzasie().size()*MNOZNIKDLUGOSCIGRAFU,470);
        g.drawLine(50+zbioryWartosci.get(0).getZbiorSrednichWCzasie().size()*MNOZNIKDLUGOSCIGRAFU,450,50+zbioryWartosci.get(0).getZbiorSrednichWCzasie().size()*MNOZNIKDLUGOSCIGRAFU,455);
        g.drawString("sredni procent obciazenia",5,90);
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
