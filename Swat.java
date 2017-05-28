/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swat;


import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;

import java.awt.event.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.SwingUtilities;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JTextPane;
/**
 *
 * @author Tymoteusz
 */


public class Swat extends JFrame {
    
    private int WYSOKOSC_SWIATA = 20;
    private int SZEROKOSC_SWIATA = 20;

    private final int ILOSC_OWIEC = 3;
    private final int ILOSC_ANTYLOP = 3;
    private final int ILOSC_LISOW = 3;
    private final int ILOSC_ZOLWI = 2;
    private final int ILOSC_WILKOW = 2;
    
    private final int ILOSC_WILCZYCH_JAG = 1;
    private final int ILOSC_TRAW = 2;
    private final int ILOSC_MLECZY = 2;
    private final int ILOSC_GUARAN = 1;

    private File plik;
    
    private int tura;
    private int ilosc_raportow;   
    private int ile_organizmow;
    private Organizm[] organizmy;
    private Kontener mlode;
    private String[] raporter= new String[20]; // 20 raportow
    
    public Swat() 
    {
        this.mlode = new Kontener();
        this.organizmy = new Organizm[WYSOKOSC_SWIATA*SZEROKOSC_SWIATA];
        this.ile_organizmow = 0;
        this.tura = 0;
        this.ilosc_raportow = 0;
        
        int i;
        
        for (i = 0; i < ILOSC_OWIEC; i++)
		this.organizmy[i] = new Owca(this);
	for (; i < ILOSC_ANTYLOP+ILOSC_OWIEC; i++)
		this.organizmy[i] = new Antylopa(this);
	for (; i < ILOSC_LISOW+ ILOSC_ANTYLOP + ILOSC_OWIEC; i++)
		this.organizmy[i] = new Lis(this);
	for ( ; i < ILOSC_ZOLWI+ ILOSC_LISOW + ILOSC_ANTYLOP + ILOSC_OWIEC; i++)
		this.organizmy[i] = new Zolw(this);
	for ( ; i < ILOSC_WILKOW+ ILOSC_ZOLWI + ILOSC_LISOW + ILOSC_ANTYLOP + ILOSC_OWIEC; i++)
		this.organizmy[i] = new Wilk(this);
	for (; i < ILOSC_WILCZYCH_JAG+ILOSC_WILKOW + ILOSC_ZOLWI + ILOSC_LISOW + ILOSC_ANTYLOP + ILOSC_OWIEC; i++)
		this.organizmy[i] = new Wilcze_jag(this);
	for (; i < ILOSC_GUARAN+ILOSC_WILCZYCH_JAG + ILOSC_WILKOW + ILOSC_ZOLWI + ILOSC_LISOW + ILOSC_ANTYLOP + ILOSC_OWIEC; i++)
		this.organizmy[i] = new Guarana(this);
	for (; i < ILOSC_MLECZY+ILOSC_GUARAN + ILOSC_WILCZYCH_JAG + ILOSC_WILKOW + ILOSC_ZOLWI + ILOSC_LISOW + ILOSC_ANTYLOP + ILOSC_OWIEC; i++)
		this.organizmy[i] = new Mlecz(this);
	for (; i < ILOSC_TRAW+ILOSC_MLECZY + ILOSC_GUARAN + ILOSC_WILCZYCH_JAG + ILOSC_WILKOW + ILOSC_ZOLWI + ILOSC_LISOW + ILOSC_ANTYLOP + ILOSC_OWIEC; i++)
		this.organizmy[i] = new Trawa(this);
	this.organizmy[i] = new Czlowiek(this);
        
        //rysowanie
        try
        {
            initUI(this);
        }
        catch(FileNotFoundException a)
                {
                    
                }
        
    }
    
    private void sortuj()
    {
        for (int i = 0; i < this.ile_organizmow; i++)
	{
		for (int j = 0; j < this.ile_organizmow - 1; j++)
		{
			if (this.organizmy[j].zwroc_inicjatywe() < this.organizmy[j + 1].zwroc_inicjatywe())
				swap(this.organizmy[j], this.organizmy[j + 1]);
			else if(this.organizmy[j].zwroc_inicjatywe() == this.organizmy[j + 1].zwroc_inicjatywe() && this.organizmy[j].zwroc_wiek() < this.organizmy[j + 1].zwroc_wiek())
				swap(this.organizmy[j], this.organizmy[j + 1]);
		}
	}
    }
    
    
    
    private void wykonajTure(char a)
    {
        
        this.zeruj_raporter();
        
        this.sortuj();
        
        this.tura++;
        //System.out.println("przed tura " + ile_organizmow);
        for (int i=0; i< this.ile_organizmow;i++)
        {
           if(this.organizmy[i] instanceof Czlowiek)
               this.organizmy[i].akcja(a);
           else
               this.organizmy[i].akcja(3);
        }
        
                
        for(int i=0; i<this.mlode.zwroc_ile(); i++)
        {
            this.ile_organizmow++;
            this.organizmy[this.ile_organizmow -1]= this.mlode.zwroc_mlode(i);
        }
        
        //System.out.println("po turze " + ile_organizmow);
        this.mlode.usuwanie();
        
        for(int i=0; i < this.ile_organizmow-1;i++)
            this.organizmy[i].zmien_mozliwosc_poruszania(true);
       
    }
    
    private void zeruj_raporter()
    {
        for(int i=0;i< 20; i++)
            this.raporter[i]="";
        this.ilosc_raportow = 0;
    }
    
    public int zwrocTure()
    {
        return this.tura;
    }
    
    public int zwrocIleRap()
    {
        return this.ilosc_raportow;
    }
    
    public void zapisz_do_rejestru(String notka)
    {
        if(this.ilosc_raportow <20)
        {
            ilosc_raportow++;
            this.raporter[ilosc_raportow-1]=notka;
        }
    }
    
    public void zwieksz_ilosc_organizmow()
    {
        this.ile_organizmow++;
    }
    
    public int sprawdz_czy_jest_miejsce(int x, int y)
    {
        
        for(int i=0;i<this.ile_organizmow;i++)
        {
            if(this.organizmy[i] == null)
                System.out.println(i);
            
            if(x==this.organizmy[i].zwrocX() && y == this.organizmy[i].zwrocY())
            {
                return i;
            } 
            
            
        }
        return -1;
    }
    
    public int sprawdz_cz_nie_ma_mlodego(int x, int y)
    {
        return this.mlode.sprawdz_czy_jest_miejsce(x, y);
    }
    
    public Organizm zwroc_organizm(int nr_organizmu)
    {
        return this.organizmy[nr_organizmu];
    }
    
    
    
    public void usuwanie_organizmu(int nr_organizmu)
    {
        this.organizmy[nr_organizmu] = this.organizmy[this.ile_organizmow -1];
        this.organizmy[this.ile_organizmow - 1] = null;
        this.ile_organizmow--;
    }
    
    public boolean dodaj_mlode(Organizm obcy, char wyglad, Swat ref_na_swiat, int x, int y)
    {
        if(x >=0 && x <= this.zwroc_szerokosc_swiata()
                        && y >=0 && y <= this.zwroc_wysokosc_swiata())
            {
                if(this.sprawdz_czy_jest_miejsce(x, y) == -1 && (this.ile_organizmow + this.mlode.zwroc_ile() < SZEROKOSC_SWIATA*WYSOKOSC_SWIATA))
                {
                    return this.mlode.dodaj_mlode(obcy, wyglad, ref_na_swiat, x, y);
                }
                
            }
        return false;
    }
    
    private static void swap(Organizm a, Organizm b)
    {
        Organizm zmiana = a;
        a = b;
        b = zmiana;
    }
    
    private String zwroc_raport(int ktory)
    {
        return this.raporter[ktory];
    }
    
    public int zwroc_wysokosc_swiata()
    {
        return this.WYSOKOSC_SWIATA;
    }
    
    public int zwroc_szerokosc_swiata()
    {
        return this.SZEROKOSC_SWIATA;
    }
    
    private void zeruj_swiat()
    {
        for(int i=0; i<this.ile_organizmow;i++)
            this.organizmy[i]=null;
        
        this.ile_organizmow=0;
        this.ilosc_raportow = 0;
        this.tura=0;
    }
    
    private void zapisz_gre() throws FileNotFoundException
    {
        PrintWriter zapis = new PrintWriter("zapis.txt");
        zapis.println(this.tura + " " + this.ile_organizmow );
        for(int i=0;i<this.ile_organizmow;i++)
            zapis.println(this.organizmy[i].toString());
        zapis.close();
    }
    
    
    
    private void wczytaj_gre() throws FileNotFoundException
    {
        this.zeruj_swiat();
        
        plik = new File("zapis.txt");
        Scanner scan = new Scanner(plik);
        
        String linia = scan.nextLine();
        
        String[] wartosci = linia.split(" ", 0);
        
        this.tura = Integer.parseInt(wartosci[0]);
        int ile = Integer.parseInt(wartosci[1]);
        
        for(int i=0; i<ile; i++)
        {
            String dane_jednego_organizmu = scan.nextLine();
            this.wczytaj_organizmy(dane_jednego_organizmu, i);
        }
    }
    
    private void wczytaj_organizmy(String dane, int pozycja)
    {
        
        String[] wartosci = dane.split(" ", 0);
        
        switch(wartosci[0].charAt(0))
        {
            case 'O': this.organizmy[pozycja]= new Owca(this, Integer.parseInt(wartosci[1]), Integer.parseInt(wartosci[2]), Integer.parseInt(wartosci[3]), Integer.parseInt(wartosci[4]));break;
            case 'L': this.organizmy[pozycja]= new Lis(this, Integer.parseInt(wartosci[1]), Integer.parseInt(wartosci[2]), Integer.parseInt(wartosci[3]), Integer.parseInt(wartosci[4]));break;
            case 'W': this.organizmy[pozycja]= new Wilk(this, Integer.parseInt(wartosci[1]), Integer.parseInt(wartosci[2]), Integer.parseInt(wartosci[3]), Integer.parseInt(wartosci[4]));break;
            case 'Z': this.organizmy[pozycja]= new Zolw(this, Integer.parseInt(wartosci[1]), Integer.parseInt(wartosci[2]), Integer.parseInt(wartosci[3]), Integer.parseInt(wartosci[4]));break;
            case 'A': this.organizmy[pozycja]= new Antylopa(this, Integer.parseInt(wartosci[1]), Integer.parseInt(wartosci[2]), Integer.parseInt(wartosci[3]), Integer.parseInt(wartosci[4]));break;
            
            case 'C': this.organizmy[pozycja]= new Czlowiek(this, Integer.parseInt(wartosci[1]), Integer.parseInt(wartosci[2]), Integer.parseInt(wartosci[3]), Integer.parseInt(wartosci[4]));break;
               
            case '%': this.organizmy[pozycja]= new Guarana(this, Integer.parseInt(wartosci[1]), Integer.parseInt(wartosci[2]), Integer.parseInt(wartosci[3]), Integer.parseInt(wartosci[4]));break;
            case '|': this.organizmy[pozycja]= new Trawa(this, Integer.parseInt(wartosci[1]), Integer.parseInt(wartosci[2]), Integer.parseInt(wartosci[3]), Integer.parseInt(wartosci[4]));break;
            case ':': this.organizmy[pozycja]= new Wilcze_jag(this, Integer.parseInt(wartosci[1]), Integer.parseInt(wartosci[2]), Integer.parseInt(wartosci[3]), Integer.parseInt(wartosci[4]));break;
            case '*': this.organizmy[pozycja]= new Mlecz(this, Integer.parseInt(wartosci[1]), Integer.parseInt(wartosci[2]), Integer.parseInt(wartosci[3]), Integer.parseInt(wartosci[4]));break;
            default: break;
        }
        
    }
    
    
    
    
    
    private void initUI(Swat swiat) throws FileNotFoundException
    {
        JPanel podstawa = new JPanel();
        add(podstawa);
        
        JPanel plansza = new JPanel();
        
        PanelRysowania dpnl = new PanelRysowania(swiat);
        plansza.add(dpnl);
        
        plansza.addMouseListener (new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        SwingUtilities.invokeLater(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                OknoWyboru asd = new OknoWyboru(e.getX(), e.getY(), swiat);
                                asd.setVisible(true);
                            }
                        });
                        
                        
                    }
                });
        
        JPanel raporter = new JPanel();
        JTextPane raporty = new JTextPane();
        
        raporty.setContentType("text/html");
        String tekst = "Nowa gra";
        for(int i=0; i < swiat.zwrocIleRap() && i < 12;i+=3)
        {
            tekst+=swiat.zwroc_raport(i);
            if(swiat.zwroc_raport(i+1)!=null)
                tekst+=" ------- " + swiat.zwroc_raport(i+1);
        }
        
        raporty.setText(tekst);
        raporty.setEditable(false);
        raporter.add(raporty);
        
        JPanel menu = new JPanel();
        
        JButton klepsydra = new JButton("Klepsydra");
        klepsydra.setToolTipText("Przejdź do następnej tury");
        klepsydra.addActionListener ( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                swiat.wykonajTure('m');
                dpnl.paint(dpnl.getGraphics());
                String tekst = "";
                for(int i=0; i < swiat.zwrocIleRap() ;i+=2)
                {
                    tekst+="<p>"+swiat.zwroc_raport(i);
                    if(i < swiat.zwrocIleRap()-1)
                        tekst+= " ----- "+swiat.zwroc_raport(i+1);
                    tekst +="</p>";
                }
                
                raporty.setText(tekst);
                raporter.paint(raporter.getGraphics());
            }
        });
        
        
        
        
        
        BasicArrowButton gora = new BasicArrowButton(BasicArrowButton.NORTH);
        gora.addActionListener ( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                swiat.wykonajTure('g');
                dpnl.paint(dpnl.getGraphics());
                String tekst = "";
                for(int i=0; i < swiat.zwrocIleRap() ;i+=2)
                {
                    tekst+="<p>"+swiat.zwroc_raport(i);
                    if(i < swiat.zwrocIleRap()-1)
                        tekst+= " ----- "+swiat.zwroc_raport(i+1);
                    tekst +="</p>";
                }
                
                raporty.setText(tekst);
                raporter.paint(raporter.getGraphics());
            }
        });
        gora.setMnemonic(KeyEvent.VK_UP);
        
        BasicArrowButton dol = new BasicArrowButton(BasicArrowButton.SOUTH);
        dol.addActionListener ( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                swiat.wykonajTure('d');
                dpnl.paint(dpnl.getGraphics());
                String tekst = "";
                for(int i=0; i < swiat.zwrocIleRap() ;i+=2)
                {
                    tekst+="<p>"+swiat.zwroc_raport(i);
                    if(i < swiat.zwrocIleRap()-1)
                        tekst+= " ----- "+swiat.zwroc_raport(i+1);
                    tekst +="</p>";
                }
                
                raporty.setText(tekst);
                raporter.paint(raporter.getGraphics());
            }
        });
        dol.setMnemonic(KeyEvent.VK_DOWN);
        
        BasicArrowButton lewo = new BasicArrowButton(BasicArrowButton.WEST);
        lewo.addActionListener ( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                swiat.wykonajTure('l');
                dpnl.paint(dpnl.getGraphics());
                String tekst = "";
                for(int i=0; i < swiat.zwrocIleRap() ;i+=2)
                {
                    tekst+="<p>"+swiat.zwroc_raport(i);
                    if(i < swiat.zwrocIleRap()-1)
                        tekst+= " ----- "+swiat.zwroc_raport(i+1);
                    tekst +="</p>";
                }
                
                raporty.setText(tekst);
                raporter.paint(raporter.getGraphics());
            }
        });
        lewo.setMnemonic(KeyEvent.VK_LEFT);
        
        BasicArrowButton prawo = new BasicArrowButton(BasicArrowButton.EAST);
        prawo.addActionListener ( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                swiat.wykonajTure('p');
                dpnl.paint(dpnl.getGraphics());
                String tekst = "";
                for(int i=0; i < swiat.zwrocIleRap() ;i+=2)
                {
                    tekst+="<p>"+swiat.zwroc_raport(i);
                    if(i < swiat.zwrocIleRap()-1)
                        tekst+= " ----- "+swiat.zwroc_raport(i+1);
                    tekst +="</p>";
                }
                
                raporty.setText(tekst);
                raporter.paint(raporter.getGraphics());
            }
        });
        prawo.setMnemonic(KeyEvent.VK_RIGHT);
        
        JButton niesmiertelnosc = new JButton("Niesmiertelnosc");
        niesmiertelnosc.addActionListener ( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                String tekst ;
                tekst = "";
                swiat.wykonajTure('s');
                dpnl.paint(dpnl.getGraphics());
                for(int i=0; i < swiat.zwrocIleRap() ;i+=2)
                {
                    tekst+="<p>"+swiat.zwroc_raport(i);
                    if(i < swiat.zwrocIleRap()-1)
                        tekst+= " ----- "+swiat.zwroc_raport(i+1);
                    tekst +="</p>";
                }
                raporty.setText(tekst);
                raporter.paint(raporter.getGraphics());
            }
        });
        niesmiertelnosc.setMnemonic(KeyEvent.VK_S);
        
        JButton zapisz = new JButton("Zapisz");
        zapisz.setToolTipText("Zapisz stan gry");
        zapisz.addActionListener ( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                String tekst ;
                
                try
                {
                    swiat.zapisz_gre();
                    tekst = "Zapisano stan gry.";
                }
                catch(FileNotFoundException a)
                {
                    tekst = "Nie udalo sie zapisac stanu gry.";     
                }
                raporty.setText(tekst);
                raporter.paint(raporter.getGraphics());
            }
        });
        
        JButton wczytaj = new JButton("Wczytaj");
        wczytaj.setToolTipText("Wczytaj stan gry");
        wczytaj.addActionListener ( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                String tekst;
                try
                {
                    swiat.wczytaj_gre();
                    tekst ="Wczytano stan gry.";
                    dpnl.paint(dpnl.getGraphics());
                }
                catch(FileNotFoundException a)
                {
                    tekst ="Nie udalo sie wczytac stanu gry";
                }
                raporty.setText(tekst);
                raporter.paint(raporter.getGraphics());
            }
        });
        
        menu.add(klepsydra);
        menu.add(zapisz);
        menu.add(wczytaj);
        podstawa.add(lewo);
        podstawa.add(gora);
        podstawa.add(dol);
        podstawa.add(prawo);
        podstawa.add(niesmiertelnosc);
        podstawa.add(menu);
        podstawa.add(plansza);
        
        podstawa.add(raporter);
        
        
        setTitle("Swat v0.2  Tymoteusz Konkol 160845");
        setSize(500,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    
    public static void main(String[] args) {
        
        
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                Swat swiat = new Swat();
                swiat.setVisible(true);
            }
        });
        //Swat azeroth = new Swat();
        //System.out.println(azeroth.getClass());
       // azeroth.wykonajTure('n');
    }
    
    private class OknoWyboru extends JFrame
    {
        public OknoWyboru(int x, int y, Swat swiat)
        {
            this.dodaj_organizm_po_kliknieciu(x, y, swiat);
        }
        
        public void dodaj_organizm_po_kliknieciu(int x, int y, Swat swiat)
    {
        JPanel panel_do_tworzenia_nowych = new JPanel();
        add(panel_do_tworzenia_nowych);
        
        int pozycja_x = (int)((double)(x-28)/14.5);
        int pozycja_y = (int)((double)(y-28)/14.5);
        
        
        
        //przyciski
        JButton owca = new JButton("Owca");
        owca.addActionListener ( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                swiat.dodaj_mlode(null,'O' , swiat, pozycja_x, pozycja_y);
                dispose();
            }
        });
        panel_do_tworzenia_nowych.add(owca);
        
        JButton antylopa = new JButton("Antylopa");
        antylopa.addActionListener ( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                swiat.dodaj_mlode(null,'A' , swiat, pozycja_x, pozycja_y);
                dispose();
            }
        });
        panel_do_tworzenia_nowych.add(antylopa);
        
        JButton lis = new JButton("Lis");
        lis.addActionListener ( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                swiat.dodaj_mlode(null,'L' , swiat, pozycja_x, pozycja_y);
                dispose();
            }
        });
        panel_do_tworzenia_nowych.add(lis);
        
        JButton wilk = new JButton("Wilk");
        wilk.addActionListener ( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                swiat.dodaj_mlode(null,'W' , swiat, pozycja_x, pozycja_y);
                dispose();
            }
        });
        panel_do_tworzenia_nowych.add(wilk);
        
        JButton zolw = new JButton("Zolw");
        zolw.addActionListener ( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                swiat.dodaj_mlode(null,'Z' , swiat, pozycja_x, pozycja_y);
                dispose();
            }
        });
        panel_do_tworzenia_nowych.add(zolw);
        
        JButton guarana = new JButton("Guarana");
        guarana.addActionListener ( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                swiat.dodaj_mlode(null,'%' , swiat, pozycja_x, pozycja_y);
                dispose();
            }
        });
        panel_do_tworzenia_nowych.add(guarana);
        
        JButton mlecz = new JButton("Mlecz");
        mlecz.addActionListener ( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                swiat.dodaj_mlode(null,'*' , swiat, pozycja_x, pozycja_y);
                dispose();
            }
        });
        panel_do_tworzenia_nowych.add(mlecz);
        
        JButton trawa = new JButton("Trawa");
        trawa.addActionListener ( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                swiat.dodaj_mlode(null,'|' , swiat, pozycja_x, pozycja_y);
                dispose();
            }
        });
        panel_do_tworzenia_nowych.add(trawa);
        
        JButton wilcze_jag = new JButton("Wilcze jagody");
        wilcze_jag.addActionListener ( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                swiat.dodaj_mlode(null,':' , swiat, pozycja_x, pozycja_y);
                dispose();
            }
        });
        panel_do_tworzenia_nowych.add(wilcze_jag);
        
        
        
        //
        setTitle("Wybór zwierza");
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    }
    
    private class PanelRysowania extends JPanel {

    protected Swat swiat;

    public PanelRysowania(Swat swiat)
    {
        super();
        this.swiat = swiat;
    }
    
    public Graphics zwroc_g()
    {
        return this.getGraphics();
    }
        
    public void narysuj(Graphics g) {
        
        Graphics2D grafika = (Graphics2D) g;
        
        int nr_org;
        grafika.setColor(new Color(212, 212, 212));
        for(int i=0; i <= this.swiat.zwroc_szerokosc_swiata();i++)
        {
            
            for(int j=0; j <= this.swiat.zwroc_wysokosc_swiata();j++)
            {
                
                nr_org = this.swiat.sprawdz_czy_jest_miejsce(j, i);
                
                if(nr_org != -1)
                {
                    Organizm aktualny = this.swiat.zwroc_organizm(nr_org);
                    grafika.setColor(aktualny.zwroc_kolor());
                }
                
                    
                grafika.drawRect(15 + 15*j, 15 + 15*i, 15, 15);
                grafika.fillRect(15 + 15*j, 15 + 15*i, 14, 14);
                
                    grafika.setColor(new Color(212, 212, 212));
            }
        }        
    }

     @Override
     public Dimension getPreferredSize() {
        return new Dimension(400, 330); 
    }
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        narysuj(g);
    }
}

}