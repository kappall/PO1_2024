package it.unive.dais.po1.game.carte;

public interface Addable {
    public Card[] carte = new Card[40];
    public boolean add(Card c);
    // da java-8 si puà implementare un metodo di un interafccia dichiarandolo defaultpp
    default  Card get(int index){
        return  carte[index];
    }
}
