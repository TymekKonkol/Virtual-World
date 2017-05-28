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
public class Mlecz extends Roslina{
    public Mlecz(Swat swiat)
    {
       super(swiat);
        
       this.wyglad = '*';
       
       this.kolor = Color.YELLOW;
       
       this.swiat.zwieksz_ilosc_organizmow();
       
    }
    
    public Mlecz(Swat swiat, int x, int y)
    {
        super(swiat);
        
        this.wyglad = '*';
        
        this.kolor = Color.YELLOW;
        
        this.polozenie_x = x;
        this.polozenie_y = y;        
    }
    
    public Mlecz(Swat swiat, int x, int y, int sila, int wiek)
    {
        super(swiat, sila);
        
        this.sila = sila;
        this.wyglad = '*';
        this.wiek = wiek;
        
        this.kolor = Color.YELLOW;
        
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
