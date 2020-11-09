package ccompiler.wordsplit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Fizz Pu
 * @Date 2020/11/1 上午12:35
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
class ObserveDfaTest {
    static  Dfa dfa;

    @BeforeAll
    static void setUp(){
        dfa = new ObserveDfa();
    }

    @Test
    void getToke() {
    System.out.println(dfa.getToke("int4f", 0, 4, 1));
    System.out.println(dfa.getToke("int", 0, 2, 1));
    System.out.println(dfa.getToke("if()", 0, 3, 1));
    System.out.println(dfa.getToke("break;", 0, 5, 1));
    System.out.println(dfa.getToke("case{", 0, 4, 1));
    System.out.println(dfa.getToke("else{", 0, 4, 1));
    System.out.println(dfa.getToke("case", 0, 3, 1));
    }

    @Test
    void canRecognition() {
    }
}