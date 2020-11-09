package ccompiler.wordsplit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Fizz Pu
 * @Date 2020/11/1 上午11:40
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
class DoubleQuotesDfaTest {

    static  Dfa dfa;

    @BeforeAll
    static void setUp(){
        dfa = new DoubleQuotesDfa();
    }

    @Test
    void getToke() {
        System.out.println(dfa.getToke("\"ffsd\"", 0, 5, 1));
        System.out.println(dfa.getToke("\"fsds", 0, 4, 1));
        System.out.println(dfa.getToke("r\"", 0, 1, 1));
        System.out.println(dfa.getToke("\"\"", 0, 1, 1));
    }

    @Test
    void canRecognition() {
    }
}