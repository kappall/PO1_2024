package it.unive.dais.po1.generics;

public class C<N> implements Empty {
    private N info;

    public C(N info) {
        this.info = info;
    }

    public N getInfo() {
        return info;
    }


    static void print(int n){
        System.out.println(n+ " is integer");
    }

    static void print(char n){
        System.out.println(n+ " is char");
    }

    static void print(Types n){
        System.out.println(n+ " is Types");
    }

}
