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
public abstract class Zwierze extends Organizm {
    
    
    public Zwierze(Swat swiat)
    {
        super(swiat);
    }
    
    public Zwierze(Swat swiat,int sila)
    {
        super(swiat, sila);
    }
    
    @Override
    public void akcja(int zasieg)
    {
        
        if(this.czy_sie_porusza)
        {
            int zmienna_na_x;
            int zmienna_na_y;
                    
            boolean czy_wykonano_ruch=false;
            int czy_kolizja;
        
            int ile_odjac;
            if(zasieg == 4)
                ile_odjac = 2;
            else
                ile_odjac = 1;
        
            do
                {    
                   
                zmienna_na_x=losowa.nextInt(zasieg)- ile_odjac;
                zmienna_na_y=losowa.nextInt(zasieg)- ile_odjac;
                
                
                if( zmienna_na_x !=0 || zmienna_na_y != 0)
                {
                    
                    if(this.polozenie_x + zmienna_na_x >=0 && this.polozenie_x + zmienna_na_x < swiat.zwroc_szerokosc_swiata()-1
                            && this.polozenie_y + zmienna_na_y >=0 && this.polozenie_y + zmienna_na_y < this.swiat.zwroc_wysokosc_swiata()-1)
                    {
                        
                        
                        czy_kolizja = this.swiat.sprawdz_czy_jest_miejsce(this.polozenie_x + zmienna_na_x, this.polozenie_y+zmienna_na_y);
                        //funkcja sprawdz_czy_jest_miejsce zwraca -1 jezeli miejsce jest wolne
                        if(czy_kolizja != -1)
                        {
                            this.kolizja(czy_kolizja);
                            czy_wykonano_ruch=true;
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
    
    
    
    @Override
    public void kolizja( int nr_organizmu)
    {
        Organizm obcy = this.swiat.zwroc_organizm(nr_organizmu);
        int nr_tego = this.swiat.sprawdz_czy_jest_miejsce(this.polozenie_x , this.polozenie_y);
        if( obcy.getClass() == this.getClass() )
        {
            this.rozmnazanie(obcy);
        }
        else
        {
            boolean uciekl=false;
            if(obcy instanceof Antylopa)
            {
                if(losowa.nextInt(101)>50)
                {
                    uciekl=true;
                }
            }
            
            if(obcy instanceof Zolw && this.sila < 5)
            {
                this.zapisz_do_rejestru(" nie udalo sie zaatakowac " + obcy.zwroc_wyglad());
            }
            else if(uciekl || ( obcy instanceof Czlowiek && obcy.zwroc_niesmiertelnosc() != 0))
            {
                this.ucieczka(obcy);
            }
            else if(uciekl==false)
            {
            
                if( ( this.sila > obcy.zwroc_sile() )|| 
                        ( this.sila == obcy.zwroc_sile() && this.wiek > obcy.zwroc_wiek() ) || 
                        ( this.sila == obcy.zwroc_sile() && this.wiek == obcy.zwroc_wiek() ) )
                {
                    if(obcy instanceof Guarana)
                    {
                        this.sila +=3;
                        this.polozenie_x = obcy.zwrocX();
                        this.polozenie_y = obcy.zwrocY();
                    }
                    else if( obcy instanceof Wilcze_jag)
                    {
                        this.zapisz_do_rejestru(" zjadł wilcze jagody i zdechł");
                        this.swiat.usuwanie_organizmu(nr_tego);
                    }
                    else
                    {
                        this.zapisz_do_rejestru(" zjadł " + obcy.zwroc_wyglad());
                        this.polozenie_x = obcy.zwrocX();
                        this.polozenie_y = obcy.zwrocY();
                    }
                    this.swiat.usuwanie_organizmu(nr_organizmu);
                }
                else
                {
                    if(obcy instanceof Wilcze_jag)
                    {
                        this.zapisz_do_rejestru(" zjadl wilcze jagody i zdechł");
                        this.swiat.usuwanie_organizmu(nr_organizmu);
                        this.swiat.usuwanie_organizmu(nr_tego);
                    }
                    else
                    {
                        if(this instanceof Czlowiek && this.zwroc_niesmiertelnosc() !=0)
                        {
                            this.zapisz_do_rejestru(" uciekł przed " + obcy.zwroc_wyglad());
                            this.ucieczka(obcy);
                        }
                        else
                        {
                            this.zapisz_do_rejestru(" zostal zjedzony przez " + obcy.zwroc_wyglad());
                            this.swiat.usuwanie_organizmu(nr_tego);
                        }
                    
                    }
                }
            }
        }
    }
    
    private void rozmnazanie(Organizm obcy)
    {
        boolean czy_sie_rozmnozyl=false;
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
            czy_sie_rozmnozyl = this.swiat.dodaj_mlode( obcy, this.wyglad, this.swiat, this.polozenie_x + zmiana_pozycji_x, this.polozenie_y + zmiana_pozycji_y);
            
            if(czy_sie_rozmnozyl)
                break;
        }
    }
    
    private void ucieczka(Organizm obcy)
    {
        boolean czy_jest_zajete_miejsce = false;
        int zmiana_pozycji_x = 0;
        int zmiana_pozycji_y = 1;
        int pozycja;
        int pozycja_mlode;
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
            
            if(this.polozenie_x + zmiana_pozycji_x >0 && this.polozenie_x + zmiana_pozycji_x <= this.swiat.zwroc_szerokosc_swiata()
                        && this.polozenie_y + zmiana_pozycji_y >0 && this.polozenie_y + zmiana_pozycji_y <= this.swiat.zwroc_wysokosc_swiata())
            {
                
                pozycja = this.swiat.sprawdz_czy_jest_miejsce(this.polozenie_x + zmiana_pozycji_x, this.polozenie_y + zmiana_pozycji_y);
                pozycja_mlode = this.swiat.sprawdz_cz_nie_ma_mlodego(this.polozenie_x + zmiana_pozycji_x, this.polozenie_y + zmiana_pozycji_y);
                
                if(pozycja != -1 && pozycja_mlode != -1)
                {
                    czy_jest_zajete_miejsce = true;
                }
            }
            else
                czy_jest_zajete_miejsce = true;
            
            if(czy_jest_zajete_miejsce == false)
            {
                obcy.zmien_x_y(zmiana_pozycji_x, zmiana_pozycji_y);
                
                this.polozenie_x = obcy.zwrocX();
                this.polozenie_y = obcy.zwrocY();
            }
        }
    }
    
    
    
}
