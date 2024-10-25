package it.unive.dais.po1.dispatching;

public class A {

    void foo(A p){
        System.out.println("A");
    }
    void foo(B p){
        System.out.println("B");
    }

    void foo11(){// visibilità standard/packagge
        System.out.println("Afoo11");
    }

    A foo12(){
        System.out.println("foo12");
        return this;
    }

    A foo13(A p){
        System.out.println("Aafoo13");
        return p;
    }

    void foo(A a, B b){
        System.out.println("A, B");
    }
}
