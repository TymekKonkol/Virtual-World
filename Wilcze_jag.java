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
public class Wilcze_jag extends Roslina{
    public Wilcze_jag(Swat swiat)
    {
       super(swiat);
       this.sila = 99;
       this.wyglad = ':';
       
       this.kolor = Color.magenta;
       
       this.swiat.zwieksz_ilosc_organizmow();
       
    }
    
    public Wilcze_jag(Swat swiat, int x, int y)
    {
        super(swiat);
        
        this.wyglad = ':';
        this.sila = 99;
        
        this.kolor = Color.magenta;
        
        this.polozenie_x = x;
        this.polozenie_y = y;        
    }
    
    public Wilcze_jag(Swat swiat, int x, int y, int sila, int wiek)
    {
        super(swiat, sila);
        
        this.sila = sila;
        this.wyglad = ':';
        this.wiek = wiek;
        
        this.kolor = Color.magenta;
        
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
