package ccompiler.wordsplit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Fizz Pu
 * @Date 2020/11/1 上午11:52
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
class WordSplitTest {

    static WordSplit split;

    @BeforeAll
    static void setUp(){
        split = new WordSplit();
    }


    // 测试空格段
    @Test
    void getTokes() {
        String s = "       ";
        System.out.println(split.getTokes(s).toString());
    }

    // 测试标识符号的识别
    // 测试空格段
    @Test
    void test_() {
        String s = "_fefae_ _42ewfw_";
        System.out.println(split.getTokes(s).toString());
    }

    // 字母开头
    @Test
    void test3() {
        String s = "int int3 Int float$";
        System.out.println(split.getTokes(s).toString());
    }

    // 数字开头
    @Test
    void test4(){
        String s = "234 0x32f 0h3122";
        System.out.println(split.getTokes(s).toString());
    }

    // 运算符的识别
    // 数字开头
    @Test
    void test5(){
        String s = "+ - / =";
        System.out.println(split.getTokes(s).toString());
    }

    // 运算符的识别
    // 字符串常量和字符常量的识别
    @Test
    void test6(){
        String s = "'3' 'f' \"fsdfsfds\"";
        System.out.println(split.getTokes(s).toString());
    }

    @Test
    void test7(){
        String s = "int main()\n{int a = 1;}";
        System.out.println(split.getTokes(s).toString());
    }
}