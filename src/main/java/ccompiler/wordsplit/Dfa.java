package ccompiler.wordsplit;

/**
 * @Author Fizz Pu
 * @Date 2020/10/31 下午7:20
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

/**
 * 定义具有特定功能的DFA，以此来实现解耦
 *
  */

public interface Dfa {


    /**
     * 根据dfa识别字符串
     * @param str 要识别的字符串
     * @param start 字符串的开始位置
     * @param end 字符串的结束位置
     * @param row token所在的行数
     * @return Token
     */
    Token getToke(String str, int start, int end, int row);

    /**
     * 是否能够识别
     * @return 能够识别
     */
    public boolean canRecognition(String str, int start, int end, int row);
}
