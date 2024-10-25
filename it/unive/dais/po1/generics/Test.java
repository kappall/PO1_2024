package it.unive.dais.po1.generics;
import java.lang.*;

public class Test {
    public static void main(String[] args) {
        C<Integer> el = new C<>(3); // è necessario usare <> anche se vuoti
        C<Character> el1 = new C<>('b');
        C.print(el.getInfo());
        C.print(el1.getInfo());
        C<Types> el3 = new C<>(Types.ONE); // deve essere un enum di Types, altrimento non compila
        C.print(el3.getInfo()); //in bytecode: INVOKEVIRTUAL it/unive/dais/po1/generics/C.getInfo ()Ljava/lang/Object; cioè ritorna un Object
        //int v = el3.getInfo(); // possibile solo se il tipo di ritorno e il tipo della variabile sono compatibili

        Restricted<Types> el4 = new Restricted<>(Types.THREE);

        Restricted.print((Types)el4.getInfo());
        /* in bytecod is:
            ALOAD 4
            INVOKEVIRTUAL it/unive/dais/po1/generics/Restricted.getInfo ()Ljava/lang/Object;
            CHECKCAST it/unive/dais/po1/generics/Types
            INVOKESTATIC it/unive/dais/po1/generics/Restricted.print (Lit/unive/dais/po1/generics/Types;)V
            L9
         */

        Extending<Empty> el5 = new Extending<>(el1);
        System.out.println(el5.getEl().getInfo());

    }

}
