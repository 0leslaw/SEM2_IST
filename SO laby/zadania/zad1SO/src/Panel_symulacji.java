import Algo.FIFO;
import Algo.RR;
import Algo.SJF;
import Algo.wSJF;
import Procesy.Grupa_procesow;
import Procesy.Proces;

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

public class Panel_symulacji extends JPanel implements ActionListener {
    private static final DecimalFormat df = new DecimalFormat("0.000");
    private static ArrayList<Grupa_procesow> lista_grupprocesow = new ArrayList<>();
    private static int ROZM_PLYTKI;
    private static int WYSOKOSC;
    private static int SZEROKOSC;
    private static int k_na_z;
    private static int DELAY;
    private static String nazwa_pliku;
    private int sterownik_histografu = 0;
    private Timer timer;
    private boolean running = false;
    Random random = new Random();
    int ktory_kwant = 1;
    private Color[] kolory;


    public Panel_symulacji() throws InterruptedException, IOException, ClassNotFoundException {
        ROZM_PLYTKI = 2;
        SZEROKOSC = ROZM_PLYTKI * 1400;
        WYSOKOSC = ROZM_PLYTKI * 250;
        DELAY = 50;
        k_na_z = 15;


        this.setBounds(15, 35, SZEROKOSC, WYSOKOSC);
        this.setBackground(Color.DARK_GRAY.darker().darker().darker());
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(new mojKeyAdpter());
        kolory = new Color[]{Color.blue, Color.red, Color.CYAN, Color.MAGENTA};

        lista_grupprocesow.add(new Grupa_procesow(Serializacja.odczytDowolnejArrayListy("PlikListy.ser"),new FIFO()));
        lista_grupprocesow.add(new Grupa_procesow(Serializacja.odczytDowolnejArrayListy("PlikListy.ser"),new RR()));
        lista_grupprocesow.add(new Grupa_procesow(Serializacja.odczytDowolnejArrayListy("PlikListy.ser"),new SJF()));
        lista_grupprocesow.add(new Grupa_procesow(Serializacja.odczytDowolnejArrayListy("PlikListy.ser"),new wSJF()));

        startSim();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void restart() throws IOException {

    }

    public void draw(Graphics g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif",0,20));


        g.drawString("FIFO, średnia ilość zakończonych procesów w czasie: "+df.format((double) (lista_grupprocesow.get(0).getIlosc_przeszlych_procesow())/(double) ktory_kwant)+"        ilość skończonych procesów: "+lista_grupprocesow.get(0).getIlosc_przeszlych_procesow(),0,15);
        g.drawString("RR,   średnia ilość zakończonych procesów w czasie: "+df.format(((double) (lista_grupprocesow.get(1).getIlosc_przeszlych_procesow())/(double) ktory_kwant))+"          ilość skończonych procesów: "+lista_grupprocesow.get(1).getIlosc_przeszlych_procesow(),0,10 * ROZM_PLYTKI + 55);
        g.drawString("SJF,  średnia ilość zakończonych procesów w czasie: "+df.format(((double) (lista_grupprocesow.get(2).getIlosc_przeszlych_procesow())/(double) ktory_kwant))+"          ilość skończonych procesów: "+lista_grupprocesow.get(2).getIlosc_przeszlych_procesow(),0,10 * ROZM_PLYTKI + 115);
        g.drawString("wSJF, średnia ilość zakończonych procesów w czasie: "+df.format(((double) (lista_grupprocesow.get(3).getIlosc_przeszlych_procesow())/(double) ktory_kwant))+"         ilość skończonych procesów: "+lista_grupprocesow.get(3).getIlosc_przeszlych_procesow(),0,10 * ROZM_PLYTKI + 175);
        g.drawString("Ilosc przeszlych kwantow czasu: "+ktory_kwant,0,300);
        int kolorek = 0;
        int przesY = 15;
        for(Grupa_procesow grupa_procesow:lista_grupprocesow) {
            g.setColor(kolory[kolorek]);

            if(sterownik_histografu == 0)
            for(int i=0;i<grupa_procesow.getSrednie_czasy_zamkniecia_operacji().size()-1;i++){
                g.drawLine(i*3+150,470-(int)(grupa_procesow.getSrednie_czasy_zamkniecia_operacji().get(i)*3000),i*3+151,470-(int)(grupa_procesow.getSrednie_czasy_zamkniecia_operacji().get(i+1)*3000));
            }
            else if(sterownik_histografu == 1)
            for(int i=0;i<grupa_procesow.getSuma_czasow_oczekiwania_zamknietych_operacji().size()-1;i++){
                g.drawLine(i*3+150,470-(int)(grupa_procesow.getSuma_czasow_oczekiwania_zamknietych_operacji().get(i)*10),
                        i*3+151,470-(int)(grupa_procesow.getSuma_czasow_oczekiwania_zamknietych_operacji().get(i+1)*10));
            }
            if(running) {

                grupa_procesow.getSrednie_czasy_zamkniecia_operacji().add((double) (grupa_procesow.getIlosc_przeszlych_procesow()) / (double) ktory_kwant);
            }
            int przesuniecie = 15;
            kolorek++;
            for (int i = 0; i < grupa_procesow.getLista_procesow().size(); i++) {
                g.fillRect(przesuniecie * ROZM_PLYTKI, przesY*ROZM_PLYTKI, grupa_procesow.getLista_procesow().get(i).getCzas_pozostaly() * ROZM_PLYTKI, 10 * ROZM_PLYTKI);
                przesuniecie += 5 + grupa_procesow.getLista_procesow().get(i).getCzas_pozostaly();
            }
            przesY += 30;
        }
        if(!running){
            String nazwa="";
            int przes =300;
            for (Grupa_procesow grupa_procesow: lista_grupprocesow) {
                double temp = 0;
                for(Double srednia: grupa_procesow.getSrednie_czasy_zamkniecia_operacji())
                    temp+=srednia;
                temp = temp/grupa_procesow.getSrednie_czasy_zamkniecia_operacji().size();
                if(przes ==300)
                    nazwa = "FIFO: " ;
                if(przes ==320)
                    nazwa = "RR: " ;
                if(przes ==340)
                    nazwa = "SJF: " ;
                if(przes ==360)
                    nazwa = "wSJF: " ;


                g.drawString(nazwa+String.valueOf(temp),600,przes);
                przes+=20;
            }
        }

    }

    public void ruch() {
        for(Grupa_procesow grupa_procesow : lista_grupprocesow)
            if(!grupa_procesow.getLista_procesow().isEmpty())
                grupa_procesow.wykonajKrokAlgo(ktory_kwant,k_na_z);
        if(lista_grupprocesow.get(3).getLista_procesow().isEmpty())
            running = false;
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
            ruch();
            ktory_kwant++;
        }
        repaint();

    }

    public class mojKeyAdpter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    try {
                        lista_grupprocesow.removeAll(lista_grupprocesow);

                        lista_grupprocesow.add(new Grupa_procesow(Serializacja.odczytDowolnejArrayListy("PlikListy.ser"),new FIFO()));
                        lista_grupprocesow.add(new Grupa_procesow(Serializacja.odczytDowolnejArrayListy("PlikListy.ser"),new RR()));
                        lista_grupprocesow.add(new Grupa_procesow(Serializacja.odczytDowolnejArrayListy("PlikListy.ser"),new SJF()));
                        lista_grupprocesow.add(new Grupa_procesow(Serializacja.odczytDowolnejArrayListy("PlikListy.ser"),new wSJF()));
                        ktory_kwant =1;
                        if(Start.getPole_delay().getText().matches("[0-9]+") && Start.getPole_ile_kwantow_na_jeden_proces().getText().matches("[0-9]+")) {
                            DELAY = Integer.parseInt(Start.getPole_delay().getText());
                            setK_na_z(Integer.parseInt(Start.getPole_ile_kwantow_na_jeden_proces().getText()));
                            timer.setDelay(DELAY);
                        }
                        startSim();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                if(running)
                running= false;
                else running = true;
            }



        }

    }

    public static void setK_na_z(int k_na_z) {
        Panel_symulacji.k_na_z = k_na_z;
    }


}
