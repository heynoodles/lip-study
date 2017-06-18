package com.parsing.backtrack;

import com.parsing.lexer.Lexer;
import com.parsing.lexer.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoxin.wei
 * 回溯解析器
 */
public abstract class Parser {

    Lexer input;
    List<Integer> markers; // 栈，存放记录位置的标记
    List<Token> lookahead; // 缓冲区
    int p = 0;

    public Parser(Lexer lexer) {
        this.input = lexer;
        markers = new ArrayList<Integer>();
        lookahead = new ArrayList<Token>();
    }

    public Token LT(int i) {
        sync(i);
        return lookahead.get(p + i -1);
    }

    public int LI(int i) {
        return LT(i).type;
    }

    private void sync(int i) {
        if (p+i-1 > (lookahead.size() -1)) {
            int n = (p+i-1) - (lookahead.size() -1);
            fill(n);
        }
    }

    private void fill(int n) {
        for (int i = 1; i <= n; i++) {
            lookahead.add(input.nextToken());
        }
    }

    void match(int x) {
        if (LI(1) == x) {
            consume();
        } else
            throw new Error("match error");
    }

    private void consume() {
        p++;
        if (p == lookahead.size()-1 && !isSpeculating()) {
            p = 0;
            lookahead.clear();
        }
        sync(1);
    }

    void mark() {
        markers.add(p);
    }

    void release() {
        Integer marker = markers.get(markers.size() - 1);
        markers.remove(markers.size() - 1);
        seek(marker);
    }

    private void seek(Integer marker) {
        p = marker;
    }

    private boolean isSpeculating() {
        return markers.size() > 0;
    }

}
