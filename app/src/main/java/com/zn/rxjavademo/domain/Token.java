package com.zn.rxjavademo.domain;

public class Token {
    public String token;
    public boolean isInvalid;

    public Token(boolean isInvalid) {
        this.isInvalid = isInvalid;
    }

    public Token() {
    }
}
