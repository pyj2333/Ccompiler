package ccompiler.wordsplit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Fizz Pu
 * @Date 2020/11/1 上午8:53
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
class NumberDfaTest {

    static  Dfa dfa;

    @BeforeAll
    static void setUp(){
        dfa = new NumberDfa();
    }

    @Test
    void getToke() {
        System.out.println(dfa.getToke("2342-", 0, 4, 1));
        System.out.println(dfa.getToke("0x131", 0, 4, 1));
        System.out.println(dfa.getToke("0X23*-", 0, 5, 1));
        System.out.println(dfa.getToke("0H311x;", 0, 5, 1));
        System.out.println(dfa.getToke("0h322", 0, 4, 1));
        System.out.println(dfa.getToke("0", 0, 0, 1));
        System.out.println(dfa.getToke("01313", 0, 0, 1));
        System.out.println(dfa.getToke("*1313", 0, 0, 1));
        System.out.println((char)0);
    }

    @Test
    void canRecognition() {
    }
}