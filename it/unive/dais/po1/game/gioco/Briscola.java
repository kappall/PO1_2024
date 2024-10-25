package it.unive.dais.po1.game.gioco;

import it.unive.dais.po1.game.carte.Card;
import it.unive.dais.po1.game.carte.CarteATerra;
import it.unive.dais.po1.game.carte.CarteRaccolte;
import it.unive.dais.po1.game.carte.Mazzo;
import it.unive.dais.po1.game.giocatori.Giocatore;

abstract public class Briscola {

    protected Card briscola;// = new it.dais.unive.po1.carte.carte.Seme(1);

    protected Mazzo mazzo;

     public Briscola() {
        mazzo = new Mazzo();
         //@invariant mazzo.mazzo!=null
         //@invariant mazzo.cartaCorrente>=0 mazzo.cartaCorrente<mazzo.mazzo.length
     }


     abstract public Giocatore partita();

     protected final void distribuisciCarteInit(Mazzo mazzo, int n, int first, Giocatore... giocatori){
/*
         for(int g = first; g < giocatori; g = (g+1)%(n+1)){
             for(int i = 0; i < n; ++i)
                 g.giveCard(mazzo.pop());
         }*/

     }

     public boolean prende(Card first, Card second) {
         if(first.isErrata() || second.isErrata()) {
             System.err.println("Carta errata");
             return false;
         }
         if(first.stessoSeme(briscola) && ! second.stessoSeme(briscola))
             return true;
         if(first.stessoSeme(second))
             return maggiore(first.getValue(), second.getValue());
         else return false;
     }

    public boolean maggiore(Card first, Card second) {
        if(first.isErrata() || second.isErrata()) {
            System.err.println("Carta errata");
            return false;
        }

        if(first.stessoSeme(briscola) && ! second.stessoSeme(briscola))
            return true;
        if(! first.stessoSeme(briscola) && second.stessoSeme(briscola))
            return false;
        if(! first.stessoSeme(second) && ! second.stessoSeme(briscola))
            return true;
        if(maggiore(first.getValue(), second.getValue()))
            return true;
        else return false;
    }

    static private boolean maggiore(int f1, int f2) {
        //assumo valore!=valore1
        switch(f1) {
            case 1 : return true;
            case 3:
                switch(f2) {
                    case 1: return false;
                    default: return true;
                }
            default: switch(f2) {
                case 1: return false;
                case 3: return false;
                default: return f1 > f2;
            }
        }
    }

    public static int contaPunti(CarteRaccolte m) {
        int puntiTotali = 0;
        for(int i = 0; i < 40; i++) {
            Card c = m.getCard(i);
            if(c != null) {
                switch (c.getValue()) {
                    case 1:
                        puntiTotali += 11;
                        break;
                    case 3:
                        puntiTotali += 10;
                        break;
                    case 10:
                        puntiTotali += 4;
                        break;
                    case 9:
                        puntiTotali += 3;
                        break;
                    case 8:
                        puntiTotali += 2;
                        break;
                    default:
                }
            }
        }
        return puntiTotali;
    }

    protected boolean getInitialCards(Giocatore g1, boolean accepted) {
        accepted = accepted && g1.giveCard(mazzo.pop());
        accepted = accepted && g1.giveCard(mazzo.pop());
        accepted = accepted && g1.giveCard(mazzo.pop());
        return accepted;
    }


}
