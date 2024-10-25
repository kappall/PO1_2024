package it.unive.dais.po1.dispatching;

public class Test {
    public static void main(String[] args) {
        A a1 = new A();
        B b1 = new B();

        a1.foo(a1); // A
        a1.foo(b1); // B
        //esssendo che in A è presente un metodo che acceatta come parametro B allora a runtime vengono distinti

        A ab = new B();
        a1.foo(ab); // A
        // In questo caso la scleta dei progettattori di Java è di accetare in tipo statico per i parametri
        b1.foo(b1, b1); // A, A

        A c = new B();
        c.foo12(); //Bfoo12
    }
}
