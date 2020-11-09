package ccompiler.wordsplit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Fizz Pu
 * @Date 2020/10/31 下午10:46
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
class IdentifierDfaTest {

    static  Dfa dfa;
    static  String str;

    @BeforeAll
    static void setUp(){
        dfa = new IdentifierDfa();
        str = "hello_*f";
    }

    @Test
    void getToke() {
        System.out.println(dfa.getToke(str, 0, str.length()-1, 1)); // hello_*f
        System.out.println(dfa.getToke(str, 1, str.length()-1, 1)); // ello_*f
        System.out.println(dfa.getToke(str, 0, 4, 1));   // hello
        System.out.println(dfa.getToke("a *", 0, 2, 1));   // a *
        System.out.println(dfa.getToke("a\n\3", 0, 2, 1));   // a\n3 jav里面\不是一个字符
        System.out.println(dfa.getToke("hu _D", 0, 4, 1));   // hu _D
        System.out.println(dfa.getToke("_hu _D", 0, 5, 1));   // _hu _D
        System.out.println(dfa.getToke("", 0, 0, 1));   // ""
    }

}