package ccompiler.wordsplit;

/**
 * @Author Fizz Pu
 * @Date 2020/10/31 下午8:50
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */


/**
 * 识别字符常量的dfa
 */
public class SingleQuoteDfa implements Dfa {

    @Override
    public Token getToke(String str, int start, int end, int row) {
        char curChar = str.charAt(start);
        StringBuilder sb = new StringBuilder();
        char sec, third;

        if(curChar != '\'' || (end - start == 0))return null;

        sb.append(curChar);
        sec = str.charAt(start+1);
        sb.append(sec);


        if(sec != '\''){ // 第二个字符不为'
            if(end-start + 1 < 3)return null; // 第三个字符不存在
            else if((third = str.charAt(start+2)) == '\'')sb.append(third); // 第三个字符为'
            else return null; // 第三个字符不为'
        }
        return new Token(TypeImp.ConstChar, new TokeValueImp(sb.toString(), row, start, start + sb.length() - 1));
    }

    @Override
    public boolean canRecognition(String str, int start, int end, int row) {
        return getToke(str, start, end, row) != null;
    }
}
