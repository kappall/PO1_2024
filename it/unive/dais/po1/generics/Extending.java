package it.unive.dais.po1.generics;

public class Extending<T extends Empty> {// anche ai generics è possibile estendere calssi e interfacce
    T el;
    public Extending(T el) {
        this.el = el;
    }

    public T getEl() {
        return el;
    }
}
