package ccompiler.wordsplit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Fizz Pu
 * @Date 2020/10/28 下午4:28
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */


class TokenTest {

    @Test
    void print(){
        Token token = new Token();
        token.setType(TypeImp.ConstChar);
        token.setValue(null);
        System.out.println(token);
    }
}