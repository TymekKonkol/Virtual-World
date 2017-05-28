/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swat;


import java.util.Random;
import java.awt.Color;
/**
 *
 * @author Tymoteusz
 */
public abstract class Organizm {
    protected int sila;
    protected int inicjatywa;
    protected int polozenie_x;
    protected int polozenie_y;
    protected Swat swiat;
    protected int wiek;
    protected Color kolor;
    
    protected Random losowa = new Random();
    
    protected boolean czy_sie_porusza;
    protected char wyglad;
    
    public Organizm(Swat swiat)
    {   
        this.swiat=swiat;
	czy_sie_porusza = true;
        this.wiek = swiat.zwrocTure();
        
        int czy_mozna_zamiescic_organizm;
        
        do
        {
            this.polozenie_x=losowa.nextInt(this.swiat.zwroc_szerokosc_swiata());
            this.polozenie_y=losowa.nextInt(this.swiat.zwroc_wysokosc_swiata());
            
            czy_mozna_zamiescic_organizm=this.swiat.sprawdz_czy_jest_miejsce(this.polozenie_x,this.polozenie_y);
            
        }while(czy_mozna_zamiescic_organizm != -1);
        
        
    }
    
    public Organizm(Swat swiat, int sila)
    {   
        this.swiat=swiat;
	czy_sie_porusza = true;
    }
    
    public abstract void akcja(int zasieg);
    public abstract void kolizja( int nr_organizmu);
    public abstract void rysowanie();
    
    public void akcja(char a)
    {
        
    }
    
    public int zwrocX()
    {
        return this.polozenie_x;
    }
    
    public int zwrocY()
    {
        return this.polozenie_y;
    }
    
    public int zwroc_sile()
    {
        return this.sila;
    }
    
    public int zwroc_wiek()
    {
        return this.wiek;
    }
    
    public int zwroc_inicjatywe()
    {
        return this.inicjatywa;
    }
    
    public boolean zwroc_mozliwosc_poruszania()
    {
        return this.czy_sie_porusza;
    }
    
    public void zapisz_do_rejestru(String informacja)
    {
        this.swiat.zapisz_do_rejestru(this.wyglad + informacja);
    }
    
    public char zwroc_wyglad()
    {
        return this.wyglad;
    }
    public int zwroc_niesmiertelnosc()
    {
        return 0;
    }
    
    public void zmien_mozliwosc_poruszania(boolean na_co)
    {
        this.czy_sie_porusza = na_co;
    }
    
    public void zmien_x_y(int x, int y)
    {
        this.polozenie_x += x;
        this.polozenie_y += y;
    }
    
    @Override
    public String toString()
    {
        String info = this.wyglad + " " + this.polozenie_x + " " + this.polozenie_y  
                + " " + this.sila + " " + this.wiek;
        return info;
    }
        
    public Color zwroc_kolor()
    {
        return this.kolor;
    }
}

