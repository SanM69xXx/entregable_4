package com.sam.entregable_4;
public class turno{
    private String key;
    private String author;
    private String quote;

    public turno() {

    }

    public turno(String key, String author, String quote) {
        this.key = key;
        this.author = author;
        this.quote = quote;
    }

    public String getKey() {
        return key;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuote() {
        return quote;
    }
}