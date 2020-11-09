package ccompiler.wordsplit;

import java.io.*;
import java.util.List;

/**
 * @Author Fizz Pu
 * @Date 2020/11/1 下午5:15
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // java -p path
        /*if(args.length < 2){
            throw  new IllegalArgumentException("缺少参数 -p 文件路径, java -p /home/file/test.c");
        }


        String path = args[1];*/
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
    }
}
