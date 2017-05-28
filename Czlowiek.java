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
public class Czlowiek extends Zwierze {
    
    private int czy_mozna_wlaczyc_buff;
    private int niesmiertelnosc;
    
    public Czlowiek(Swat swiat)
    {
       super(swiat);
       this.czy_mozna_wlaczyc_buff = 5;
       this.niesmiertelnosc = 0;
       
       this.inicjatywa = 4;
       this.sila = 5;
       this.wyglad = 'C';
       
       this.kolor = Color.PINK;
       
       this.swiat.zwieksz_ilosc_organizmow();
       
    }
    
    public Czlowiek(Swat swiat, int x, int y, int sila, int wiek)
    {
        super(swiat, sila);
        
        this.czy_mozna_wlaczyc_buff = 5;
        this.niesmiertelnosc = 0;
        
        this.inicjatywa = 4;
        this.sila = sila;
        this.wyglad = 'C';
        this.wiek = wiek;
        
        this.kolor = Color.PINK;
        
        this.polozenie_x = x;
        this.polozenie_y = y;
        this.swiat.zwieksz_ilosc_organizmow();        
    }
    
    @Override
    public void akcja(int zasieg)
    {
        
    }
    
    @Override
    public void akcja(char znak)
    {
        int pozycja_obcego;
        if(this.niesmiertelnosc == 0 && this.czy_mozna_wlaczyc_buff <6)
            this.czy_mozna_wlaczyc_buff++;
        
        if(this.niesmiertelnosc > 0)
            this.niesmiertelnosc--;
        
        switch(znak)
        {
            case 'l':
            {
                if(this.polozenie_x > 0)
                        {
                            pozycja_obcego = this.swiat.sprawdz_czy_jest_miejsce(this.polozenie_x-1, this.polozenie_y);
                            if(pozycja_obcego != -1)
                                this.kolizja(pozycja_obcego);
                            else
                                this.polozenie_x--;
                        }
                break;
            }
            case 'p':
            {
                if(this.polozenie_x < this.swiat.zwroc_szerokosc_swiata())
                        {
                            pozycja_obcego = this.swiat.sprawdz_czy_jest_miejsce(this.polozenie_x+1, this.polozenie_y);
                            if(pozycja_obcego != -1)
                                this.kolizja(pozycja_obcego);
                            else
                                this.polozenie_x++;
                        }
                break;
            }
            case 'd':
            {
                if(this.polozenie_y < this.swiat.zwroc_wysokosc_swiata())
                        {
                            pozycja_obcego = this.swiat.sprawdz_czy_jest_miejsce(this.polozenie_x, this.polozenie_y+1);
                            if(pozycja_obcego != -1)
                                this.kolizja(pozycja_obcego);
                            else
                                this.polozenie_y++;
                        }
                break;
            }
            case 'g':
            {
                if(this.polozenie_y > 0)
                        {
                            pozycja_obcego = this.swiat.sprawdz_czy_jest_miejsce(this.polozenie_x, this.polozenie_y-1);
                            if(pozycja_obcego != -1)
                                this.kolizja(pozycja_obcego);
                            else
                                this.polozenie_y--;
                        }
                break;
            }
            case 's':
            {
                if(this.czy_mozna_wlaczyc_buff >= 5)
                {
                    this.niesmiertelnosc = 5;
                    this.czy_mozna_wlaczyc_buff = 0;
                    this.zapisz_do_rejestru(" wlacza niesmiertelnosc.");
                }
            }
            break;
        }
    }
    
    @Override
    public void rysowanie()
    {
        System.out.println(wyglad);
    }
    
    @Override
    public int zwroc_niesmiertelnosc()
    {
        return this.niesmiertelnosc;
    }
}
