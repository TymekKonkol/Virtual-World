/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swat;

import java.awt.Color;

/**
 *
 * @author Tymoteusz
 */
public class Antylopa extends Zwierze{
    
    public Antylopa(Swat swiat)
    {
       super(swiat);
        
       this.inicjatywa = 4;
       this.sila = 4;
       this.wyglad = 'A';
       
       
       this.kolor = Color.LIGHT_GRAY;
       
       this.swiat.zwieksz_ilosc_organizmow();
       
    }
    
    public Antylopa(Swat swiat, int x, int y)
    {
        super(swiat);
        
        this.inicjatywa = 4;
        this.sila = 4;
        this.wyglad = 'A';
        
        
        this.kolor = Color.LIGHT_GRAY;
        
        this.polozenie_x = x;
        this.polozenie_y = y;        
    }
    
    public Antylopa(Swat swiat, int x, int y, int sila, int wiek)
    {
        super(swiat, sila);
        this.inicjatywa = 4;
        this.sila = sila;
        this.wyglad = 'A';
        this.wiek = wiek;
        
        this.kolor = Color.LIGHT_GRAY;
        
        this.polozenie_x = x;
        this.polozenie_y = y;   
        this.swiat.zwieksz_ilosc_organizmow();
    }
    
    @Override
    public void rysowanie()
    {
        System.out.println(wyglad);
    }
    @Override
    public void akcja( int zasieg)
    {
        zasieg = 4;
        super.akcja(zasieg);
    }
    
}
