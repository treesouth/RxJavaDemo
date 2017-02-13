package com.zn.rxjavademo.model;

import java.util.List;

/**
 * Created by zn on 17/2/13.
 */
public class PutModel {
    private int code;
    private String message;
    private XgoEntity entity;

    private class XgoEntity {
        private List<Data> data;

        private class Data {
            @Override
            public String toString() {
                return "Data{}";
            }
        }

        @Override
        public String toString() {
            return "XgoEntity{" +
                    "data=" + data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PutModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", entity=" + entity +
                '}';
    }
}
