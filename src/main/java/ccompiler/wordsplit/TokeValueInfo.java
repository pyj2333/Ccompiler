package ccompiler.wordsplit;

/**
 * @Author Fizz Pu
 * @Date 2020/10/31 下午7:36
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public interface TokeValueInfo {

    public String getTextValue();

    public Integer getLine();

    public Integer getStart();

    public Integer getEnd();
}
