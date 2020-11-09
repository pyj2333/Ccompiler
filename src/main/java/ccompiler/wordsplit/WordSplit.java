package ccompiler.wordsplit;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Fizz Pu
 * @Date 2020/10/28 下午4:12
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */


public class WordSplit {
    static HashSet<Character> spcialEles = new HashSet<Character>();
    static {
        spcialEles.add('+');
        spcialEles.add('-');
        spcialEles.add('*');
        spcialEles.add('/');
        spcialEles.add('{');
        spcialEles.add('}');
        spcialEles.add(';');
        spcialEles.add('(');
        spcialEles.add(')');
        spcialEles.add('=');
        spcialEles.add('！');
        spcialEles.add('<');
        spcialEles.add('>');
    }


    // 保留字表
    static HashSet<String> observedKey = new HashSet<String>();

    static {
        observedKey.add("if");
        observedKey.add("else");
        observedKey.add("while");
        observedKey.add("do");
        observedKey.add("main");
        observedKey.add("int");
        observedKey.add("float");
        observedKey.add("double");
        observedKey.add("return");
        observedKey.add("const");
        observedKey.add("void");
        observedKey.add("continue");
        observedKey.add("break");
        observedKey.add("char");
        observedKey.add("unsigned");
        observedKey.add("enum");
        observedKey.add("long");
        observedKey.add("switch");
        observedKey.add("case");
        observedKey.add("signed");
        observedKey.add("auto");
        observedKey.add("static");
    }

    public static void main(String[] args) {
        System.out.println("hello word");
    }

    // ch是字符吗
    static boolean isLetter(char ch){
        return (ch <= 'z' && ch >= 'a') || (ch <= 'Z' && ch >= 'A');
    }

    // ch是数字吗？
    static boolean isNumber(char ch){
        return ch <= '9' && ch >= '0';
    }

    // ch是特殊字符吗
    static boolean isSpecialEle(char ch){
        return spcialEles.contains(ch);
    }

    // ch是下划线吗
    static boolean is_(char ch){
        return ch == '_';
    }

    // ch是\n, ' ', '\t'吗
    static  boolean isValid(char ch){
        return ch == ' ' || ch == '\n' || ch == '\t';
    }

    // 新的一行
    static boolean isnewRow(char ch){ return  ch == '\n';}

    // 是否是保留字的结尾
    static boolean oberservedKetEnd(char ch){
        return ch == '{' || ch == ';' || ch == ' ' || ch == '(';
    }

    // 0-9 a - f A - F
    static boolean isHex(char ch){
        return ch <= '9' && ch >= '0' || ch <= 'f' && ch >= 'a' || ch <= 'F' && ch >= 'A';
    }

    // 0-7之间
    static boolean isObc(char ch){
        return ch <= '7' && ch >= '0';
    }

    /**
     * 输入源程序的一行，进行单词分割，后面可以改为对以;结尾的句子进行分割
     * @param str 去掉注释的源码
     * @return Token列表
     */
    public List<Token> getTokes(String str){
        int strLen = str.length();
        int index = 0;
        int row = 1;
        Dfa resultDfa = null;
        List<Token> res = new ArrayList<Token>();

        while(index < strLen){
            char ch = str.charAt(index);

            if(isnewRow(ch)){ row++;}

            // 空格,\t,\n无效字符，读取下一个字符
            if(isValid(ch)){
                  index++;
                  continue;
            }

            // ch是'_'，进入标识符号的识别
            if(is_(ch) ){
                resultDfa = new IdentifierDfa();
            }

            // 是字母，优先进入保留字的识别，如果无法识别，则进行标识符的识别
            else if(isLetter(ch)){
                resultDfa = new ObserveDfa();
                if(!resultDfa.canRecognition(str, index, strLen-1, row)){
                    resultDfa = new IdentifierDfa();
                }
            }

            // 是数字
            else if(isNumber(ch)){
                resultDfa = new NumberDfa();
            }

            // 是特殊字符
            else if(isSpecialEle(ch)){
                resultDfa = new OperatorDfa();
            }

            // 字符常量的识别
            else if(ch == '\''){
                resultDfa = new SingleQuoteDfa();
            }   else if(ch == '"'){
                 resultDfa = new DoubleQuotesDfa();
            }


            // 获得token, 修改开始字符
            Token token = resultDfa.getToke(str, index, strLen-1, row);
            // 无法识别
            if(token != null){
                res.add(token);
                index = token.getValue().getEnd() + 1;
            }  else {
                System.out.println(ch);
                throw new RuntimeException("第"+row + "行"+"第"+index+"个字符无法识别");
            }


        }
        return  res;
    }
}
