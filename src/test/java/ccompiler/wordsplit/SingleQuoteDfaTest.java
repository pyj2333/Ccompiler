package ccompiler.wordsplit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Fizz Pu
 * @Date 2020/11/1 上午11:35
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

class SingleQuoteDfaTest {

    static  Dfa dfa;

    @BeforeAll
    static void setUp(){
        dfa = new SingleQuoteDfa();
    }

    @Test
    void getToke() {
        System.out.println(dfa.getToke("'3'", 0, 2, 1));
        System.out.println(dfa.getToke("'", 0, 0, 1));
        System.out.println(dfa.getToke("''", 0, 1, 1));
        System.out.println(dfa.getToke("'w", 0, 1, 1));
        System.out.println(dfa.getToke("'ww", 0, 4, 1));
    }

    @Test
    void canRecognition() {
    }
}