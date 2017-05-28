/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swat;

/**
 *
 * @author Tymoteusz
 */
public abstract class Roslina extends Organizm {
    private final int LOSOWOSC_ROZROSTU = 85;
    
    public Roslina(Swat swiat)
    {
        super(swiat);
        this.inicjatywa = 0;
        this.sila = 0;
    }
    
    public Roslina(Swat swiat, int sila)
    {
        super(swiat, sila);
        this.inicjatywa = 0;
        this.sila = 0;
    }
    
    @Override
    public void akcja(int zasieg)
    {
        int czy_rozrasta = 0;
        int ile_prob = 1;
        if(this instanceof Mlecz)
            ile_prob = 3;
        
        for(int i=0; i<ile_prob; i++)
        {
            czy_rozrasta = losowa.nextInt(101);
            if(czy_rozrasta > LOSOWOSC_ROZROSTU)
                break;
        }
            
        
        if(czy_rozrasta > LOSOWOSC_ROZROSTU)
        {
            
            boolean czy_sie_rozsial=false;
            int zmiana_pozycji_x = 0;
            int zmiana_pozycji_y = 1;
        
            for(int i=0; i<8; i++)
            {
                switch(i)
                {
                    case 0: zmiana_pozycji_x = -1; zmiana_pozycji_y = -1; break;
                    case 1: zmiana_pozycji_x = -1; zmiana_pozycji_y = 0; break;
                    case 2: zmiana_pozycji_x = -1; zmiana_pozycji_y = 1; break;
                    case 3: zmiana_pozycji_x = 0; zmiana_pozycji_y = 1; break;
                    case 4: zmiana_pozycji_x = 1; zmiana_pozycji_y = 1; break;
                    case 5: zmiana_pozycji_x = 1; zmiana_pozycji_y = 0; break;
                    case 6: zmiana_pozycji_x = 1; zmiana_pozycji_y = -1; break;
                    case 7: zmiana_pozycji_x = 0; zmiana_pozycji_y = -1; break;
                    default: break;
                }
            
                if(this.polozenie_x + zmiana_pozycji_x >=0 && this.polozenie_x + zmiana_pozycji_x <= this.swiat.zwroc_szerokosc_swiata()
                            && this.polozenie_y + zmiana_pozycji_y >=0 && this.polozenie_y + zmiana_pozycji_y <= this.swiat.zwroc_wysokosc_swiata())
                {
                    czy_sie_rozsial = this.swiat.dodaj_mlode( this , this.wyglad, this.swiat, this.polozenie_x + zmiana_pozycji_x, this.polozenie_y + zmiana_pozycji_y);
                
            }
            
            if(czy_sie_rozsial)
                break;
        }
            
            
        }
    }
    @Override
    public void kolizja(int nr_organizmu)
    {
        
    }
    
}
