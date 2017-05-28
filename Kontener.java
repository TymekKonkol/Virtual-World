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
public class Kontener {
    private int ilosc_org;
    private Organizm[] organizmy;
    
    private static final int WYSOKOSC_SWIATA = 20;
    private static final int SZEROKOSC_SWIATA = 20;
    
    public Kontener()
    {
        this.ilosc_org = 0;
        this.organizmy = new Organizm[SZEROKOSC_SWIATA*WYSOKOSC_SWIATA];
    }
    
    public Organizm zwroc_mlode(int i)
    {
        return this.organizmy[i];
    }
    
    public int zwroc_ile()
    {
        return this.ilosc_org;
    }
    
    public int sprawdz_czy_jest_miejsce(int x, int y)
    {
        
        if(this.ilosc_org > 0)
            
        for(int i=0;i<this.ilosc_org;i++)
        {
            if(x==this.organizmy[i].zwrocX() && y == this.organizmy[i].zwrocY())
            {
                return i;
            }
        }
        return -1;
    }
    
    public boolean dodaj_mlode(Organizm obcy, char wyglad, Swat swiat, int pozycja_x, int pozycja_y)
    {
        if(this.sprawdz_czy_jest_miejsce(pozycja_x, pozycja_y) == -1)
        {
            if(obcy!=null)
                obcy.zmien_mozliwosc_poruszania(false);
            this.ilosc_org++;
            switch(wyglad)
            {
                case 'O': this.organizmy[this.ilosc_org-1]= new Owca(swiat, pozycja_x, pozycja_y);break;
                case 'L': this.organizmy[this.ilosc_org-1]= new Lis(swiat, pozycja_x, pozycja_y);break;
                case 'W': this.organizmy[this.ilosc_org-1]= new Wilk(swiat, pozycja_x, pozycja_y);break;
                case 'Z': this.organizmy[this.ilosc_org-1]= new Zolw(swiat, pozycja_x, pozycja_y);break;
                case 'A': this.organizmy[this.ilosc_org-1]= new Antylopa(swiat, pozycja_x, pozycja_y);break;
                
                case '%': this.organizmy[this.ilosc_org-1]= new Guarana(swiat, pozycja_x, pozycja_y);break;
                case '|': this.organizmy[this.ilosc_org-1]= new Trawa(swiat, pozycja_x, pozycja_y);break;
                case ':': this.organizmy[this.ilosc_org-1]= new Wilcze_jag(swiat, pozycja_x, pozycja_y);break;
                case '*': this.organizmy[this.ilosc_org-1]= new Mlecz(swiat, pozycja_x, pozycja_y);break;
                default: break;
            }
            swiat.zapisz_do_rejestru(wyglad + " rozmnozyl sie!");
            return true;
        }
        return false;
    }
    
    public void usuwanie()
    {
        for(int i=0; i<this.ilosc_org;i++)
        {
            this.organizmy[i]=null;
        }
        this.ilosc_org = 0;
    }
}
