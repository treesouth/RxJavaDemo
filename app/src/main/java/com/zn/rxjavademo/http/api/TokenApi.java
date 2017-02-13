package com.zn.rxjavademo.http.api;

import android.support.annotation.NonNull;
import com.zn.rxjavademo.domain.DataInfo;
import com.zn.rxjavademo.domain.Token;
import rx.Observable;
import rx.functions.Func1;

import java.util.Random;

public class TokenApi {

    public static Observable<Token> getToken(@NonNull String auth) {
        return Observable.just(auth).map(new Func1<String, Token>() {
            @Override
            public Token call(String s) {

                try {
                    Thread.sleep(new Random().nextInt(600) + 600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Token token = new Token();
                token.token = createToken();

                return token;
            }
        });
    }

    private static String createToken() {

        return "token_zn_" + System.currentTimeMillis() % 1000;
    }


    public static Observable<DataInfo> getData(@NonNull Token token) {
        return Observable.just(token).map(new Func1<Token, DataInfo>() {
            @Override
            public DataInfo call(Token token) {


                try {
                    Thread.sleep(new Random().nextInt(600) + 600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (token.isInvalid) {
                    throw new IllegalArgumentException("Token is invalid");
                }
                DataInfo dataInfo = new DataInfo();
                dataInfo.id = (int) (System.currentTimeMillis() % 1000);
                dataInfo.name = "USER_" + dataInfo.id;

                return dataInfo;
            }
        });
    }
}
