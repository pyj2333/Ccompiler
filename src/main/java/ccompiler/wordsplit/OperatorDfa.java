package ccompiler.wordsplit;

/**
 * @Author Fizz Pu
 * @Date 2020/10/31 下午7:53
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public class OperatorDfa implements Dfa{

    @Override
    public Token getToke(String str, int start, int end, int row) {
        if(start < 0) throw new IllegalArgumentException("开始字符的下标不能小于0");
        if(end >= str.length()) throw new IllegalArgumentException("结束字符的下标不能大于等于字符串长度");

        char curChar = str.charAt(start);
        char nextChar = (start + 1 <= end ? str.charAt(start+1): 0);
        StringBuilder sb = new StringBuilder();
        int flag = 1;

        if(curChar == '=' || curChar == '!' || curChar=='<' || curChar=='>' ) {
            {
                sb.append(curChar);
                if (nextChar == '=') sb.append(nextChar);
            }
        }

        else if(curChar == '|') {
            sb.append(curChar);
            if (nextChar == '|') sb.append(nextChar);
        }

        else if(curChar == '&') {
            sb.append(curChar);
            if (nextChar == '&') sb.append(nextChar);
        }

        else if(WordSplit.spcialEles.contains(curChar)){
            return new Token(TypeImp.Operator, new TokeValueImp(String.valueOf(curChar), row, start, start));
        }

        else {
            flag = 0;
        }

        // 错误状态
        if(flag == 0){
            return null;
        }
        return new Token(TypeImp.Operator, new TokeValueImp(sb.toString(), row, start, start+sb.length()-1));
    }

    @Override
    public boolean canRecognition(String str, int start, int end, int row) {
        return getToke(str, start, end, row) != null;
    }
}
