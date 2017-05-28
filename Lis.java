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
public class Lis extends Zwierze{
    
       
    public Lis(Swat swiat)
    {
       super(swiat);
       
       this.inicjatywa = 7;
       this.sila = 3;
       this.wyglad = 'L';
       
       this.kolor = Color.RED;
       
       this.swiat.zwieksz_ilosc_organizmow();
       
    }
    
    public Lis(Swat swiat, int x, int y)
    {
        super(swiat);
        
        this.inicjatywa = 7;
        this.sila = 3;
        this.wyglad = 'L';
        
        this.kolor = Color.RED;
        
        this.polozenie_x = x;
        this.polozenie_y = y;        
    }
    
    public Lis(Swat swiat, int x, int y, int sila, int wiek)
    {
        super(swiat, sila);
        
        this.inicjatywa = 7;
        this.sila = sila;
        this.wyglad = 'L';
        this.wiek = wiek;
        
        this.kolor = Color.RED;
        
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
    public void akcja(int zasieg)
    {
        
        boolean czy_wykonano_ruch=false;
        int czy_kolizja;
        
        int ile_odjac;
        ile_odjac = 1;
        zasieg = 3;        
        
        do
            {                               //2
            int zmienna_na_x=losowa.nextInt(zasieg)- ile_odjac;
            int zmienna_na_y=losowa.nextInt(zasieg)- ile_odjac;
        
            if( zmienna_na_x !=0 || zmienna_na_y != 0)
            {
                if(this.polozenie_x + zmienna_na_x >0 && this.polozenie_x + zmienna_na_x <= this.swiat.zwroc_szerokosc_swiata()
                        && this.polozenie_y + zmienna_na_y >0 && this.polozenie_y + zmienna_na_y <= this.swiat.zwroc_wysokosc_swiata())
                {
                    czy_kolizja = this.swiat.sprawdz_czy_jest_miejsce(this.polozenie_x + zmienna_na_x, this.polozenie_y+zmienna_na_y);
                    //funkcja sprawdz_czy_jest_miejsce zwraca -1 jezeli miejsce jest wolne
                    if(czy_kolizja != -1)
                    {
                        Organizm obcy = this.swiat.zwroc_organizm(czy_kolizja);
                        if(this.sila > obcy.zwroc_sile())
                        {
                            this.kolizja(czy_kolizja);
                            czy_wykonano_ruch=true;
                        }else
                            czy_wykonano_ruch=false;
                        
                    }
                    else
                    {
                        this.polozenie_x += zmienna_na_x;
                        this.polozenie_y += zmienna_na_y;
                        czy_wykonano_ruch = true;
                    }
                }
            }
        }while(czy_wykonano_ruch==false);
    }
    
}
