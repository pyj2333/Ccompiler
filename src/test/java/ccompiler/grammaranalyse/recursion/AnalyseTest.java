package ccompiler.grammaranalyse.recursion;

import ccompiler.wordsplit.TokeValueImp;
import ccompiler.wordsplit.Token;
import ccompiler.wordsplit.WordSplit;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Fizz Pu
 * @Date 2020/11/9 下午5:25
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
class AnalyseTest {

    @Test
    void test1(){
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(null, new TokeValueImp("{", 0,0,0)));
        tokens.add(new Token(null, new TokeValueImp("}", 0,0,0)));
        Analyse analyse = new Analyse(tokens, 0);
        analyse.start();
    }

    @Test
    void test2() throws Exception{
        String path = "/home/fizz/Ccompiler/src/main/resources/test.cc";

        File file = new File(path);
        Reader reader = new FileReader(file);
        StringBuilder sb = new StringBuilder();

        // 全部读入内存
        char[] chars = new char[1024];
        int charCount;

        while ((charCount = reader.read(chars)) != -1){
            sb.append(chars, 0, charCount);
        }

        WordSplit wordSplit = new WordSplit();
        List<Token> tokens = wordSplit.getTokes(sb.toString());
        for(Token token : tokens){
            System.out.println(token);
        }
        Analyse analyse = new Analyse(tokens, 0);
        analyse.start();
    }
}