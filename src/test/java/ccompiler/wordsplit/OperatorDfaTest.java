package ccompiler.wordsplit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Fizz Pu
 * @Date 2020/11/1 上午10:15
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
class OperatorDfaTest {

    static  Dfa dfa;
    static  String str;

    @BeforeAll
    static void setUp(){
        dfa = new OperatorDfa();
    }

    @Test
    void getToke() {
        System.out.println(dfa.getToke("|21", 0, 2, 1)); // |21
        System.out.println(dfa.getToke("*", 0, 0, 1)); // ello_*f
        System.out.println(dfa.getToke("+/", 0, 1, 1));   // hello
        System.out.println(dfa.getToke("&&", 0, 1, 1));   // a\n3 jav里面\不是一个字符
        System.out.println(dfa.getToke(">=", 0, 1, 1));   // hu _D
        System.out.println(dfa.getToke("<==", 0, 2, 1));   // _hu _D
        System.out.println(dfa.getToke("||", 0, 1, 1));   // ""
        System.out.println(dfa.getToke("!=", 0, 1, 1));   // ""
        System.out.println(dfa.getToke("2=", 0, 1, 1));   // ""
    }

    @Test
    void canRecognition() {

    }
}