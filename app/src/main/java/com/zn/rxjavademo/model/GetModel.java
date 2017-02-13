package com.zn.rxjavademo.model;

/**
 *
 * Created by zn on 17/2/13.
 */
public class GetModel {
    private int code;
    private String message;
    private XgoEntity entity;

    private class XgoEntity {
            private Data data;

        private class Data {
            private int id;
            private String  name;
            private String value;
            private Links links;

            @Override
            public String toString() {
                return "Data{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", value='" + value + '\'' +
                        ", links=" + links +
                        '}';
            }

            private class Links {
                private String rel;
                private String href;

                @Override
                public String toString() {
                    return "Links{" +
                            "rel='" + rel + '\'' +
                            ", href='" + href + '\'' +
                            '}';
                }
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
        return "PostModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", entity=" + entity +
                '}';
    }
}