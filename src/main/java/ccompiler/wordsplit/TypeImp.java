package ccompiler.wordsplit;

/**
 * @Author Fizz Pu
 * @Date 2020/10/31 下午7:40
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

public enum  TypeImp implements TokenType{
    Error ,       // 错误  0
    ReserveWord, // 保留字 1
    Variable,    // 标识符 2
    Operator,    // 远算符 3
    ConstChar,    // 常量字符 4
    ConstStr,   // 常量字符串 5
    BInt,    // 十进制整型 6
    ObcInt,    // 8进制整型 7
    HexInt    // 16进制整型 8
}
