package ccompiler.wordsplit;

/**
 * @Author Fizz Pu
 * @Date 2020/10/31 下午7:40
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public class TokeValueImp  implements TokeValueInfo{
    String textValue; // 对应的值
    Integer line;   //  对应的行数
    Integer start;
    Integer end;

    public TokeValueImp(String textValue, Integer line, Integer start, Integer end) {
        this.textValue = textValue;
        this.line = line;
        this.start = start;
        this.end = end;
    }

    public TokeValueImp() {
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return textValue;
    }
}
