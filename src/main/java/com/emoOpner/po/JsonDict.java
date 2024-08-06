package com.emoOpner.po;

import lombok.Data;

@Data
public class JsonDict {
    private String accessToken;
    private long expires;
    private long expiresIn;
    private String uid;

    public JsonDict(String accessToken, long expires, long expiresIn, String uid) {
        this.accessToken = accessToken;
        this.expires = expires;
        this.expiresIn = expiresIn;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "JsonDict{" +
                "accessToken='" + accessToken + '\'' +
                ", expires=" + expires +
                ", expiresIn=" + expiresIn +
                ", uid='" + uid + '\'' +
                '}';
    }
}
