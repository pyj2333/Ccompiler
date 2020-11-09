package ccompiler.wordsplit;

/**
 * @Author Fizz Pu
 * @Date 2020/10/31 下午8:52
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

/**
 * 识别字符串常量的dfa
 */
public class DoubleQuotesDfa implements Dfa{

    private enum State {
        status0, status1, status2, statusError
    }

    @Override
    public Token getToke(String str, int start, int end, int row) {
        if(start < 0) throw new IllegalArgumentException("开始字符的下标不能小于0");
        if(end >= str.length()) throw new IllegalArgumentException("结束字符的下标不能大于等于字符串长度");

        int index = start;
        char curChar;
        State state = State.status0;
        StringBuilder sb = new StringBuilder();

        while (index <= end){
            curChar = str.charAt(index++);
            switch (state){
                case status0:{
                    if(curChar == '"'){
                        state = State.status1;
                        sb.append(curChar);
                    }
                    else {
                        state = State.statusError;
                        end = -1;
                    }
                    break;
                }

                case status1:{
                    sb.append(curChar);
                    if(curChar == '"')state = State.status2;
                    break;
                }

                case status2:{
                    end = -1;
                    break;
                }
            }
        }

        if(state == State.status1 || state == State.statusError )return null;
        else return new Token(TypeImp.ConstStr, new TokeValueImp(sb.toString(), row, start,start + sb.length() - 1));
    }

    @Override
    public boolean canRecognition(String str, int start, int end, int row) {
        return false;
    }
}
