package com.dalong.elmbusiness.event;

/**
 * Created by dalong on 2016/12/27.
 */

public class MessageEvent {
    public int  num;
    public int  price;

    public MessageEvent(int totalNum, int price) {
        this.num = totalNum;
        this.price = price;
    }
}
