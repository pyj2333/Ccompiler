package ccompiler.wordsplit;

/**
 * @Author Fizz Pu
 * @Date 2020/10/31 下午7:52
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

/**
 * 识别十个进制整型，16进制整形，8进制整型
 * 这种每个dfa都要写一个算法，实在是太愚蠢了，后面可以采用矩阵的形式来简化
 */

public class NumberDfa implements Dfa{

    private enum State{Status0, Status1, Status2, Status3, Status4, Status5, Status6, StatusError}

    @Override
    public Token getToke(String str, int start, int end, int row) {
        StringBuilder token = new StringBuilder();
        State currentState = State.Status0;
        int index = start;
        char curChar;

        if(start < 0) throw new IllegalArgumentException("开始字符的下标不能小于0");
        if(end >= str.length()) throw new IllegalArgumentException("结束字符的下标不能大于等于字符串长度");

        while (index <= end){
            curChar = str.charAt(index++);
            token.append(curChar);

            switch (currentState){
                case Status0:{
                    if(curChar == '0' ){
                        currentState = State.Status1;
                    }
                    else if(curChar <= '9' && curChar >= '1'){
                        currentState = State.Status2;
                    }
                    else {
                        currentState = State.StatusError;
                        token.deleteCharAt(token.length()-1);
                    }
                     break;
                }
                case Status1:{
                    if(curChar == 'x'|| curChar == 'X' )currentState = State.Status3;
                    else if(curChar == 'H'|| curChar == 'h' )currentState = State.Status5;
                    else {
                        currentState = State.StatusError;
                        token.deleteCharAt(token.length()-1);
                    }
                    break;
                }

                case Status2:{
                    if(!WordSplit.isNumber(curChar) ){
                        // 退出循环
                        token.deleteCharAt(token.length()-1);
                        end = -1;
                    }
                    break;
                }

                case Status3:{
                    if(curChar != '0' && WordSplit.isHex(curChar))currentState = State.Status4;
                    else {
                        currentState = State.StatusError;
                        token.deleteCharAt(token.length()-1);
                    }
                    break;
                }

                case Status4:{
                     if(!WordSplit.isHex(curChar)){
                         end = -1;
                         token.deleteCharAt(token.length()-1);
                     }
                     break;
                }

                case Status5:{
                    if(curChar != '0' && WordSplit.isObc(curChar))currentState = State.Status6;
                    else {
                        currentState = State.StatusError;
                        token.deleteCharAt(token.length()-1);
                    }
                    break;
                }

                case Status6:{
                    if(!WordSplit.isObc(curChar)){
                        end = -1;
                        token.deleteCharAt(token.length()-1);
                    }
                    break;
                } case StatusError:{
                    end = -1;
                }
            }
        }



        TokeValueInfo temp;
        String text;
        if(currentState == State.StatusError )return null;
        else if(currentState == State.Status2 || currentState == State.Status1){
            temp = new TokeValueImp(token.toString(), row, start, start + token.length() - 1);
            return new Token(TypeImp.BInt, temp);
        }
        else if(currentState == State.Status4){
            // 16进制删除前两个字符
            text = token.delete(0, 2).toString();
            temp = new TokeValueImp(text, row, start, start + token.length() - 1);
            return new Token(TypeImp.HexInt, temp);
        }
        else {
            // 8进制删除前两个字符
            text = token.delete(0, 2).toString();
            temp = new TokeValueImp(text, row, start, start + token.length() - 1);
            return new Token(TypeImp.ObcInt, temp);
        }
    }

    @Override
    public boolean canRecognition(String str, int start, int end, int row) {
        return getToke(str, start, end, row) != null;
    }
}
