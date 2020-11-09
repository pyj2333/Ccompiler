package ccompiler.grammaranalyse.recursion;

import ccompiler.wordsplit.Token;
import ccompiler.wordsplit.TokenType;
import ccompiler.wordsplit.TypeImp;

import java.util.List;

/**
 * @Author Fizz Pu
 * @Date 2020/11/4 下午3:41
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public class Analyse {
    private List<Token> tokens;
    private int curIndex = 0; // 当前下标

    public int getCurIndex() {
        return curIndex;
    }

    public Analyse(List<Token> tokens, int curIndex) {
        this.tokens = tokens;
        this.curIndex = curIndex;
    }

    // 开始分析
    public void start(){
        print("start - > block");
        block();
        print("分析成功");
    }

    /**-----------------------------私有方法---------------------------------------*/
    private List<Token> getTokens(){return tokens;}

    private void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    private void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    // 异常处理
    private void error(String msg){
        throw  new RuntimeException("错误, 分析终止, 产生式: " + msg);
    }

    // 信息提示
    private void print(String info) {
        System.out.println(info);
    }

    // 是否相等
    static boolean equals(String a, String b){
        return a.equals(b);
    }

    // 获得token的值
    private String getContent(int index){
        return index < tokens.size() ? tokens.get(index).getValue().getTextValue() : null;
    }

    // 获得token的类型
    private TokenType getType(int index){
        return tokens.get(index).getType();
    }

    /**--------------------产生式------------------------------------------*/
    private void block(){
        String content = getContent(curIndex);
         if(content != null &&  content.equals("{")){
             print("block- > {stmts}");
             curIndex++;
             stmts();
             String next = getContent(curIndex);
             if(next != null && next.equals("}")){curIndex++;}
             else{ error("block - > {stmts}");}
         } else error("block - > {stmts}");
    }

    private void stmts(){
        String content = getContent(curIndex);
        if(content != null && content.equals("}")) {
            print("stmts - > null");
        } else {
            print("stmts - > stmt stmts");
            stmt();
            stmts();
        }
    }

    private void stmt(){
        TokenType type = getType(curIndex);
        String content = getContent(curIndex);
        if(type == TypeImp.Variable){
            helper1();
        }else if(content != null && content.equals("if")){
            helper2();
        } else if(content != null && content.equals("while")){
            helper3();
        } else if(content != null && content.equals("do")){
            helper4();
        } else if(content != null && content.equals("break")){

        } else {
            block();
        }
    }

    private void helper1(){
        print("stmt - >  id = expr");
        String next = getContent(++curIndex);
        if(next != null && next.equals("=")){
            curIndex++;
            expr();
            String th = getContent(curIndex);
            if((th != null && th.equals(";"))){curIndex++;}
            else {error("stmt - > id = expr;");}
        }
        else error("stmt - > id = expr;");
    }

    private void helper2(){
        print("stmt - > if(bool) stmt stmt1");
        String cur = getContent(++curIndex);
        if(cur != null && cur.equals("(")){
            curIndex++;
            bool();
            String next = getContent(curIndex);
            if(next != null && next.equals(")")){
                stmt();
                stmt1();
            } else {
                error("stmt - > if(bool) stmt stmt1");
            }
        }
        else{
            error("stmt - > if(bool) stmt stmt1");
        }
    }

    private void helper3(){
        print("stmt - > while (bool) stmt");
        String cur = getContent(++curIndex);
        if(cur != null && cur.equals("(")){
            curIndex++;
            bool();
            String next = getContent(curIndex);
            if(next != null && next.equals(")")){
                curIndex++;
                stmt();
            } else {
                error("stmt - > while (bool) stmt");
            }
        }
        else{
            error("stmt - > while (bool) stmt");
        }
    }

    private void helper4(){
        print("stmt - > do stmt while (bool)");
        String cur = getContent(++curIndex);
        if(cur != null && cur.equals("do")){
            curIndex++;
            stmt();
            String next = getContent(curIndex);
            if(next != null && next.equals("while")){
                helper5();
            } else {
                error("stmt - > do stmt while (bool)");
            }
        }
        else{
            error("stmt - > do stmt while (bool)");
        }
    }

    private void helper5(){
        String cur = getContent(++curIndex);
        if(cur != null && cur.equals("(")){
            curIndex++;
            bool();
            String next = getContent(curIndex);
            if(next != null && next.equals(")")){}
            else { error("stmt - > do stmt while (bool)");}
        }
        else{
            error("stmt - > do stmt while (bool)");
        }
    }

    private void stmt1(){
        String content = getContent(curIndex);
        if(content != null && content.equals("else")){
            print("stmt1 - > else stmt");
            curIndex++;
            stmt();
        } else {
            print("stmt1 - > null");
        }
    }

    private void bool(){
        expr();
        String content = getContent(curIndex);
        if(content != null && content.equals("<")){
            print("bool - > expr < bool1");
            curIndex++;
            bool1();
        }
        else if(content != null && content.equals(">")){
            print("bool - > expr > bool1");
            curIndex++;
            bool1();
        }

        else if(content != null && content.equals(">=")){
            print("bool - > expr >= bool1");
            curIndex++;
            bool1();
        }

        else if(content != null && content.equals("<=")){
            print("bool - > expr <= bool1");
            curIndex++;
            bool1();
        }

        else error("bool - > expr < bool1 | bool - > expr < bool1");

    }

    private void expr(){
        term();
        expr1();
    }

    private void bool1(){
        String content = getContent(curIndex);
        if(content != null &&  content.equals("=")){
            print("bool1 - > = expr");
            curIndex++;
        } else {
            print("bool1 - > expr");
        }
        expr();
    }

    private void expr1(){
        String cur = getContent(curIndex);
        if(cur != null && cur.equals("+")){
            print("expr1 -> + term expr1");
            curIndex++;
            term();
            expr1();
        }
        if(cur != null && cur.equals("-")){
            print("expr1 -> - term expr1");
            curIndex++;
            term();
            expr1();
        }else {
            print("expr1 -> null");
        }
    }

    private void term(){
        factor();
        term1();
    }

    private void term1(){
        String cur = getContent(curIndex);
        if(cur != null && cur.equals("*")){
            print("term1 -> * factor term1");
            curIndex++;
            factor();
            term1();
        }
        if(cur != null && cur.equals("/")){
            print("term1 -> / factor term1");
            curIndex++;
            factor();
            term1();
        }else {
            print("term1 -> null");
        }
    }

    private void factor(){
        String content = getContent(curIndex);
        TokenType tokenType = getType(curIndex);
        if(tokenType == TypeImp.BInt || tokenType == TypeImp.HexInt || tokenType == TypeImp.ObcInt){
            curIndex++;
            print("factor -> num");
        }
        else if(tokenType == TypeImp.Variable){
            curIndex++;
            print("factor -> id");
        }
        else if(content != null && content.equals("(")){
            print("factor -> (expr)");
            curIndex++;
            expr();
            String next = getContent(curIndex);
            if(next != null && next.equals(")")){curIndex++;}
            else{error("factor -> (expr)");}
        } else{
            error("factor -> (expr) | id | num");
        }
    }
}
