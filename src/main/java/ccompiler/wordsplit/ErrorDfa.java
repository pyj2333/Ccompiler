package ccompiler.wordsplit;

/**
 * @Author Fizz Pu
 * @Date 2020/10/31 下午8:58
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public class ErrorDfa implements Dfa {

    @Override
    public Token getToke(String str, int start, int end, int row) {
        return null;
    }

    @Override
    public boolean canRecognition(String str, int start, int end, int row) {
        return false;
    }
}
