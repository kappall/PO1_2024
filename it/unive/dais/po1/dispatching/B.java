package it.unive.dais.po1.dispatching;

public class B extends A{
    void foo(B a, B b){
        System.out.println("A, A");
    }

    @Override
    public void foo11() { // visibiltà può essere uguale o più grande
        System.out.println("Bfoo11");
    }

    //è posssibile cambiare il tipo di ritorno solo se il nuovo tipo è una sottoclasse, può spstuiyirie
    // il tipo di ritorno precedente
    @Override
    B foo12() {
        System.out.println("Bfoo12");
        return this;
    }

    @Override
    A foo13(A p) { // non mi è possibile overridere ricevendo un tupo di paramtero diverso
        return super.foo13(p);
    }
}
