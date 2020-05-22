package br.com.hazelcast.awsecsbean;

import java.io.Serializable;

public class Mensagem implements Serializable {

    private String key;

    private String value;

    public Mensagem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    Mensagem(){}

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
