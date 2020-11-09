package ccompiler.wordsplit;

/**
 * @Author Fizz Pu
 * @Date 2020/10/31 下午7:51
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

/**
 * 识别标识符的dfa
 */
public class ObserveDfa implements Dfa{



    // 思路
    // 遍历字符串，并不断拼接字符，遇到' ','{',';','('则终止拼接，检查是在拼接到的字符串是否是保留字
    @Override
    public Token getToke(String str, int start, int end, int row) {
        if(start < 0) throw new IllegalArgumentException("开始字符的下标不能小于0");
        if(end >= str.length()) throw new IllegalArgumentException("结束字符的下标不能大于等于字符串长度");

        StringBuilder sb= new StringBuilder();
        char curChar;
        String word;
        int index;

        for(index = start; index <= end; ++index){
            curChar = str.charAt(index);
            if(WordSplit.oberservedKetEnd(curChar))break;
            sb.append(curChar);
        }

        // 检查是否为保留字
        if(!WordSplit.observedKey.contains(word = sb.toString())){
            return null;
        }
        return new Token(TypeImp.ReserveWord, new TokeValueImp(word, row, start, start + word.length() - 1));
    }

    @Override
    public boolean canRecognition(String str, int start, int end, int row){
        return getToke(str, start, end, row) != null;
    }
}
