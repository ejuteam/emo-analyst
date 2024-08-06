package com.emoOpner.utils;


import java.util.ArrayList;
import java.util.Collection;

public class PagedArrayList<T> extends ArrayList<T> {
    int total = 0;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public PagedArrayList(Collection<T> collection, int total) {
        super(collection);
        this.setTotal(total);
    }

    public PagedArrayList() {
    }

    public PagedArrayList(int size) {
        super(size);
        this.setTotal(this.size());
    }

    public PagedArrayList(Collection<T> collection) {
        super(collection);
        this.setTotal(this.size());
    }
}