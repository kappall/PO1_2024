package it.unive.dais.po1.game.gioco;

import it.unive.dais.po1.game.carte.Card;
import it.unive.dais.po1.game.carte.CarteATerra;
// import it.unive.dais.po1.game.carte.ListaCarte;
import it.unive.dais.po1.game.giocatori.Giocatore;

public class BriscolaAQuattro extends Briscola {
    private Giocatore[] g;
    private int primoDIMano;

    public BriscolaAQuattro(Giocatore g1, Giocatore g2, Giocatore g3, Giocatore g4) {
        if(g1==g2 || g1 == g3 || g1 == g4
                || g2 == g3 || g2 == g4
                || g3==g4) {
            throw new IllegalArgumentException("Due o più giocatore sono la stessa istanza di un giocatore");
        }
        primoDIMano = 0;
        g = new Giocatore[4];
        this.g[0] = g1;
        this.g[1] = g2;
        this.g[2] = g3;
        this.g[3] = g4;
    }
/*
    private static boolean add(ListaCarte l, Card c) {
        if(c==null) return false;
        else { l.add(c); return true; }
    }
*/

    public Giocatore partita() {
        TavoloQuattroGiocatori tavolo = new TavoloQuattroGiocatori(g[0], g[1], g[2], g[3]);
        mazzo.shuffle();

        distribuisciCarteInit(mazzo,3,g[0],g[1],g[2],g[3]);

        briscola = mazzo.pop();
        boolean mazzoIsEmpty = false;
        while(! mazzoIsEmpty) {
            Giocatore vincitore = giocaMano(tavolo);
            tavolo.get(0).giveCard(mazzo.pop());
            tavolo.get(1).giveCard(mazzo.pop());
            tavolo.get(2).giveCard(mazzo.pop());
            Card next = mazzo.pop();
            if(next!=null)
                tavolo.get(3).giveCard(next);
            else {
                mazzoIsEmpty = true;
                tavolo.get(3).giveCard(briscola);
            }
        }
        for(int i = 0; i < 3; i++)
            giocaMano(tavolo);
        int punteggiog1 = contaPunti(g[0].getCarteVinte()) + contaPunti(g[2].getCarteVinte());
        int punteggiog2 = contaPunti(g[1].getCarteVinte()) + contaPunti(g[3].getCarteVinte());
        g[0].dropAllCards(3, 40);
        g[1].dropAllCards(3, 40);
        g[2].dropAllCards(3, 40);
        g[3].dropAllCards(3, 40);
        if(punteggiog1 > punteggiog2) {
            System.out.println("Ha vinto la squadra 1");
            return g[0];
        }
        else if(punteggiog1 < punteggiog2) {
            System.out.println("Ha vinto la squadra 2");
            return g[1];
        }
        else {
            System.out.println("Patta");
            return null;
        }
    }

    private Giocatore giocaMano(TavoloQuattroGiocatori tavolo) {
        int turn = primoDIMano;

        CarteATerra c = new CarteATerra(4);
        Card prima = g[turn].getCard(c, this);
        c.add(prima);
        Card seconda = g[turn+1%4].getCard(c, this);
        c.add(seconda);
        Card terza = g[turn+2%4].getCard(c, this);
        c.add(terza);
        Card quarta = g[turn+3%4].getCard(c, this);
        c.add(quarta);
        if(maggiore(prima, seconda) && maggiore(prima, quarta)) {
            g[turn%4].takeCards(c);
            if(prende(terza, prima))
                tavolo.setPrimoDiMano(2);
            //else
            //    tavolo.setPrimoDiMano(0);
            return g[turn%4];
        }
        else if(prende(seconda, prima) && maggiore(seconda, terza)){
            g[(turn+1)%4].takeCards(c);
            tavolo.setPrimoDiMano(1);
            return g[(turn+1)%4];
        }
        else if( (  (prende(terza, seconda) && prende(seconda,prima)) || prende(terza, prima))
                && maggiore(terza, quarta)){
            g[(turn+2)%4].takeCards(c);
            tavolo.setPrimoDiMano(2);
            return g[(turn+2)%4];
        }
        else {
            g[(turn+3)%4].takeCards(c);
            tavolo.setPrimoDiMano(3);
            return g[(turn+3)%4];
        }
    }
}
