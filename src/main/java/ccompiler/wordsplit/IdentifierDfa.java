package ccompiler.wordsplit;

/**
 * @Author Fizz Pu
 * @Date 2020/10/31 下午7:27
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

/**
 * 识别标识符的dfa
 */
public class IdentifierDfa  implements Dfa{

    // 自定义dfa状态
    private enum IdentifierDfaState {
            Status0, Status1, StatusError
    }

    @Override
    public Token getToke(String str, int start, int end, int row) {
        char curChar;
        int index = start;
        IdentifierDfaState curState = IdentifierDfaState.Status0;
        StringBuilder sb = new StringBuilder(end-start+1);

        if(start < 0) throw new IllegalArgumentException("开始字符的下标不能小于0");
        if(end >= str.length()) throw new IllegalArgumentException("结束字符的下标不能大于等于字符串长度");

        while (index <= end) {
            curChar = str.charAt(index++);
            switch (curState) {
                case Status0: {
                    if (WordSplit.is_(curChar) || WordSplit.isLetter(curChar)) {
                        curState = IdentifierDfaState.Status1;
                        sb.append(curChar);
                    } else {
                        curState = IdentifierDfaState.StatusError;
                    }
                    break;
                }
                case Status1: {
                    if (!(WordSplit.isNumber(curChar) || WordSplit.is_(curChar)
                            || WordSplit.isLetter(curChar))) {
                        end = -1;
                    } else {
                        sb.append(curChar);
                    }
                    break;
                }
                case StatusError: {
                    end = -1;
                    break;
                }
            }
        }

        if(curState == IdentifierDfaState.StatusError)return null;
        return new Token(TypeImp.Variable, new TokeValueImp(sb.toString(), row, start, start + sb.length()-1));
    }

    @Override
    public boolean canRecognition(String str, int start, int end, int count) {
        return getToke(str, start, end, count) != null;
    }
}
