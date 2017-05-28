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
public class Wilk extends Zwierze{
    public Wilk(Swat swiat)
    {
       super(swiat);
       
       this.inicjatywa = 5;
       this.sila = 9;
       this.wyglad = 'W';
       
       this.kolor = Color.BLACK;
       
       this.swiat.zwieksz_ilosc_organizmow();
    }
    
    public Wilk(Swat swiat, int x, int y)
    {
        super(swiat);
        
        this.inicjatywa = 5;
        this.sila = 9;
        this.wyglad = 'W';
        
        this.kolor = Color.BLACK;
        
        this.polozenie_x = x;
        this.polozenie_y = y;        
    }
    
    public Wilk(Swat swiat, int x, int y, int sila, int wiek)
    {
        super(swiat, sila);
        
        this.inicjatywa = 5;
        this.sila = sila;
        this.wyglad = 'W';
        this.wiek = wiek;
        
        this.kolor = Color.BLACK;
        
        this.polozenie_x = x;
        this.polozenie_y = y; 
        
        this.swiat.zwieksz_ilosc_organizmow();
    }
    
    @Override
    public void rysowanie()
    {
        System.out.println(wyglad);
    }
}
