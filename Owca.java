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
public class Owca extends Zwierze{
    public Owca(Swat swiat)
    {
       super(swiat);
       
       this.inicjatywa = 4;
       this.sila = 4;
       this.wyglad = 'O';
       
       this.kolor = Color.WHITE;
       
       this.swiat.zwieksz_ilosc_organizmow();
    }
    
    public Owca(Swat swiat, int x, int y)
    {
        super(swiat);
        
        this.inicjatywa = 4;
        this.sila = 4;
        this.wyglad = 'O';
        
        this.kolor = Color.WHITE;
        
        this.polozenie_x = x;
        this.polozenie_y = y;        
    }
    
    public Owca(Swat swiat, int x, int y, int sila, int wiek)
    {
        super(swiat, sila);
        this.inicjatywa = 4;
        this.sila = sila;
        this.wyglad = 'O';
        this.wiek = wiek;
        
        this.kolor = Color.WHITE;
        
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
