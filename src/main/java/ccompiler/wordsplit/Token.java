package ccompiler.wordsplit;

/**
 * @Author Fizz Pu
 * @Date 2020/10/28 下午4:18
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public class Token {
    private TokenType type;    //类型

    private TokeValueInfo value; // 值

    public Token(TokenType type, TokeValueInfo value) {
        this.type = type;
        this.value = value;
    }

    public Token() {
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public TokeValueInfo getValue() {
        return value;
    }

    public void setValue(TokeValueInfo value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "第" + value.getLine() +"行：<" + type + "," + value.getTextValue() + ">";
    }
}
