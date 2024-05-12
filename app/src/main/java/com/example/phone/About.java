package com.example.phone;

import java.io.Serializable;

public class About implements Serializable {
    private String name;
    private String price;
    private String info1;
    private String info2;
    private String info3;
    private int count;
    private String id;


    public About() {
    }

    public About(String name, String price, String info1, String info2, String info3, int count) {
        this.name = name;
        this.price = price;
        this.info1 = info1;
        this.info2 = info2;
        this.info3 = info3;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getInfo1() {
        return info1;
    }

    public String getInfo2() {
        return info2;
    }

    public String getInfo3() {
        return info3;
    }

    public int getCount() {
        return count;
    }

    public String _getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addToCart() {
        count++;
    }

    public void delete() {
        if (count <= 0) {
            count = 0;
        } else {
            count--;
        }
    }
}