/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swat;

import java.awt.Color;
import java.util.Random;
/**
 *
 * @author Tymoteusz
 */
public class Zolw extends Zwierze{
    public Zolw(Swat swiat)
    {
       super(swiat);
       
       this.inicjatywa = 1;
       this.sila = 2;
       this.wyglad = 'Z';
       
       this.kolor = Color.BLUE;
       
       this.swiat.zwieksz_ilosc_organizmow();
    }
    
    public Zolw(Swat swiat, int x, int y)
    {
        super(swiat);
        
        this.inicjatywa = 1;
        this.sila = 2;
        this.wyglad = 'Z';
        
        this.kolor = Color.BLUE;
        
        this.polozenie_x = x;
        this.polozenie_y = y;        
    }
    
    public Zolw(Swat swiat, int x, int y, int sila, int wiek)
    {
        super(swiat, sila);
        
        this.inicjatywa = 1;
        this.sila = sila;
        this.wyglad = 'Z';
        this.wiek = wiek;
        
        this.kolor = Color.BLUE;
        
        this.polozenie_x = x;
        this.polozenie_y = y;  
        
        this.swiat.zwieksz_ilosc_organizmow();
    }
    
    @Override
    public void akcja(int zasieg)
    {
         Random losowa = new Random();
         int czy_porusza_sie_zolw = losowa.nextInt(101);
         if(czy_porusza_sie_zolw > 75)
             super.akcja(3);
    }
    
    @Override
    public void rysowanie()
    {
        System.out.println(wyglad);
    }
}
